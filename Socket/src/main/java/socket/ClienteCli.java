package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteCli implements Runnable {
	
    String nickName = "";
    Socket sock;
    Thread hilo;

	final DataInputStream disCliente;
    final DataOutputStream dosCliente;
    boolean isConected;
    PrintStream ps;
    
    public ClienteCli(Socket sock, String nick, DataInputStream in, DataOutputStream out) {
        this.nickName = nick;
        this.sock = sock;
        this.disCliente = in;
        this.dosCliente = out;    
        
        ps = new PrintStream(System.out);
        this.isConected = true;
        this.hilo = new Thread(this, nickName);
    }
    
	@Override
	public void run() {
		String msgRecibido = "";
		String destino = "";
		
		while( this.sock.isConnected() && this.isConected )
		{
			try {
				//identificaos el mensaje
				msgRecibido = this.disCliente.readUTF().trim();
				
				if (Servidor.getClientesbaneados().containsKey(this.nickName)) {
				    long tiempoRestante = Servidor.getClientesbaneados().get(this.nickName) - System.currentTimeMillis();
				    if (tiempoRestante > 0) {
				        this.dosCliente.writeUTF(Servidor.ANSI_RED + "Estás baneado. Tiempo restante: " + tiempoRestante / 1000 + " segundos." + Servidor.ANSI_RESET);
				        continue; // Ignorar el mensaje
				    } else {
				        Servidor.getClientesbaneados().remove(this.nickName); // Eliminar el ban si ya expiró
				    }
				}
				//CUANDO EL CLIENTE ESCRIBA "/SALIR" LO DESCONECTE
				
				if (msgRecibido.equalsIgnoreCase("/salir")) {
	                ps.println(Servidor.ANSI_RED + "El cliente " + this.nickName + " ha salido del servidor." + Servidor.ANSI_RESET);
	                this.isConected = false;
	                this.notificarClientes(false);
	                this.sock.close(); // Cerrar el socket
	                Servidor.getClientesconectados().remove(this);
	                break;
	            }
				// /listar muestra los clientes conectados
				if (msgRecibido.equalsIgnoreCase("/listar")) {
				    StringBuilder listaConectados = new StringBuilder(Servidor.ANSI_GREEN + "Clientes conectados:\n" + Servidor.ANSI_RESET);
				    
				    for (ClienteCli cli : Servidor.getClientesconectados().values()) {
				    	listaConectados.append(Servidor.ANSI_RESET+"\t-"+
				    			cli.getNickName()+"\n");
				    }
				    this.dosCliente.writeUTF(listaConectados.toString());
				    continue;
				}
				// BANEAR A CLIENTES
				
				if (msgRecibido.startsWith("/banear ")) {
				    String[] partes = msgRecibido.split(" ");
				    if (partes.length == 3) {
				        String clienteABanear = partes[1];
				        long tiempoBan = Long.parseLong(partes[2]) * 1000; 
				        boolean encontrado = false;

				        for (ClienteCli cli : Servidor.getClientesconectados().values()) {
				            if (cli.getNickName().equalsIgnoreCase(clienteABanear)) {
				                encontrado = true;
				                Servidor.getClientesbaneados().put(clienteABanear, System.currentTimeMillis() + tiempoBan);
				                cli.dosCliente.writeUTF(Servidor.ANSI_RED + "Has sido baneado por " + tiempoBan / 1000 + " segundos." + Servidor.ANSI_RESET);
				                break;
				            }
				        }

				        if (!encontrado) {
				            this.dosCliente.writeUTF(Servidor.ANSI_RED + "El cliente " + clienteABanear + " no está conectado." + Servidor.ANSI_RESET);
				        }
				    } else {
				        this.dosCliente.writeUTF(Servidor.ANSI_YELLOW + "Uso: /banear <cliente> <tiempoEnSegundos>" + Servidor.ANSI_RESET);
				    }
				    continue; // Ir a la siguiente iteración
				}

				
				
				
				
				

				//identificar el destinaratio
				//   destino # mensaje a enviar
				// Furno# Todo bien?
				if( msgRecibido.contains("#") )
				{
					StringTokenizer token = new StringTokenizer(msgRecibido,"#");
					destino = token.nextToken().trim().toLowerCase();
					msgRecibido = token.nextToken().trim();
				}else {
					destino = "";
				}
				
				ps.println("\n"
						+ Servidor.ANSI_PURPLE
						+ "El cliente " 
						+ Servidor.ANSI_GREEN 
						+ this.nickName 
						+ Servidor.ANSI_PURPLE
						+ " envia: "
						+ Servidor.ANSI_YELLOW
						+ msgRecibido + "\n\t"
						+ Servidor.ANSI_PURPLE
						+ " al cliente =>"
						+ Servidor.ANSI_CYAN
						+ (destino.equalsIgnoreCase("") ? " Todos" : " ".concat(destino.toUpperCase()))
						+ "\n"
						+ Servidor.ANSI_RESET
					);
				
				//filtro de comandos
				//  mensaje= /salir
				
				
				
				//enviar mensaje
				boolean encontrado = false;
				for( ClienteCli cli : Servidor.getClientesconectados().values())
				{
					//si el mensaje a enviar esta vacio
					if(msgRecibido.equalsIgnoreCase(""))
						break;
					
					//MENSAJE PRIVADO
					if (cli.getNickName().equalsIgnoreCase(destino) && this.isConected()) {
	                    cli.dosCliente.writeUTF(Servidor.ANSI_CYAN + "[MP de " + this.nickName + "]: " + Servidor.ANSI_RESET + msgRecibido);
	                    encontrado = true;
	                    break;
	                // MENSAJE GLOBAL
	                } else if (destino.equalsIgnoreCase("") && this.isConected() && !cli.getNickName().equalsIgnoreCase(this.nickName)) {
	                    cli.dosCliente.writeUTF(Servidor.ANSI_YELLOW + "[Global de " + this.nickName + "]: " + Servidor.ANSI_RESET + msgRecibido);
	                }
	            
					
				}
				// SI UN CLIENTE NO ESTA DISPONIBLE MUESTRA ESTE MESNAJE
				if (!encontrado && !destino.equals("")) {
	                this.dosCliente.writeUTF(Servidor.ANSI_RED + "<cliente " + destino + " no disponible> " + Servidor.ANSI_RESET);
	            }


				
			} catch (IOException ex) {
				Logger.getLogger(ClienteCli.class.getName()).log(Level.SEVERE,null,ex);
				this.isConected = false;
			}
		}
	}
    
	
	
	void notificarClientes(boolean estado) {
		for( ClienteCli cli : Servidor.getClientesconectados().values())
		{
			if( !cli.getNickName().equals(this.nickName) && cli.isConected() )
			{
				try {
					if(estado)
					{
						cli.dosCliente.writeUTF(Servidor.ANSI_GREEN
								+ "\t---"
								+ this.getNickName()
								+ " se ah CONECTADO---"
								+ Servidor.ANSI_RESET
						);						
					}else {
						cli.dosCliente.writeUTF(Servidor.ANSI_RED
								+ "\t---"
								+ this.getNickName()
								+ " se ah DESCONECTADO---"
								+ Servidor.ANSI_RESET
						);
					}
				}catch(IOException ex) {
					Logger.getLogger(ClienteCli.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
		}
	}
	
	private String filtrarMensaje(String mensaje) {
	    for (String palabra : Servidor.getMalaspalabras()) {
	        mensaje = mensaje.replaceAll("(?i)" + palabra, "****");
	    }
	    return mensaje;
	}
	
    public String getNickName() {
		return nickName;
	}

	public Socket getSock() {
		return sock;
	}

	public Thread getHilo() {
		return hilo;
	}

	public boolean isConected() {
		return isConected;
	}	
	
}

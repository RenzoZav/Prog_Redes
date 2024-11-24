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
				for( ClienteCli cli : Servidor.ClientesConectados)
				{
					//si el mensaje a enviar esta vacio
					if(msgRecibido.equalsIgnoreCase(""))
						break;
					
					if(cli.getNickName().toLowerCase().equalsIgnoreCase(destino) && this.isConected )
					{
						cli.dosCliente.writeUTF(Servidor.ANSI_YELLOW 
								+this.nickName
								+ ":"
								+Servidor.ANSI_RESET
								+ msgRecibido
							);
						break;
					}else if(destino.equalsIgnoreCase("") && 
							this.isConected && 
							!cli.getNickName().toLowerCase().equalsIgnoreCase(this.nickName) ){
						cli.dosCliente.writeUTF(Servidor.ANSI_YELLOW 
								+this.nickName
								+ ":"
								+Servidor.ANSI_RESET
								+ msgRecibido
							);
					}
				}
				
			} catch (IOException ex) {
				Logger.getLogger(ClienteCli.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
	}
    
	
	
	void notificarClientes(boolean estado) {
		for( ClienteCli cli : Servidor.ClientesConectados )
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

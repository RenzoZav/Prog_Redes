package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {

	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_MAGENTA = "\u0033[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";

	PrintStream ps;
	//static LinkedList<ClienteCli> ClientesConectados;
	private static final HashMap<String, ClienteCli> clientesConectados = new HashMap<>();

	

	private static final HashMap<String, Long> clientesBaneados = new HashMap<>();
	private static final List<String> malasPalabras = Arrays.asList("puta", "trola", "pete", "tonto", "mama");

	ServerSocket serverSock;
	Socket sockCli;
	DataInputStream dis;
	DataOutputStream dos;
	int puerto = 7777;

	public Servidor() {
		try {
			ps = new PrintStream(System.out);
			dis = null;
			dos = null;
			//ClientesConectados = new LinkedList<ClienteCli>();

			serverSock = new ServerSocket(puerto);

			// verificacion de clientes conectados
			Thread verificarLista = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						// ELIMINA CLIENTES DE BANEADOS SI VENCIO EL BAN
						Servidor.clientesBaneados.entrySet()
								.removeIf(entry -> System.currentTimeMillis() > entry.getValue());

						for (ClienteCli cli : clientesConectados.values()) {
							if (!cli.getSock().isConnected() || !cli.isConected()) {
								clientesConectados.remove(cli);
								cli.notificarClientes(false);
							}
						}
						// esto es precario
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});

		} catch (IOException ex) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		new Thread(() -> {
		    Scanner scanner = new Scanner(System.in);
		    while (true) {
		        System.out.print("Servidor -> ");
		        String mensaje = scanner.nextLine();
		        if (mensaje.startsWith("/mp ")) {
		            String[] partes = mensaje.split(" ", 3);
		            if (partes.length == 3) {
		                String destino = partes[1];
		                String mensajeMp = partes[2];

		                if (clientesConectados.containsKey(destino)) {
		                    try {
								clientesConectados.get(destino).dosCliente.writeUTF(Servidor.ANSI_GREEN + "[Servidor MP]: " + mensajeMp + Servidor.ANSI_RESET);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                } else {
		                    System.out.println("Cliente no encontrado.");
		                }
		            }
		        } else {
		            for (ClienteCli cli : clientesConectados.values()) {
		                try {
							cli.dosCliente.writeUTF(Servidor.ANSI_YELLOW + "[Servidor Global]: " + mensaje + Servidor.ANSI_RESET);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }
		    }
		}).start();

		while (true) {
			try {
				ps.println("Esperando conexion de un clinete...\n");
				sockCli = serverSock.accept();

				ps.println(Servidor.ANSI_CYAN + "Cliente Conectado: " + sockCli.getInetAddress().getHostAddress()
						+ Servidor.ANSI_RESET);

				dis = new DataInputStream(sockCli.getInputStream());
				dos = new DataOutputStream(sockCli.getOutputStream());

				ps.println(
						Servidor.ANSI_CYAN + "Creando un cliente... esperado identificacion..." + Servidor.ANSI_RESET);
				String nickName = dis.readUTF();
				if (clientesConectados.containsKey(nickName)) {
				    dos.writeUTF(Servidor.ANSI_RED + "El nickname ya está en uso." + Servidor.ANSI_RESET);
				    sockCli.close();
				} else {
					ClienteCli cli = new ClienteCli(sockCli, nickName, dis, dos);

				    clientesConectados.put(nickName, cli);
				    ps.println(Servidor.ANSI_RED + "El cliente " + cli.getNickName() + " accedio al servidor.\n"
							+ Servidor.ANSI_RESET);

					cli.getHilo().start();
					cli.notificarClientes(true);
				}
			} catch (IOException ex) {
				Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
			} // catch
		} // while
		
	}// run

	
	public static HashMap<String, Long> getClientesbaneados() {
		return clientesBaneados;
	}

	public static List<String> getMalaspalabras() {
		return malasPalabras;
	}
	public static HashMap<String, ClienteCli> getClientesconectados() {
		return clientesConectados;
	}

}// class

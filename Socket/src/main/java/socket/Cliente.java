package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	byte[] iv = Cifrado.generarIV();
	InetAddress IP;
	int puerto = 7777;
	boolean nickEnviado = true;
	String nick = "";

	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	InputStreamReader isr;
	BufferedReader buff;
	PrintStream ps;

	public Cliente() {

		try {
			IP = InetAddress.getByName("localhost");
			sock = new Socket(IP, puerto); // se conectar

			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
			isr = new InputStreamReader(System.in);
			buff = new BufferedReader(isr);
			ps = new PrintStream(System.out);

			if (sock.isConnected() && nickEnviado) {
				ps.println("Ingresa tu ID: ");
				String nick = buff.readLine();
				dos.writeUTF(nick);
				nickEnviado = false;

				ps.println("Bienvenido " + nick);
			}

			ps.print("->");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Hilo que leer datos permanentemente y los envia por la red.
		Thread enviarMensajes = new Thread(new Runnable() {
			@Override
			public void run() {
				String msg = "";
				String clave = "1234567890123456";

				try {
					while (true && !msg.equalsIgnoreCase("/salir")) {
						msg = buff.readLine();
						byte[] iv = Cifrado.generarIV();
						String valorEncriptado = Cifrado.encriptar(clave, iv, msg);
						String encriptadoFinal = Base64.getEncoder().encodeToString(iv) + ":" + valorEncriptado;
						dos.writeUTF(encriptadoFinal);
						ps.print("->");
					}
					// Si el mensaje es "/salir", cerrar conexión localmente
					sock.close();
					ps.println("Te has desconectado del servidor.");
				} catch (IOException e) {
					Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
				} // try
			}// run
		}// runnable
		);// thread
		enviarMensajes.setName("enviar");
		enviarMensajes.start();

		// Hilo que leer datos permanentemente y los envia por la red.
		Thread recibirMensaje = new Thread(new Runnable() {
			@Override
			public void run() {
				String msg = "";
				String clave = "1234567890123456";
				byte[] iv = Cifrado.generarIV();
				while (true && !msg.equalsIgnoreCase("/salir")) {
					try {
						if (msg.equalsIgnoreCase("/listar")) {
							msg = dis.readUTF();
							ps.println("\t".concat(msg));

							ps.println("\t->");
						} else {
							msg = dis.readUTF();

							ps.println("\t".concat(msg));
						}

					} catch (IOException e) {
						Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
					}
				} // while

			}// run
		}// runnable
		);// thread
		recibirMensaje.setName("recibir");
		recibirMensaje.start();

	}
}

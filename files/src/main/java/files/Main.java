package files;

import java.io.PrintStream;

public class Main {

	public static void main(String[] args) {
Ficheros archivo = new Ficheros("","Mario",".txt");
		PrintStream ps = new PrintStream(System.out);
		//archivo.createFilePrintStream( archivo.getArchivo() );
		archivo.createFilePrinter( archivo.getArchivo() );
		archivo.datosArchivos();
		ps.println(archivo.leerCharChar(archivo.getArchivo()));
		ps.println( archivo.leerConReader(archivo.getArchivo()));


	}

}

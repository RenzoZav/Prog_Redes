package files;

public class Main {

	public static void main(String[] args) {
Ficheros archivo = new Ficheros("","Mario",".txt");
		
		//archivo.createFilePrintStream( archivo.getArchivo() );
		archivo.createFilePrinter( archivo.getArchivo() );
	}

}

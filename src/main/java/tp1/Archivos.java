package tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Archivos {
	PrintStream ps = new PrintStream(System.out);
	File archivo;

	public Archivos(String nombre, String extencion) {
		String datosArch = nombre.concat(extencion);
		archivo = new File(datosArch);
	}

	public File getArchivo() {
		return archivo;
	}

	public void guardar(File f, String dato) {
		try {
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileOutputStream fosArch = new FileOutputStream(f, true);
			ps = new PrintStream(fosArch);
			ps.println(dato);
			ps.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
}
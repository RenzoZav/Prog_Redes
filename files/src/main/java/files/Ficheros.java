package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Ficheros {
	
	private File archivo;
	
	
	public Ficheros() 
	{
		String ruta = "";	//  "c:\\User\\"
		String nombre = "mario";
		String extencion = ".txt";
		
		archivo = new File(ruta.concat(nombre.concat(extencion)));
	}
	
	
	
	public void createrFilePrintStream(File a) 
	{
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(a);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}


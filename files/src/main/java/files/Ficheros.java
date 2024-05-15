package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Ficheros {
	
	private File archivo;
	private PrintStream ps;
	
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
			ps = new PrintStream(fos,true); //mode append
			
			ps.println("Mati: Soy gay, lo confieso");
			ps.println("jsjsjsj");
			
			ps.flush();
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}




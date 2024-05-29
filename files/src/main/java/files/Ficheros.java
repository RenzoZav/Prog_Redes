package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ficheros {

	private File archivo;
	private PrintStream ps;

	public Ficheros(String rut, String name , String ext) {
		String ruta = rut; //  " C:\\User\\ "
		String nombre = name;
		String extension = ext;
		
		try {
			System.setErr( new PrintStream(
						   new FileOutputStream(
						   new File("Errores.log")) , true) 
						 );
		}catch(FileNotFoundException e) {
			Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
		}
		archivo = new File(ruta.concat(nombre.concat(extension)));
	}

	public File getArchivo()
	{
		return this.archivo;
	}
	
	public void createFilePrintStream(File a) {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(a ,true);
			ps = new PrintStream(fos); // mode append true = no sobreescribe

			ps.println("holaa mundo");
			ps.println("Chauu mundo");

			ps.flush();

		} catch (FileNotFoundException e) {
			Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
				}
		}
	}

	public void createFilePrinter(File a)
	{
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			if( !a.exists() )
			{
				a.createNewFile();
			}			
			
			fw = new FileWriter( a , true);
			pw = new PrintWriter(fw);
			
			pw.println("Otro MUNDO");		
		} catch (FileNotFoundException e ) {
			Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
		} catch (IOException e ){
			Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
		}
		finally {
			try {
				if( pw != null)
					pw.close();
				
				if( fw != null)
					fw.close();
			} catch (IOException e) {
				Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING, null, e);
			}
		}
	}
	
	public void createFileBuffered(File a)
	{
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(a , true);
			bw = new BufferedWriter(fw);
			
			bw.write("Explotemos al MUNDO");
			bw.newLine();
			bw.write("Mejor creemos un nuevo MUNDO");
			
			bw.flush();
		} catch (IOException e) {
			Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);
		}finally {
			try {
				if( fw != null )
					fw.close();
				
				if(bw != null)
					bw.close();
				
			} catch (IOException e) {
				Logger.getLogger( Ficheros.class.getName() ).log(Level.WARNING,null, e);	 			
			}
		}
		
	}
}


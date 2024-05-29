package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	
	public void datosArchivos() {
		try {
			ps= new PrintStream(System.out);
		ps.println("Name: "+archivo.getName());//Muestra nombre del archivo
		ps.println("Path: "+archivo.getPath());//Ruta
		ps.println("PathAbs"+archivo.getAbsolutePath());//Ruta absoluta
		ps.println("PathCannon:"+archivo.getCanonicalPath());
		ps.println("Contenedor del archivo: "+ archivo.getParentFile());
		ps.println("Parent"+ archivo.getParent());
		ps.println("Tamaño: "+archivo.getTotalSpace());
		ps.println("Ejecutable: "+archivo.canExecute());
		ps.println("Acceso de lectura: "+archivo.canRead());
		ps.println("Acceso de escritura"+ archivo.canWrite());
		ps.println("Esta oculta: "+ archivo.isHidden());
		ps.println("existe?: "+archivo.exists());
		ps.println("Es archivo?: "+archivo.isFile());
		ps.println("Es carpeta: "+archivo.isDirectory());
		
		
		//ps.println("Elimina cuando cierra el programa: " archivo.deleteOnExit());
	
		//ps.println("Renombre: " + archivo.renameTo("furro.txt"));
		//ps.println("Borrar Archvo"+archivo.delete());
		//ps.println("Crear archivos nuevos:"+ archivo.createNewFile());
		//ps.println("Crear Archivo: "+archivo.mkdir());
	}catch(IOException e){
		e.printStackTrace();
	}
		
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
			
			fw = new FileWriter( a , false);
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
	
	//Lectura de archivos
	public String leerCharChar(File a) {
		FileReader fr = null;
		String texto="";
		try {
			fr = new FileReader(a);
			int letra;
			
			while((letra = fr.read()) != -1)
			{
				texto +=(char)letra;
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return texto;
	}
	
	public String leerConReader(File a) {
		FileReader fr = null;

		BufferedReader br = null;

		String texto = "";

		try {

		fr = new FileReader(a);

		br = new BufferedReader(fr);

		String linea = "";

		try {

		while((linea = br.readLine()) != null) {

		texto += linea;

		}

		} catch (IOException e) {

		e.printStackTrace();

		}

		} catch (FileNotFoundException e) {

		e.printStackTrace();

		}finally {

		try {

		br.close();

		fr.close();

		} catch (IOException e) {

		e.printStackTrace();

		}

		}

		return texto;
}
}

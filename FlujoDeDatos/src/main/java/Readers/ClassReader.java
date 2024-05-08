package Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassReader {
	
	PrintStream ps;
	InputStreamReader isr;
	BufferedReader br;
	
	public ClassReader()
	{
		isr = new InputStreamReader( System.in );
		br = new BufferedReader( isr );
		
		ps = new PrintStream( System.out );
	}
	
	public String leer()
	{
		try {
			
			return br.readLine();	
		} catch (IOException e) {
			Logger.getLogger( ClassReader.class.getName() ).log(Level.WARNING , null , e ) ;
		}
		
		return null;
	}
	
}

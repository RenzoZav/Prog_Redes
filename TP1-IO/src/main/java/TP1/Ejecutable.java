package TP1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class Ejecutable {

	public static void main(String[] args){
		PrintStream ps = new PrintStream(System.out);
		
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(reader);
		
		int op = 6;	
		
		while(op != 0) {
			ps.println("-Ingreso de datos-");
			ps.println("1-Memoria Volatil");
			ps.println("2-Memoria No-Volatil");
			ps.println("0-Salir");
			
			String opS = entradaDeDatos();
			op = Integer.parseInt(opS);	

			switch (op) {
				case(1): volatil(); 
						break;
				case(2):
						NoVolatilG archivo = new NoVolatilG("", "CargadoLista", ".txt");
						archivo.createFilePrintStream(archivo.getArchivo());
						
						ps.println("A continuacion la division por 3");	

						NoVolatilG.lecturaReader(archivo.getArchivo());
						
						NoVolatilG resultados = new NoVolatilG("", "resultados", ".txt");
						NoVolatilG errores = new NoVolatilG("", "erroresCalculo", ".txt");
						NoVolatilG.operacion(NoVolatilG.lecturaReader(archivo.getArchivo()), resultados.getArchivo(), errores.getArchivo());

						break;

				case(0):
					break;
			}	
		}
		
	}
	
	
	public static void volatil() {
		PrintStream ps = new PrintStream(System.out);

		ArrayList <Integer> listaNums = new ArrayList();
		
		while(VolatilGuardado.verificarLista(listaNums) == false) {
			listaNums = VolatilGuardado.crearLista();
			VolatilGuardado.verificarLista(listaNums);
			
		}
		ps.println("Su lista:");
		for(int i = 0; i < listaNums.size(); i++) {
			ps.print(" "+listaNums.get(i) + " " + "\n");
			
		}
		ps.println("Anotacion de datos en memoria volatil en txts(Automatico):");
		NoVolatilG resultados = new NoVolatilG("", "resultados", ".txt");
		NoVolatilG errores = new NoVolatilG("", "erroresCalculo", ".txt");
		NoVolatilG.operacion(listaNums, resultados.getArchivo(), errores.getArchivo());
		
		
	}
	
	public static String entradaDeDatos() {
		
		String cadena = "";
		try {
			int Byte = -1;
			while(    (Byte = System.in.read())  != '\n'    ) 
			{
				if( Byte != 13 )
					cadena += (char)Byte;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cadena;
	}
	
}

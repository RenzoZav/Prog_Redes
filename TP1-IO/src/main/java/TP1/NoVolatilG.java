package TP1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoVolatilG {

	private File archivo;
	private PrintStream ps;
	
	public NoVolatilG(String rut, String name, String ext) {
		String ruta = rut; 
		String nombre = name; 
		String extension = ext;

		try {
			System.setErr(new PrintStream(new FileOutputStream(new File("Errores.log")), true));
		} catch (FileNotFoundException e) {
			Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
		}
		archivo = new File(ruta.concat(nombre.concat(extension)));
	}
	
	public File getArchivo() {
		return this.archivo;
	}
	
	public void createFilePrintStream(File a) {
		FileOutputStream fos = null;
		PrintStream psSalida = new PrintStream(System.out);
		ArrayList <String> numeros = new ArrayList();
		
		a.delete();

		try {
			fos = new FileOutputStream(a, true);
			ps = new PrintStream(fos); 
			psSalida.println("Escribe cinco numeros, dos deben ser 0 si o si:");
			
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(reader);
			
			int cantCeros = 0;
			String num = null;
			for(int i = 0; i < 5; i++) {
				try {
					
					num = br.readLine();
					if(num.isEmpty()) {
						psSalida.println("no se ingreso un numero");
						num = null;
						continue;
					}
					numeros.add(num);
	
				}catch(IOException e) {
					e.printStackTrace();
				}catch (NullPointerException e) {
				        if (num.isEmpty()) {
				            psSalida.println("No pusistes ningun numero");
				            num = null;
				        } else {
				            psSalida.println("El numero no es valido");
				            num = null;
				        }continue;
				}
				
				}
			for(int arraylI = 0; arraylI < numeros.size(); arraylI++) {
				if(Integer.parseInt(numeros.get(arraylI)) == 0) {
					cantCeros+= 1;
				}
			}
			if(cantCeros > 1 ) {
				for(int listado = 0; listado <  numeros.size(); listado++) {
					String numIndividual = numeros.get(listado);
					ps.println(numIndividual);
					ps.flush();
					
	//				psSalida.println("cantidad ceros" + cantCeros);  
					
					}
				}else {
					psSalida.println("No ingresastes la cantidad de ceros necesaria"); 
					
				}
				
		} catch (FileNotFoundException e) {
			Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
		}
		finally {
			if(ps != null) {
				ps.close();
			}
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
				}
		}
	}
	

	public static void operacion(ArrayList<Integer> listaNums, File resultados, File error) {
		FileOutputStream fosResultados = null;
		FileOutputStream fosErrores = null;
		PrintStream ps = new PrintStream(System.out);
		
		//No me pegue profe es para que no se me junte demasiado en el bloc T.T
		
		resultados.delete();
		error.delete();
		
		try {
			fosResultados = new FileOutputStream(resultados, true);
			PrintStream psBlocR = new PrintStream(fosResultados); 
			
			fosErrores = new FileOutputStream(error, true);
			PrintStream psBlocErrores= new PrintStream(fosErrores); 
			
			for (int i = 0; i < listaNums.size() - 1; i++) { 
			    int num1 = listaNums.get(i);
			    int num2 = listaNums.get(i + 1);
			    try {
			    	 if ((num2 - 3) != 0) { 
					        int operacion = num1 / (num2 - 3);
					        psBlocR.println("Aca es division por num2 - 3");
					        psBlocR.println(num1 + " / " + (num2 - 3) + " = " + operacion);
					    }else {
		                    throw new ArithmeticException("Division por cero");
		                }
			    }catch(ArithmeticException e) {
			    	psBlocErrores.println(num1 + "/" + (num2 - 3) +"= " + e.getMessage());
			    	psBlocErrores.flush();
			    }catch (NullPointerException e) {
	                psBlocErrores.println("faltan numeros " + (i + 1));
	                psBlocErrores.flush();
			    }
			}
			for(int i2 = 0; i2 < listaNums.size(); i2++) {
			    int num1 = listaNums.get(i2);
			    try {
					   int operacion = num1 / 3;
					   psBlocR.println("Aca es division por 3");
					   psBlocR.println(num1 + " / " + 3 + " = " + operacion);
					   
			    }catch (NullPointerException e) {
	                psBlocErrores.println("faltan numeros " + (i2 + 1));
	                psBlocErrores.flush();
			    }
			    psBlocR.flush();
			}
		}catch (FileNotFoundException e) {
			Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
		} finally {
			if (fosResultados != null) {
				try {
					fosResultados.close();
				} catch (IOException e) {
					Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
				}
		}if (fosErrores != null) {
            try {
                fosErrores.close();
            } catch (IOException e) {
                Logger.getLogger(NoVolatilG.class.getName()).log(Level.WARNING, null, e);
            }
        }
		}
		
		

	}
	public static ArrayList lecturaReader(File a) {
		ArrayList <Integer> listaNums = new ArrayList();
		
		PrintStream ps = new PrintStream(System.out);
		
		FileReader fr = null;
		BufferedReader br = null;
		String numS = "";
		
		try {
			fr = new FileReader(a);
			br = new BufferedReader(fr);

			String lin = "";
	        while ((lin = br.readLine()) != null) {
	            listaNums.add(Integer.parseInt(lin)); 
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listaNums;

		
	}
	
	
}

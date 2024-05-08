package Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Utils {

	static boolean continuar = true;
	static PrintStream ps = new PrintStream(System.out);

	
	static void menu() {
		while (continuar) {
			ps.println("Ingrese la opción a ejecutar:");
			ps.println("1- Ejercicio 1 (a)");
			ps.println("2- Ejercicio 1 (b)");
			ps.println("3- Ejercicio 2");
			ps.println("0- Salir");

			int opcion = Utils.leerOpcion();

			switch (opcion) {
			case 1:
				ps.println("Ejecutando Ejercicio 1 (a)");
				// Lógica del ejercicio 1 (1)
					// aca llamar al metodo que resuelve el ejercicio
					// el metodo esta en otra Class y recominedo que el metodo sea
					// de tipo Static, asi no hay que Instanciarlo (crear el objeto)
				break;
			case 2:
				ps.println("Ejecutando Ejercicio 1 (b)");
				// Lógica del ejercicio 1 (b)
				break;
			case 3:
				ps.println("Ejecutando Ejercicio 2");
				// Lógica del ejercicio 2
				break;
			case 0:
				ps.println("Saliendo del menú...");
				continuar = false;
				break;
			default:
				ps.println("Opción inválida. Por favor, ingrese una opción válida.");
				break;
			}
		}
	}

    static int leerOpcion() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            ps.println("Ingrese la opción:");
            try {
                String input = reader.readLine(); 
                opcion = Integer.parseInt(input); 

                
                if (opcion >= 0) {
                    entradaValida = true; 
                } else {
                    ps.println("Error: Por favor, ingrese un número entero positivo.");
                }
            } catch (NumberFormatException | IOException e) {
                ps.println("Error: Por favor, ingrese un número entero válido.");
            }
        }

        return opcion;
    }

}

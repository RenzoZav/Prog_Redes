package examen_1;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {
    private static Archivos Archivos;
    static PrintStream ps = new PrintStream(System.out);
    
   
    public static void main(String[] args) {
    	Archivos = new Archivos("datos.dat", "tuti-fruti.csv");
        try {
            if (Archivos.convcsv()) {
            	ps.println("Archivo convertido a CSV exitosamente. Puede empezar a usar el sistema.");
            } else {
            	ps.println("El archivo ya está convertido o no existe. Puede empezar a usar el sistema.");
            }
            Menuinfi();
        } catch (IOException e) {
        	ps.println("Error al convertir el archivo: " + e.getMessage());
        	Archivos.logError(e.getMessage());
        }
    }

    private static void Menuinfi() {
        while (true) {
        	ps.println("\nMENU:");
        	ps.println("1. Agregar datos nuevos");
        	ps.println("2. Eliminar datos");
        	ps.println("3. Mostrar datos existentes");
        	ps.println("4. Salir");
        	ps.println("Seleccione una opción: ");
            String option = Archivos.Leer();

            switch (option) {
                case "1":
                    AgregarDatos();
                    break;
                case "2":
                	BorrarDatos();
                    break;
                case "3":
                	MostrarDatos();
                    break;
                case "4":
                	ps.println("Saliendo del programa...");
                    return;
                default:
                	ps.println("Opción no válida, intente nuevamente.");
            }
        }
    }
   
static void AgregarDatos() {
    try {
        List<String> lineas = Archivos.LeerCsv();
        

        ps.println("Ingrese una letra para jugar la ronda: ");
        char letra = Archivos.Leer().toUpperCase().charAt(0);
        

        ps.println("Ingrese el color: ");
        String color = Archivos.Leer();
        ps.println("Ingrese el animal: ");
        String animal = Archivos.Leer();
        ps.println("Ingrese el objeto: ");
        String objeto = Archivos.Leer();
        ps.println("Ingrese el alimento: ");
        String alimento = Archivos.Leer();
        

        lineas.add(letra + ";" + color + ";" + animal + ";" + objeto + ";" + alimento);
        Archivos.Escribir(lineas);
        ps.println("Datos agregados exitosamente.");
    } catch (IOException e) {
    	ps.println("Error al agregar datos: " + e.getMessage());
        Archivos.logError(e.getMessage());
    }
}

static void BorrarDatos() {
    try {
        List<String> lineas = Archivos.LeerCsv();
        ps.println("Datos existentes:");
        for (int i = 0; i < lineas.size(); i++) {
        	ps.println((i + 1) + ". " + lineas.get(i).replace(';', ' '));
        }

        ps.println("Ingrese el número de renglón a eliminar: ");
        int numLinea = Integer.parseInt(Archivos.Leer()) - 1;
        if (numLinea < 0 || numLinea >= lineas.size()) {
        	ps.println("Número de renglón inválido.");
            return;
        }

        lineas.remove(numLinea);
        Archivos.Escribir(lineas);
        ps.println("Renglón eliminado exitosamente.");
    } catch (IOException e) {
    	ps.println("Error al eliminar datos: " + e.getMessage());
        Archivos.logError(e.getMessage());
    }
}

static void MostrarDatos() {
    try {
        List<String> lineas = Archivos.LeerCsv();
        lineas.sort(Comparator.naturalOrder());

        ps.println(String.format("%-10s%-10s%-10s%-10s%-10s", "Letra", "Color", "Animal", "Objeto", "Alimento"));
        for (String linea : lineas) {
            String[] palabra = linea.split(";");
            if (palabra.length > 0) {
            	ps.println(linea);
                continue;
            }
            ps.println(String.format(/*"%-10s%-10s%-10s%-10s%-10s",*/ palabra[0], palabra[1], palabra[2], palabra[3], palabra[4]));
        }
    } catch (IOException e) {
    	ps.println("Error al mostrar datos: " + e.getMessage());
        Archivos.logError(e.getMessage());
    }
}
}

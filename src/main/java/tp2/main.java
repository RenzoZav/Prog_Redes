package tp2;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\033[1;34m-------------------------");
            System.out.println("   Menú de Inventario");
            System.out.println("-------------------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Salir");
            System.out.print("\033[0mSeleccione una opción: ");
            
            String option = Convertir.leer(sc);

            switch (option) {
                case "1":
                    Producto producto = Producto.crearProducto(sc);
                    File.añadirProductoArchivo("Inventario.dat", producto);
                    System.out.println("\033[1;32mProducto agregado exitosamente.\033[0m");
                    break;
                case "2":
                    File.mostrarProductos("Inventario.dat");
                    break;
                case "3":
                    exit = true;
                    System.out.println("\033[1;31mSaliendo...\033[0m");
                    break;
                default:
                    System.out.println("\033[1;31mOpción inválida, intente nuevamente.\033[0m");
            }
        }
        sc.close();
    }

	}



package tp2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {
    public static void añadirProductoArchivo(String nombreArchivo, Producto Producto) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true)) {
        	fw.write(Producto.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static List<Producto> leerProductosArhivo(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(";");
                if (data.length == 4) {
                    String nombre = data[0];
                    float precioCompra = Float.parseFloat(data[1]);
                    float precioVenta = Float.parseFloat(data[2]);
                    int stock = Integer.parseInt(data[3]);
                    productos.add(new Producto(nombre, precioCompra, precioVenta, stock));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }

    public static void mostrarProductos(String nombreArchivo) {
        List<Producto> productos = leerProductosArhivo(nombreArchivo);
        if (productos.isEmpty()) {
            System.out.println("\033[1;33mNo hay productos en el inventario.\033[0m");
        } else {
            System.out.println("\033[1;34m--- Inventario ---\033[0m");
            for (Producto producto : productos) {
                producto.mostrarInventario();
            }
        }
    }
}

package tp2;

import java.util.Scanner;

public class Producto {
    private String nombre;
    private float precioCompra;
    private float precioVenta;
    private int stock;

    public Producto(String nombre, float precioCompra, float precioVenta, int stock) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public static Producto crearProducto(Scanner sc) {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = Convertir.leer(sc);

        System.out.print("Ingrese el precio de compra: ");
        float precioCompra = Convertir.convertirAFloat(Convertir.leer(sc));

        System.out.print("Ingrese el precio de venta: ");
        float precioVenta = Convertir.convertirAFloat(Convertir.leer(sc));

        System.out.print("Ingrese el stock: ");
        int stock = Convertir.converirAInt(Convertir.leer(sc));

        return new Producto(nombre, precioCompra, precioVenta, stock);
    }

   @Override
    public String toString() {
        return nombre + ";" + precioCompra + ";" + precioVenta + ";" + stock;
    }

    public void mostrarInventario() {
        System.out.printf("Nombre: %s\tCompra: %.2f\tVenta: %.2f\tStock: %d\n", nombre, precioCompra, precioVenta, stock);
    }
}


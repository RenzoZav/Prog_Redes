package tp2;

import java.util.Scanner;

public class Convertir {
    public static String leer(Scanner sc) {
        return sc.nextLine();
    }

    public static int converirAInt(String text) {
        return Integer.parseInt(text);
    }

    public static float convertirAFloat(String text) {
        return Float.parseFloat(text);
    }
}


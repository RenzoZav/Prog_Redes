package tp1;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        Archivos a = new Archivos("datos", ".txt");
        Archivos resultados = new Archivos("resultados", ".txt");
        Archivos error = new Archivos("error", ".txt");
        File f = new File();

        ArrayList<Integer> array = almacenarNumeros(f, error);
        almacenarNumerosFile(f, a, array);
        
        cuenta(array, resultados, error, f);
    }

    public static ArrayList<Integer> almacenarNumeros(File f, Archivos error) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            try {
                int aux = Integer.parseInt(f.entradaDeDatos());
                array.add(aux);
            } catch (NumberFormatException e) {
                error.guardar(error.getArchivo(), "Error de formato en la entrada de datos: " + e.getMessage());
            }
        }
        return array;
    }

    public static void almacenarNumerosFile(File f, Archivos a, ArrayList<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            a.guardar(a.getArchivo(), String.valueOf(lista.get(i)));
        }
    }

    public static void cuenta(ArrayList<Integer> lista, Archivos resultados, Archivos error, File f) {
        for (int i = 0; i < lista.size() - 1; i++) {
            try {
                int num1 = lista.get(i);
                int num2 = lista.get(i + 1) - 3;

                if (num2 == 0) {
                    throw new ArithmeticException("Error: División por cero al intentar dividir " + num1 + " entre " + num2);
                }

                double aux = (double) num1 / num2;
                String str = num1 + " / " + num2 + " = " + aux;
                resultados.guardar(resultados.getArchivo(), str);
            } catch (ArithmeticException | NullPointerException | NumberFormatException e) {
                error.guardar(error.getArchivo(), "Excepción encontrada: " + e.getMessage());
            }
        }
    }
}

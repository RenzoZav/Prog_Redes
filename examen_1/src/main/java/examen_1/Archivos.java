	package examen_1;

	import java.io.*;
	import java.nio.file.*;
	import java.util.*;

	public class Archivos {
	    private String nomOri;//originalFileName
	    private String nomCsv;//csvFileName

	    public Archivos(String nomOri, String nomCsv) {
	        this.nomOri = nomOri;
	        this.nomCsv = nomCsv;
	    }

	    public boolean convcsv() throws IOException {
	        File originalFile = new File(nomOri);
	        File csvFile = new File(nomCsv);

	        if (!originalFile.exists() || csvFile.exists()) {
	            return false;
	        }

	        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
	             BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {

	            String linea;
	            while ((linea = br.readLine()) != null) {
	                if (linea.isEmpty()) continue;
	                char letra = Character.toUpperCase(linea.charAt(1));
	                bw.write(letra + linea.substring(1).replace('.', ';') + "\n");
	            }
	        }

	        Files.delete(Paths.get(nomOri));
	        return true;
	    }

	    public void logError(String errorMessage) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ERRORES.log", true))) {
	        bw.write(errorMessage + "\n");
	        } catch (IOException e) {
	            System.err.println("No se pudo escribir en el archivo de errores: " + e.getMessage());
	        }
	    }

	    public List<String> LeerCsv() throws IOException {
	        List<String> lines = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(nomCsv))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                lines.add(line);
	            }
	        }
	        return lines;
	    }

	    public void Escribir(List<String> lines) throws IOException {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomCsv))) {
	            for (String line : lines) {
	            bw.write(line + "\n");
	            }
	        }
	    }

	    public String Leer() {
	        String input = "";
	        try {
	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            input = br.readLine();
	        } catch (IOException e) {
	            System.err.println("Error al leer la entrada del usuario: " + e.getMessage());
	        }
	        return input;
	}
	    }

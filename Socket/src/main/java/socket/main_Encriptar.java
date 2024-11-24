package socket;

public class main_Encriptar {

	public static void main(String[] args) {
		String clave = "1234567890123456"; // Clave de 16 bytes (128 bits)
		byte[] iv = Cifrado.generarIV(); // IV aleatorio generado
		String valorOriginal = "Texto a encriptar";
		
		byte[] iv2 = Cifrado.generarIV(); // IV aleatorio generado

		String valorEncriptado = Cifrado.encriptar(clave, iv, valorOriginal);
		System.out.println("Texto encriptado: " + valorEncriptado);

		String valorDesencriptado = Cifrado.decriptar(clave, iv2, valorEncriptado);
		System.out.println("Texto desencriptado: " + valorDesencriptado);
	}

}

package Cajero_Supermercado;

import java.util.ArrayList;

//herada todo de la clase "Thread"
public class CajaHilo extends Thread {
	private int numCaja;
	private String nombreCajero;
	private long tiempo;
	private ArrayList<Persona> filaCliente;
	
	public CajaHilo(int nro, String name, long ti,ArrayList<Persona> fiCli ) {
		this.numCaja=nro;
		this.nombreCajero=name;
		this.tiempo=ti;
		this.filaCliente=fiCli;
	}
	
	
	public int getNumCaja() {
		return numCaja;
	}

	public void setNumCaja(int numCaja) {
		this.numCaja = numCaja;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	public ArrayList<Persona> getFilaCliente() {
		return filaCliente;
	}

	public void setFilaCliente(ArrayList<Persona> filaCliente) {
		this.filaCliente = filaCliente;
	}

	//sobreescrir       todo lo que queremos que haga el hilo
	//va dentro del run
	@Override
	public void run() {
		
		
	}
}

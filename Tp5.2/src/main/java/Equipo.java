import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Equipo {
	private final String nombre;
	private final String color;
	private final List<Atleta> atletas = new ArrayList<>();
	private int indiceActual = 0;
	private final Arbitro arbitro;
	private final Lock lock = new ReentrantLock();
	private long tiempoTotal = 0;
		
	public Equipo(String nombre, String color, Arbitro arbitro) {
		this.nombre = nombre;
		this.color = color;
		this.arbitro = arbitro;
		crearAtletas();
	}

	private void crearAtletas() {
		String[] actividades = { "Remando", "Corriendo", "Nadando", "en Bicicleta" };
		for (String actividad : actividades) {
			Atleta atleta = new Atleta(this, actividad);
			atletas.add(atleta);
		}
	}

	public boolean pasarPosta(Atleta atletaActual) {
		lock.lock();
		try {
			if (atletas.get(indiceActual) == atletaActual) {
				int siguienteIndice = indiceActual + 1;

				if (siguienteIndice < atletas.size()) {
					Atleta siguienteAtleta = atletas.get(siguienteIndice);
					siguienteAtleta.recibirPosta();
					indiceActual = siguienteIndice;
					return true;
				}
			}
			return false;
		} finally {
			lock.unlock();
		}
	}
	
	public void actualizarTiempoFinal(long tiempo) {
		lock.lock();
		try {
			this.tiempoTotal += tiempo;
		}finally {
			lock.unlock();
		}
	}
	public long getTiempoTotal(){
		return tiempoTotal;
	}
	public String getNombre() {
		return nombre;
	}

	public String getColor() {
		return color;
	}

	public List<Atleta> getAtletas() {
		return atletas;
	}

	public Arbitro getArbitro() {
		return arbitro;
	}
}

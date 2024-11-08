import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Arbitro {
    private static final Logger logger = Logger.getLogger(Arbitro.class.getName());
    private long tiempoInicio;

    public static void main(String[] args) throws InterruptedException {
        Arbitro arbitro = new Arbitro();

        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo A", Colores.ROJO, arbitro));
        equipos.add(new Equipo("Equipo B", Colores.VERDE, arbitro));
        equipos.add(new Equipo("Equipo C", Colores.AZUL, arbitro));
        equipos.add(new Equipo("Equipo D", Colores.AMARILLO, arbitro));

        arbitro.iniciarCarrera(equipos);
    }

    public void iniciarCarrera(List<Equipo> equipos) throws InterruptedException {
        tiempoInicio = System.currentTimeMillis();
        //logger.info("¡Comienza la carrera!");
        System.out.println(Colores.RESET+"¡Comienza la carrera!");

        List<Thread> hilosAtletas = new ArrayList<>();

        for (Equipo equipo : equipos) {
            List<Atleta> atletas = equipo.getAtletas();
            for (int i = 0; i < atletas.size(); i++) {
                Atleta atleta = atletas.get(i);
                Thread hilo = new Thread(atleta);
                hilo.setName(equipo.getNombre() + " - Atleta " + (i + 1));
                hilosAtletas.add(hilo);
            }

            if (!atletas.isEmpty()) {
                atletas.get(0).recibirPosta();
            }
        }

        for (Thread hilo : hilosAtletas) {
            hilo.start();
        }

        for (Thread hilo : hilosAtletas) {
            hilo.join();
        }

        long tiempoFin = System.currentTimeMillis();
        long duracion = tiempoFin - tiempoInicio;

        //logger.info("¡La carrera ha terminado!");
        System.out.println(Colores.RESET+"¡La carrera ha terminado!");
        mostrarPodio(equipos);

    }
    private void mostrarPodio(List<Equipo> equipos) {
    	equipos.sort((equipo1, equipo2)-> Long.compare(equipo1.getTiempoTotal(), equipo2.getTiempoTotal()));
    	//logger.info("\nResultados de la carrera:");
    	System.out.println("Resultados de la carrera:");
    	for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            String puesto = "";
            switch (i) {
                case 0:
                	puesto = "1er lugar";
                	break;
                case 1:
                	puesto = "2do lugar";
                	break;
                case 2:
                	puesto = "3er lugar";
                	break;
                default:
                	puesto = (i + 1) + "er lugar";
                	break;
            }
            //logger.info(String.format("%s: %s - Tiempo total: %d ms", puesto, equipo.getNombre(), equipo.getTiempoTotal()));
            System.out.println(String.format("%s%s: %s - Tiempo total: %d ms",equipo.getColor(),  puesto, equipo.getNombre(), equipo.getTiempoTotal()));
        }
    }
    
    public void registrarProgreso(Equipo equipo, Atleta atleta, long tiempoInicioAtleta) {
    	long tiempoRecibido = atleta.getTiempoActividad();
        
        System.out.println(String.format("%s%s esta %s durante %s mes.",
        		equipo.getColor(),Thread.currentThread().getName(),
        		 atleta.getActividad(),
        	 atleta.getTiempoActividad()));
        
        equipo.actualizarTiempoFinal(tiempoRecibido);
	        
        }
    
    

}

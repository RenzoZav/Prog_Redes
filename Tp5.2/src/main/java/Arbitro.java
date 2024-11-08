import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
        logger.info("¡Comienza la carrera!");

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

        logger.info("¡La carrera ha terminado!");

    }

    public void registrarProgreso(Equipo equipo, Atleta atleta, long tiempoInicioAtleta) {
        long tiempoRecibido = System.currentTimeMillis() - tiempoInicioAtleta;
        /*logger.info(String.format("%sEquipo %s - %s: %s - Tiempo acumulado: %d ms%s",
                equipo.getColor(), equipo.getNombre(), Thread.currentThread().getName(),
                atleta.getActividad(), tiempoRecibido, Colores.RESET));
        */
        /*System.out.println(String.format("%s%s - %s: %s - Tiempo acumulado: %d ms%s",
                equipo.getColor(), equipo.getNombre(), Thread.currentThread().getName(),
                atleta.getActividad(), tiempoRecibido, Colores.RESET));
    */
        System.out.println(String.format("%s - %s: %s - Tiempo acumulado: %d ms%s",
                 equipo.getNombre(), Thread.currentThread().getName(),
                atleta.getActividad(), tiempoRecibido, Colores.RESET));
    }

}

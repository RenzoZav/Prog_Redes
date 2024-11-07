import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class Arbitro {
    private static final Logger logger = Logger.getLogger(Arbitro.class.getName());

    public static void main(String[] args) throws InterruptedException {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo A"));
        equipos.add(new Equipo("Equipo B"));
        equipos.add(new Equipo("Equipo C"));
        equipos.add(new Equipo("Equipo D"));

        long tiempoInicio = System.currentTimeMillis();
        logger.info("¡Comienza la carrera!");

       
}
    }

import java.util.Random;

public class Atleta extends Thread {
    private int tiempoRemo;
    private int tiempoCorrer;
    private int tiempoNadar;
    private int tiempoBicicleta;
    private Equipo equipo;
    private boolean tienePosta = false;
    private static final Random random = new Random();

    public Atleta(Equipo equipo) {
        this.equipo = equipo;
        this.tiempoRemo = generarTiempo();
        this.tiempoCorrer = generarTiempo();
        this.tiempoNadar = generarTiempo();
        this.tiempoBicicleta = generarTiempo();
    }

    private int generarTiempo() {
        return 300 + random.nextInt(2701);
    }

    public void recibirPosta() {
        this.tienePosta = true;
    }

    public void pasarPosta(Atleta siguienteAtleta) {
        this.tienePosta = false;
        siguienteAtleta.recibirPosta();
    }

    @Override
    public void run() {
        try {
            if (tienePosta) {
                System.out.printf("%sEquipo %s - %s: Remo (Fuerza) - Tiempo: %dms%s\n", equipo.getNombre(), getName(), tiempoRemo);
                Thread.sleep(tiempoRemo);

                System.out.printf("%sEquipo %s - %s: Correr (Velocidad) - Tiempo: %dms%s\n", equipo.getNombre(), getName(), tiempoCorrer);
                Thread.sleep(tiempoCorrer);

                System.out.printf("%sEquipo %s - %s: Nadar (Técnica) - Tiempo: %dms%s\n",equipo.getNombre(), getName(), tiempoNadar);
                Thread.sleep(tiempoNadar);

                System.out.printf("%sEquipo %s - %s: Bicicleta (Resistencia) - Tiempo: %dms%s\n",equipo.getNombre(), getName(), tiempoBicicleta);
                Thread.sleep(tiempoBicicleta);

                
            }
        } catch (InterruptedException e) {
            System.out.printf("Atleta %s del equipo %s fue interrumpido.\n", getName(), equipo.getNombre());
        }
    }

    public int getTiempoTotal() {
        return tiempoRemo + tiempoCorrer + tiempoNadar + tiempoBicicleta;
    }
}
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Atleta implements Runnable {
    private final int tiempoActividad;
    private final String actividad;
    private final Equipo equipo;
    private boolean tienePosta = false;
    private static final Random random = new Random();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Atleta(Equipo equipo, String actividad) {
        this.equipo = equipo;
        this.actividad = actividad;
        this.tiempoActividad = 300 + random.nextInt(2701); 
    }

    public void recibirPosta() {
        lock.lock();
        try {
            this.tienePosta = true;
            condition.signal();
            //System.out.println(Thread.currentThread().getName() + " ha recibido la posta.");
        } finally {
            lock.unlock();
        }
    }


    public void pasarPosta(Atleta siguienteAtleta) {
        lock.lock();
        try {
            this.tienePosta = false;
            siguienteAtleta.recibirPosta();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (!tienePosta) {
                condition.await();
            }

            if (Thread.currentThread().isInterrupted()) return;

            long tiempoInicioAtleta = System.currentTimeMillis();
            
            System.out.println(Thread.currentThread().getName() + " está realizando la actividad durante " + tiempoActividad + " ms.");
            Thread.sleep(tiempoActividad);
            
            long tiempoRecibido = System.currentTimeMillis() - tiempoInicioAtleta;
            equipo.getArbitro().registrarProgreso(equipo, this, tiempoInicioAtleta);

            equipo.pasarPosta(this);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }


    public int getTiempoActividad() {
        return tiempoActividad;
    }

    public String getActividad() {
        return actividad;
    }
}

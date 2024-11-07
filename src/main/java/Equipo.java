import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    
    private List<Atleta> atletas = new ArrayList<>();
    private int indiceActual = 0;

    public Equipo(String nombre) {
        this.nombre = nombre;
        for (int i = 0; i < 4; i++) {
            Atleta atleta = new Atleta(this);
            atleta.setName("Atleta" + (i + 1));
            atletas.add(atleta);
        }
    }

    public String getNombre() {
        return nombre;
    }


    public List<Atleta> getAtletas() {
        return atletas;
    }

   

   
}

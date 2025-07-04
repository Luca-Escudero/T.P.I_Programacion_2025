package modelos;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String pais;
    private Entrenador entrenador;
    private int idCampeonato;
    
    public Equipo(int idEquipo, String nombre, String pais, Entrenador entrenador, int idCampeonato) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.pais = pais;
        this.entrenador = entrenador;
        this.idCampeonato = idCampeonato;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", entrenador=" + entrenador +
                ", idCampeonato=" + idCampeonato +
                '}';
    }
    

}

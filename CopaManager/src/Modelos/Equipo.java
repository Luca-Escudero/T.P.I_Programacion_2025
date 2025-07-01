package modelos;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String pais;
    private Entrenador entrenador;
    
    public Equipo(int idEquipo, String nombre, String pais, Entrenador entrenador) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.pais = pais;
        this.entrenador = entrenador;
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

    

    

}

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int idCampeonato;
    private String nombre;
    private int anio;
    private String estado;
    private List<Equipo> equipos;
    private List<Partido> partidos;

    public Campeonato(int id, String nombre, int anio ) {
        this.idCampeonato = id;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = "En curso"; 
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void addEquipo(Equipo equipo) {
        this.equipos.add(equipo);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void addPartido(Partido partido) {
        this.partidos.add(partido);
    }

    public void iniciarCampeonato() {
        this.estado = "En curso";
    }

    public void finalizarCampeonato() {
        this.estado = "Finalizado";
    }

    @Override
    public String toString() {
        return "Campeonato{" +
                "idCampeonato=" + idCampeonato +
                ", nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", estado='" + estado + '\'' +
                '}';
    }
}

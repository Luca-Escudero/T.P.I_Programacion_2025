package modelos;

import java.util.Date;
import java.text.SimpleDateFormat; 

public class Partido {
    private int idPartido;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Date fechaHora;
    private Resultado resultado;
    private int idCampeonato;

    public Partido(int id, Equipo local, Equipo visitante, Date fechaHora, int idCampeonato) {
        this.idPartido = id;
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.fechaHora = fechaHora;
        this.idCampeonato = idCampeonato;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada = (fechaHora != null) ? formatter.format(fechaHora) : "Fecha no definida";

       String resultadoStr = "Sin resultado";
        if (resultado != null) {
            resultadoStr = resultado.getGolesLocal() + " - " + resultado.getGolesVisitante();
        }

        return String.format("ID: %d | %s vs %s | Fecha: %s | Resultado: %s | Campeonato ID: %d",
                             idPartido,
                             equipoLocal.getNombre(),
                             equipoVisitante.getNombre(),
                             fechaFormateada,
                             resultadoStr,
                             idCampeonato);
    }

    public String getEstado() {
        if (resultado != null) {
            return "Finalizado";
        }
        Date now = new Date();
        if (fechaHora != null && fechaHora.after(now)) {
            return "Pendiente";
        }
        return "En curso";
    }

    public String getFecha() {
        if (fechaHora == null) {
            return "Fecha no definida";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(fechaHora);
    }
}

package modelos;

import java.sql.Date;

public class Partido {
    private int idPartido;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Date fechaHora;
    private Resultado resultado;

    public Partido(int idPartido, Equipo equipoLocal, Equipo equipoVisitante, Date fechaHora, Resultado resultado) {
        this.idPartido = idPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fechaHora = fechaHora;
        this.resultado = resultado;
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
    
    

}

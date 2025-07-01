package modelos;

public class RegistroPosicion {
    private Equipo equipo;
    private DatosPosicion datos;

    public RegistroPosicion(Equipo equipo, DatosPosicion datos) {
        this.equipo = equipo;
        this.datos = datos;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public DatosPosicion getDatos() {
        return datos;
    }

    public void setDatos(DatosPosicion datos) {
        this.datos = datos;
    }
    

    

}

package modelos;

public class Resultado {
    private int golesLocal;
    private int golesVisitante;
    
    public Resultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    

}

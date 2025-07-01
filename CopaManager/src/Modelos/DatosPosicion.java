package modelos;

public class DatosPosicion {
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;

    public DatosPosicion(int puntos, int partidosJugados, int partidosGanados, int partidosEmpatados,
            int partidosPerdidos, int golesAFavor, int golesEnContra) {
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados = partidosEmpatados;
        this.partidosPerdidos = partidosPerdidos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }
    

    

}

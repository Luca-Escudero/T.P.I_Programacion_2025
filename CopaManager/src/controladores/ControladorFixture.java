package controladores;

import consultasSQL.*;
import modelos.Campeonato;
import modelos.Partido;
import vistas.*;

import java.util.List;


public class ControladorFixture {
    private VistaMenu vistaMenu;
    private ControladorBaseDeDatos controladorBaseDeDatos;

    public ControladorFixture(VistaMenu vistaMenu, ControladorBaseDeDatos controladorBaseDeDatos) {
        this.vistaMenu = vistaMenu;
        this.controladorBaseDeDatos = controladorBaseDeDatos;
    }

    public void verFixture() {
        List<Campeonato> campeonatos = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
        Campeonato campeonato = vistaMenu.seleccionarCampeonato(campeonatos);

        if (campeonato != null) {
            List<Partido> partidos = controladorBaseDeDatos.obtenerTodosLosPartidos(campeonato.getIdCampeonato());
            if (partidos.isEmpty()) {
                vistaMenu.mostrarMensaje("No hay partidos para este campeonato.");
                return;
            }
            for (Partido partido : partidos) {
                String estado = partido.getEstado();
                Integer golesLocal = partido.getResultado() != null ? partido.getResultado().getGolesLocal() : null;
                Integer golesVisitante = partido.getResultado() != null ? partido.getResultado().getGolesVisitante() : null;

                String resultado = (golesLocal != null && golesVisitante != null)
                        ? golesLocal + " - " + golesVisitante
                        : "Sin resultado";

                vistaMenu.mostrarMensaje(
                        "Partido: " + partido.getEquipoLocal().getNombre() + " vs " + partido.getEquipoVisitante().getNombre() +
                                " | Fecha: " + partido.getFecha() +
                                " | Estado: " + estado +
                                " | Resultado: " + resultado
                );
            }
        } else {
            vistaMenu.mostrarMensaje("No se seleccionó ningún campeonato.");
        }
    }
}

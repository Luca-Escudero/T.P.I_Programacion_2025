package controlador;

import modelo.Campeonato;
import vista.VistaCampeonato;
import vista.VistaMenu;

import java.util.List;

import consultaSQL.ControladorBaseDeDatos;

public class ControladorCampeonato {
    private VistaCampeonato vistaCampeonato;
    private ControladorBaseDeDatos controladorBaseDeDatos;
    private VistaMenu vistaMenu;

    public ControladorCampeonato(VistaMenu vistaMenu, ControladorBaseDeDatos controladorBaseDeDatos) {
        this.vistaCampeonato = new VistaCampeonato();
        this.controladorBaseDeDatos = controladorBaseDeDatos;
        this.vistaMenu = new VistaMenu();
    }

    public void crearNuevoCampeonato() {
        String nombre = vistaCampeonato.obtenerNombreCampeonato();
        int anio = vistaCampeonato.obtenerAnioCampeonato();

        Campeonato campeonato = new Campeonato(0, nombre, anio);

        controladorBaseDeDatos.guardarCampeonato(campeonato);
        vistaMenu.mostrarMensaje("Campeonato creado exitosamente.");
    }

    public void finalizarCampeonato() {
        List<Campeonato> campeonatos = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
        if (campeonatos.isEmpty()) {
            vistaMenu.mostrarMensaje("No hay campeonatos para finalizar.");
            return;
        }

        vistaCampeonato.mostrarCampeonatos(campeonatos);
        int idCampeonato = vistaCampeonato.obtenerIdCampeonatoAFinalizar();
        Campeonato campeonato = controladorBaseDeDatos.obtenerCampeonatoPorId(idCampeonato);

        if (campeonato != null) {
            if (campeonato.getEstado().equals("Finalizado")) {
                vistaMenu.mostrarMensaje("El campeonato " + campeonato.getNombre() + " ya está finalizado.");
            } else {
                campeonato.finalizarCampeonato();
                controladorBaseDeDatos.actualizarCampeonato(campeonato);
                vistaMenu.mostrarMensaje("Campeonato '" + campeonato.getNombre() + "' finalizado exitosamente.");
            }
        } else {
            vistaMenu.mostrarMensaje("Campeonato no encontrado.");
        }
    }

    public void mostrarMenuCampeonatos() {
        boolean salir = false;
        while (!salir) {
            int opcion = vistaCampeonato.mostrarMenuCampeonatos();
            switch (opcion) {
                case 1:
                    crearNuevoCampeonato();
                    break;
                case 2:
                    List<Campeonato> campeonatos = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
                    vistaCampeonato.mostrarCampeonatos(campeonatos);
                    break;
                case 3:
                    finalizarCampeonato();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    vistaMenu.mostrarMensaje("Opción no válida. Intente nuevamente.");
            }
        }
    }
}

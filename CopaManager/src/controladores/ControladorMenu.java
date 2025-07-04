package controladores;

import consultasSQL.*;
import vistas.VistaMenu;

public class ControladorMenu {
    private VistaMenu vistaMenu;
    private ControladorBaseDeDatos controladorBaseDeDatos;
    private ControladorCampeonato controladorCampeonato;
    private ControladorEquipo controladorEquipo;
    private ControladorPartido controladorPartido;
    private ControladorEntrenador controladorEntrenador;
    private ControladorFixture controladorFixture;

    public ControladorMenu() {
        this.vistaMenu = new VistaMenu();
        this.controladorBaseDeDatos = new ControladorBaseDeDatos();
        this.controladorCampeonato = new ControladorCampeonato(vistaMenu, controladorBaseDeDatos);
        this.controladorEntrenador = new ControladorEntrenador(vistaMenu, controladorBaseDeDatos);
        this.controladorEquipo = new ControladorEquipo(vistaMenu, controladorBaseDeDatos, controladorEntrenador);
        this.controladorPartido = new ControladorPartido(vistaMenu, controladorBaseDeDatos);
        this.controladorFixture = new ControladorFixture(vistaMenu, controladorBaseDeDatos);
    }

    public void iniciarAplicacion() {
        int num = 1;
        while (num == 1) {
            vistaMenu.mostrarMenuPrincipal();
            int opcion = vistaMenu.obtenerOpcionMenu();
            switch (opcion) {
                case 1:
                    gestionarCampeonatos();
                    break;
                case 2:
                    gestionarEquipos();
                    break;
                case 3:
                    gestionarEntrenadores();
                    break;
                case 4:
                    gestionarPartidos();
                    break;
                case 5:
                    controladorFixture.verFixture();
                    break;
                case 0:
                    num = 0;
                    break;
                default:
                    vistaMenu.mostrarError("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarCampeonatos() {
        controladorCampeonato.mostrarMenuCampeonatos();
    }

    private void gestionarEquipos() {
        controladorEquipo.mostrarMenuEquipos();
    }

    private void gestionarEntrenadores() {
        controladorEntrenador.mostrarMenuEntrenadores();
    }

    private void gestionarPartidos() {
        controladorPartido.mostrarMenuPartidos();
    }
}


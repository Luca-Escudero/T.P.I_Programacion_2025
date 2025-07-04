package controladores;



import consultasSQL.*;
import modelos.*;
import vistas.*;


import java.util.List;
import java.util.Map;

public class ControladorEquipo {
    private VistaEquipo vistaEquipo;
    private VistaMenu vistaMenu;
    private ControladorBaseDeDatos controladorBaseDeDatos;
    private ControladorEntrenador controladorEntrenador;
    private VistaEntrenador vistaEntrenador;

    public ControladorEquipo(VistaMenu vistaMenu, ControladorBaseDeDatos controladorBaseDeDatos, ControladorEntrenador controladorEntrenador) {
        this.vistaEquipo = new VistaEquipo();
        this.vistaEntrenador = new VistaEntrenador();
        this.vistaMenu = vistaMenu;
        this.controladorBaseDeDatos = controladorBaseDeDatos;
        this.controladorEntrenador = controladorEntrenador;
    }

    public void registrarEquipo(int idCampeonato) {
        Map<String, String> datosInicialesEquipo = vistaEquipo.obtenerDatosInicialesNuevoEquipo();
        String nombre = datosInicialesEquipo.get("nombre");
        String pais = datosInicialesEquipo.get("pais");

        controladorEntrenador.verTodosLosEntrenadores();

        int idEntrenador = 0;
        try {
            idEntrenador = vistaEntrenador.obtenerIdEntrenador();
        } catch (NumberFormatException e) {
            vistaMenu.mostrarError("ID de Entrenador no válido. Por favor, ingrese un número.");
            return;
        }

        Entrenador entrenador = controladorBaseDeDatos.obtenerEntrenadorPorId(idEntrenador);
        if (entrenador == null) {
            vistaMenu.mostrarError("Entrenador con ID " + idEntrenador + " no encontrado. Por favor, registre el entrenador primero.");
            return;
        }
        Equipo nuevoEquipo = new Equipo(0, nombre, pais, entrenador, idCampeonato);
        controladorBaseDeDatos.guardarEquipo(nuevoEquipo);
        vistaMenu.mostrarMensaje("Equipo registrado exitosamente.");
    }

    public void listarEquipos(int idCampeonato) {
        List<Equipo> equipos = controladorBaseDeDatos.obtenerTodosLosEquipos(idCampeonato);
        vistaEquipo.mostrarListaEquipos(equipos);
    }

    public void mostrarMenuEquipos() {
        int salir = 0;
        while (salir == 0) {
            vistaEquipo.mostrarMenuEquipos();
            int opcion = vistaMenu.obtenerOpcionMenu();
            switch (opcion) {
                case 1:
                    List<Campeonato> campeonatos1 = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
                    Campeonato seleccionado1 = vistaMenu.seleccionarCampeonato(campeonatos1);
                    if (seleccionado1 != null)
                        registrarEquipo(seleccionado1.getIdCampeonato());
                    else
                        vistaMenu.mostrarError("Debe seleccionar un campeonato para registrar un equipo.");
                    break;
                case 2:
                    List<Campeonato> campeonatos2 = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
                    Campeonato seleccionado2 = vistaMenu.seleccionarCampeonato(campeonatos2);
                    if (seleccionado2 != null)
                        listarEquipos(seleccionado2.getIdCampeonato());
                    else
                        vistaMenu.mostrarError("Debe seleccionar un campeonato para ver sus equipos.");
                    break;
                case 0:
                    salir = 1;
                    break;
                default:
                    vistaMenu.mostrarError("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

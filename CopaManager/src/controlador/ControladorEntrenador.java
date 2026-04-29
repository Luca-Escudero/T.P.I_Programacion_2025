package controlador;

import consultaSQL.*;
import modelo.Entrenador;
import vista.*;

import java.util.List;


public class ControladorEntrenador {
    private VistaEntrenador vistaEntrenador;
    private ControladorBaseDeDatos controladorBaseDeDatos;
    private VistaMenu vistaMenu;

    public ControladorEntrenador(VistaMenu vistaMenu, ControladorBaseDeDatos controladorBaseDeDatos) {
        this.vistaEntrenador = new VistaEntrenador();
        this.controladorBaseDeDatos = controladorBaseDeDatos;
        this.vistaMenu = vistaMenu;
    }

    public void registrarNuevoEntrenador() {
        List<String> datos = vistaEntrenador.obtenerDatosNuevoEntrenador();
        try {
            String nombre = datos.get(0);
            int edad = Integer.parseInt(datos.get(1));
            String nacionalidad = datos.get(2);

            Entrenador entrenador = new Entrenador(0, nombre, edad, nacionalidad);
            controladorBaseDeDatos.guardarEntrenador(entrenador);
            vistaMenu.mostrarMensaje("Entrenador " + entrenador.getNombre() + " registrado exitosamente con ID: " + entrenador.getIdEntrenador());
        } catch (NumberFormatException e) {
            vistaMenu.mostrarError("Error: La edad debe ser un número válido.");
        } catch (Exception e) {
            vistaMenu.mostrarError("Error al registrar el entrenador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void verTodosLosEntrenadores() {
        List<Entrenador> entrenadores = controladorBaseDeDatos.obtenerTodosLosEntrenadores();
        vistaEntrenador.mostrarEntrenadores(entrenadores);
    }

    public void mostrarMenuEntrenadores() {
        boolean salir = false;
        while (!salir) {
            int opcion = vistaEntrenador.mostrarMenuEntrenadores();
            switch (opcion) {
                case 1:
                    registrarNuevoEntrenador();
                    break;
                case 2:
                    verTodosLosEntrenadores();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    vistaMenu.mostrarError("Opción no válida. Intente nuevamente.");
            }
        }
    }
}

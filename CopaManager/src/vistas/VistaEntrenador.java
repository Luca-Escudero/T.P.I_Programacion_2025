package vistas;

import modelos.Entrenador;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class VistaEntrenador {
    private Scanner escaner;

    public VistaEntrenador() {
        this.escaner = new Scanner(System.in);
    }

    public List<String> obtenerDatosNuevoEntrenador() {
        System.out.println("Ingrese los datos del nuevo entrenador:");
        System.out.print("Nombre: ");
        String nombre = escaner.nextLine();
        System.out.print("Edad: ");
        String edad = escaner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = escaner.nextLine();

        List<String> datos = new ArrayList<>();
        datos.add(nombre);
        datos.add(edad);
        datos.add(nacionalidad);
        return datos;
    }

    public int obtenerIdEntrenador() {
        System.out.print("ID Entrenador: ");
        return Integer.parseInt(escaner.nextLine());
    }

    public void mostrarEntrenadores(List<Entrenador> entrenadores) {
        System.out.println("Lista de Entrenadores:");
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores registrados.");
        } else {
            for (Entrenador entrenador : entrenadores) {
                System.out.println("ID: " + entrenador.getIdEntrenador() + ", Nombre: " + entrenador.getNombre() + ", Edad: " + entrenador.getEdad() + ", Nacionalidad: " + entrenador.getNacionalidad());
            }
        }
    }

    public int mostrarMenuEntrenadores() {
        System.out.println("=== Gestión de Entrenadores ===");
        System.out.println("1. Registrar Nuevo Entrenador");
        System.out.println("2. Ver Entrenadores Registrados");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(escaner.nextLine());
    }
}

package vistas;

import modelos.Equipo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class VistaEquipo {

    private Scanner escaner;

    public VistaEquipo() {
        this.escaner = new Scanner(System.in);
    }

    public Map<String, String> obtenerDatosInicialesNuevoEquipo() {
        System.out.println("Ingrese los datos del nuevo equipo:");
        System.out.print("Nombre: ");
        String nombre = escaner.nextLine();
        System.out.print("País: ");
        String pais = escaner.nextLine();

        Map<String, String> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("pais", pais);
        return datos;
    }

    public void mostrarListaEquipos(List<Equipo> lista) {
        System.out.println("Lista de Equipos:");
        if (lista.isEmpty()) {
            System.out.println("No hay equipos registrados para este campeonato.");
        } else {
            System.out.printf("%-5s | %-20s | %-15s | %-25s%n", "ID", "Nombre del Equipo", "País", "Entrenador");
            System.out.println("--------------------------------------------------------------------------------");
            for (Equipo equipo : lista) {
                String nombreEntrenador = (equipo.getEntrenador() != null) ? equipo.getEntrenador().getNombre() : "N/A";
                System.out.printf("ID: %-2d | %-20s | %-15s | %-25s%n",
                                  equipo.getIdEquipo(),
                                  equipo.getNombre(),
                                  equipo.getPais(),
                                  nombreEntrenador);
            }
        }
    }

    public void mostrarMenuEquipos() {
        System.out.println("=== Gestión de Equipos ===");
        System.out.println("1. Registrar Nuevo Equipo");
        System.out.println("2. Ver Equipos por Campeonato");
        System.out.println("0. Volver al Menú Principal");
    }
}

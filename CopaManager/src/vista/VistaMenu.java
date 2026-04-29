package vista;

import java.util.List;
import java.util.Scanner;

import modelo.Campeonato;

public class VistaMenu {
    private Scanner escaner;

    public VistaMenu() {
        this.escaner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Gestionar Campeonatos");
        System.out.println("2. Gestionar Equipos");
        System.out.println("3. Gestionar Entrenadores");
        System.out.println("4. Gestionar Partidos"); 
        System.out.println("5. Ver Fixture");
        System.out.println("0. Salir");
    }

    public int obtenerOpcionMenu() {
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(escaner.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }

    public String obtenerEntradaUsuario(String mensaje) {
        System.out.print(mensaje);
        return escaner.nextLine();
    }

    public int obtenerNumeroUsuario(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(escaner.nextLine());
    }

    public Campeonato seleccionarCampeonato(List<Campeonato> campeonatos) {
        if (campeonatos.isEmpty()) {
            System.out.println("No hay campeonatos disponibles.");
            return null;
        }
        System.out.println("Seleccione un Campeonato:");
        for (Campeonato camp : campeonatos) {
            System.out.println("ID: " + camp.getIdCampeonato() + ", Nombre: " + camp.getNombre() + ", Año: " + camp.getAnio());
        }
        System.out.print("Ingrese el ID del campeonato: ");
        int idSeleccionado = Integer.parseInt(escaner.nextLine());
        return campeonatos.stream()
                .filter(c -> c.getIdCampeonato() == idSeleccionado)
                .findFirst()
                .orElse(null);
    }
}

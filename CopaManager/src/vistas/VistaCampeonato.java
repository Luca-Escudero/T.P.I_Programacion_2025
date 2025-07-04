package vistas;

import modelos.Campeonato;

import java.util.List;
import java.util.Scanner;

public class VistaCampeonato {

    private Scanner escaner;

    public VistaCampeonato() {
        this.escaner = new Scanner(System.in);
    }

    public String obtenerNombreCampeonato() {
        System.out.print("Ingrese el nombre del campeonato: ");
        return escaner.nextLine();
    }

    public int obtenerAnioCampeonato() {
        System.out.print("Ingrese el año del campeonato: ");
        return Integer.parseInt(escaner.nextLine());
    }

    public void mostrarCampeonatos(List<Campeonato> campeonatos) {
        System.out.println("Lista de Campeonatos:");
        if (campeonatos.isEmpty()) {
            System.out.println("No hay campeonatos registrados.");
        } else {
            for (Campeonato campeonato : campeonatos) {
                System.out.println("ID: " + campeonato.getIdCampeonato() + ", Nombre: " + campeonato.getNombre() + ", Año: " + campeonato.getAnio() + ", Estado: " + campeonato.getEstado());
            }
        }
    }

    public int mostrarMenuCampeonatos() {
        System.out.println("=== Gestión de Campeonatos ===");
        System.out.println("1. Crear Nuevo Campeonato");
        System.out.println("2. Mostrar Campeonatos Existentes"); 
        System.out.println("3. Finalizar Campeonato"); 
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(escaner.nextLine());
    }

    public int obtenerIdCampeonatoAFinalizar() {
        System.out.print("Ingrese el ID del campeonato a finalizar: ");
        return Integer.parseInt(escaner.nextLine());
    }
}

package vista;

import modelo.Partido;
import modelo.Equipo;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class VistaPartido {

    private Scanner escaner;
    private VistaMenu vistaMenu;

    public VistaPartido(VistaMenu vistaMenu) {
        this.escaner = new Scanner(System.in);
        this.vistaMenu = new VistaMenu();
    }

    public void mostrarFixture(List<Partido> partidos) {
        System.out.println("Fixture de Partidos:");
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos registrados para este campeonato.");
        } else {
            for (Partido partido : partidos) {
                System.out.println(partido);
            }
        }
    }

    public int obtenerIdPartidoACargar() {
        System.out.print("Ingrese el ID del partido a cargar: ");
        return Integer.parseInt(escaner.nextLine());
    }

    public int obtenerGoles(String equipo) {
        System.out.print("Ingrese los goles del equipo " + equipo + ": ");
        return Integer.parseInt(escaner.nextLine());
    }

    public void mostrarMenuPartidos() {
        System.out.println("=== Gestión de Partidos ===");
        System.out.println("1. Agregar Partido");       
        System.out.println("2. Cargar Resultado de Partido");    
        System.out.println("0. Volver al Menú Principal");
    }

    public Map<String, String> obtenerDatosPartidoManual(List<Equipo> equiposDisponibles) {
        if (equiposDisponibles.isEmpty()) {
            vistaMenu.mostrarError("No hay equipos disponibles en este campeonato para agregar un partido.");
            return null;
        }

        System.out.println("Equipos disponibles:");
        for (Equipo equipo : equiposDisponibles) {
            System.out.println("ID: " + equipo.getIdEquipo() + " - " + equipo.getNombre());
        }

        System.out.println("Ingrese los datos del nuevo partido:");
        System.out.print("ID Equipo Local: ");
        String idLocal = escaner.nextLine();
        System.out.print("ID Equipo Visitante: ");
        String idVisitante = escaner.nextLine();
        System.out.print("Fecha y Hora del partido (dd/MM/yyyy HH:mm): ");
        String fechaHoraStr = escaner.nextLine();

        Map<String, String> datosPartido = new HashMap<>();
        datosPartido.put("idLocal", idLocal);
        datosPartido.put("idVisitante", idVisitante);
        datosPartido.put("fechaHoraStr", fechaHoraStr);
        return datosPartido;
    }
}
package controladores;

import consultasSQL.*;
import modelos.*;
import vistas.VistaMenu;
import vistas.VistaPartido;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ControladorPartido {
    private VistaPartido vistaPartido;
    private ControladorBaseDeDatos controladorBaseDeDatos;
    private VistaMenu vistaMenu;

    public ControladorPartido(VistaMenu vistaMenu, ControladorBaseDeDatos controladorBaseDeDatos) {
        this.vistaPartido = new VistaPartido(vistaMenu);
        this.controladorBaseDeDatos = controladorBaseDeDatos;
        this.vistaMenu = vistaMenu;
    }

    public void cargarResultadoPartido(Campeonato campeonato) {
        if (campeonato != null && "Finalizado".equals(campeonato.getEstado())) {
            vistaMenu.mostrarError("No se puede cargar un resultado en un partido de un campeonato que ya está Finalizado.");
            return;
        }

        List<Partido> partidosDisponibles = controladorBaseDeDatos.obtenerTodosLosPartidos(campeonato.getIdCampeonato());
        if (partidosDisponibles.isEmpty()) {
            vistaMenu.mostrarMensaje("No hay partidos registrados para cargar un resultado en este campeonato.");
            return;
        }
        vistaPartido.mostrarFixture(partidosDisponibles);

        int idPartido = vistaPartido.obtenerIdPartidoACargar();
        Partido partido = controladorBaseDeDatos.obtenerPartidoPorId(idPartido);
        if (partido != null) {
            if (partido.getResultado() != null && (partido.getResultado().getGolesLocal() != 0 || partido.getResultado().getGolesVisitante() != 0)) {
                vistaMenu.mostrarMensaje("Este partido ya tiene un resultado cargado. Se sobrescribirá.");
            }

            int golesLocal = vistaPartido.obtenerGoles("Local");
            int golesVisitante = vistaPartido.obtenerGoles("Visitante");
            Resultado resultado = new Resultado(golesLocal, golesVisitante);
            partido.setResultado(resultado);
            controladorBaseDeDatos.actualizarPartido(partido);
            vistaMenu.mostrarMensaje("Resultado del partido cargado exitosamente.");
        } else {
            vistaMenu.mostrarError("Partido no encontrado.");
        }
    }

    public void agregarPartidoManual(Campeonato campeonato) {
        if (campeonato == null) {
            vistaMenu.mostrarError("Debe seleccionar un campeonato para agregar un partido.");
            return;
        }
        if ("Finalizado".equals(campeonato.getEstado())) {
            vistaMenu.mostrarError("No se pueden agregar partidos a un campeonato que ya está Finalizado.");
            return;
        }

        List<Equipo> equiposDisponibles = controladorBaseDeDatos.obtenerTodosLosEquipos(campeonato.getIdCampeonato());
        if (equiposDisponibles.size() < 2) {
            vistaMenu.mostrarError("Se necesitan al menos 2 equipos en el campeonato para agregar un partido.");
            return;
        }

        Map<String, String> datosPartido = vistaPartido.obtenerDatosPartidoManual(equiposDisponibles);
        if (datosPartido == null) {
            return;
        }

        int idLocal;
        int idVisitante;
        Date fechaHora;

        try {
            idLocal = Integer.parseInt(datosPartido.get("idLocal"));
            idVisitante = Integer.parseInt(datosPartido.get("idVisitante"));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fechaHora = formatter.parse(datosPartido.get("fechaHoraStr"));
        } catch (NumberFormatException e) {
            vistaMenu.mostrarError("Error: IDs de equipo no válidos. Por favor, ingrese números.");
            return;
        } catch (ParseException e) {
            vistaMenu.mostrarError("Error: Formato de fecha y hora inválido. Use dd/MM/yyyy HH:mm.");
            return;
        }

        Equipo equipoLocal = controladorBaseDeDatos.obtenerEquipoPorId(idLocal);
        Equipo equipoVisitante = controladorBaseDeDatos.obtenerEquipoPorId(idVisitante);

        if (equipoLocal == null || equipoVisitante == null) {
            vistaMenu.mostrarError("Uno o ambos equipos no fueron encontrados en la base de datos.");
            return;
        }
        if (equipoLocal.getIdEquipo() == equipoVisitante.getIdEquipo()) {
            vistaMenu.mostrarError("Un equipo no puede jugar contra sí mismo.");
            return;
        }
        if (equipoLocal.getIdCampeonato() != campeonato.getIdCampeonato() || equipoVisitante.getIdCampeonato() != campeonato.getIdCampeonato()) {
            vistaMenu.mostrarError("Ambos equipos deben pertenecer al campeonato seleccionado.");
            return;
        }

        Partido nuevoPartido = new Partido(0, equipoLocal, equipoVisitante, fechaHora, campeonato.getIdCampeonato());

        controladorBaseDeDatos.guardarPartido(nuevoPartido);
        vistaMenu.mostrarMensaje("Partido agregado manualmente exitosamente con ID: " + nuevoPartido.getIdPartido());
    }

    public void mostrarMenuPartidos() {
        boolean salir = false;
        while (!salir) {
            vistaPartido.mostrarMenuPartidos();
            int opcion = vistaMenu.obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    List<Campeonato> campeonatosParaAgregar = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
                    Campeonato campeonatoSeleccionadoAgregar = vistaMenu.seleccionarCampeonato(campeonatosParaAgregar);
                    if (campeonatoSeleccionadoAgregar != null) {
                        agregarPartidoManual(campeonatoSeleccionadoAgregar);
                    } else {
                        vistaMenu.mostrarMensaje("No se seleccionó ningún campeonato para agregar un partido.");
                    }
                    break;
                case 2:
                    List<Campeonato> campeonatosParaCargar = controladorBaseDeDatos.obtenerTodosLosCampeonatos();
                    Campeonato campeonatoSeleccionadoCargar = vistaMenu.seleccionarCampeonato(campeonatosParaCargar);
                    if (campeonatoSeleccionadoCargar != null) {
                        cargarResultadoPartido(campeonatoSeleccionadoCargar);
                    } else {
                        vistaMenu.mostrarMensaje("No se seleccionó ningún campeonato para cargar el resultado.");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    vistaMenu.mostrarError("Opción no válida. Intente de nuevo.");
            }
        }
    }
}


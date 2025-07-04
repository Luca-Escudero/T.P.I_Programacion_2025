package consultasSQL;


import modelos.*;
import conexion.ConexionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorBaseDeDatos {

    public void guardarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO entrenadores (nombre, edad, nacionalidad) VALUES (?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entrenador.getNombre());
            stmt.setInt(2, entrenador.getEdad());
            stmt.setString(3, entrenador.getNacionalidad());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entrenador.setIdEntrenador(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Entrenador obtenerEntrenadorPorId(int id) {
        String sql = "SELECT * FROM entrenadores WHERE idEntrenador = ?";
        Entrenador entrenador = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entrenador = new Entrenador(rs.getInt("idEntrenador"), rs.getString("nombre"),
                        rs.getInt("edad"), rs.getString("nacionalidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenador;
    }

    public void guardarEquipo(Equipo equipo) {
        String sql = "INSERT INTO equipos (nombre, pais, idEntrenador, idCampeonato) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getPais());
            if (equipo.getEntrenador() != null) {
                stmt.setInt(3, equipo.getEntrenador().getIdEntrenador());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, equipo.getIdCampeonato());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipo.setIdEquipo(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipo> obtenerTodosLosEquipos(int idCampeonato) {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE idCampeonato = ?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCampeonato);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Entrenador entrenador = obtenerEntrenadorPorId(rs.getInt("idEntrenador"));
                Equipo equipo = new Equipo(rs.getInt("idEquipo"), rs.getString("nombre"),
                        rs.getString("pais"), entrenador, idCampeonato);
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public void guardarCampeonato(Campeonato campeonato) {
        String sql = "INSERT INTO campeonatos (nombre, anio, estado, puntosVictoria, puntosEmpate, puntosDerrota) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, campeonato.getNombre());
            stmt.setInt(2, campeonato.getAnio());
            stmt.setString(3, campeonato.getEstado());
            stmt.setNull(4, Types.INTEGER); // puntosVictoria (ya no se usan)
            stmt.setNull(5, Types.INTEGER); // puntosEmpate (ya no se usan)
            stmt.setNull(6, Types.INTEGER); // puntosDerrota (ya no se usan)
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                campeonato.setIdCampeonato(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Campeonato obtenerCampeonatoPorId(int id) {
        String sql = "SELECT * FROM campeonatos WHERE idCampeonato = ?";
        Campeonato campeonato = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                campeonato = new Campeonato(rs.getInt("idCampeonato"), rs.getString("nombre"),
                        rs.getInt("anio"));
                campeonato.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campeonato;
    }

    public void actualizarCampeonato(Campeonato campeonato) {
        String sql = "UPDATE campeonatos SET nombre = ?, anio = ?, estado = ?, puntosVictoria = ?, puntosEmpate = ?, puntosDerrota = ? WHERE idCampeonato = ?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, campeonato.getNombre());
            stmt.setInt(2, campeonato.getAnio());
            stmt.setString(3, campeonato.getEstado());
            stmt.setNull(4, Types.INTEGER); // puntosVictoria (ya no se usan)
            stmt.setNull(5, Types.INTEGER); // puntosEmpate (ya no se usan)
            stmt.setNull(6, Types.INTEGER); // puntosDerrota (ya no se usan)
            stmt.setInt(7, campeonato.getIdCampeonato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarPartido(Partido partido) {
        String sql = "INSERT INTO partidos (idEquipoLocal, idEquipoVisitante, fechaHora, idCampeonato, golesLocal, golesVisitante) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, partido.getEquipoLocal().getIdEquipo());
            stmt.setInt(2, partido.getEquipoVisitante().getIdEquipo());
            stmt.setTimestamp(3, new Timestamp(partido.getFechaHora().getTime()));
            stmt.setInt(4, partido.getIdCampeonato());
            if (partido.getResultado() != null) {
                stmt.setInt(5, partido.getResultado().getGolesLocal());
                stmt.setInt(6, partido.getResultado().getGolesVisitante());
            } else {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                partido.setIdPartido(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Partido> obtenerTodosLosPartidos(int idCampeonato) {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partidos WHERE idCampeonato = ?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCampeonato);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEquipoLocal = rs.getInt("idEquipoLocal");
                int idEquipoVisitante = rs.getInt("idEquipoVisitante");
                Equipo equipoLocal = obtenerEquipoPorId(idEquipoLocal);
                Equipo equipoVisitante = obtenerEquipoPorId(idEquipoVisitante);

                Partido partido = new Partido(rs.getInt("idPartido"), equipoLocal, equipoVisitante,
                        rs.getTimestamp("fechaHora"), idCampeonato);

                if (rs.getObject("golesLocal") != null && rs.getObject("golesVisitante") != null) {
                    partido.setResultado(new Resultado(rs.getInt("golesLocal"), rs.getInt("golesVisitante")));
                }
                partidos.add(partido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }

    public void actualizarPartido(Partido partido) {
        String sql = "UPDATE partidos SET golesLocal = ?, golesVisitante = ? WHERE idPartido = ?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (partido.getResultado() != null) {
                stmt.setInt(1, partido.getResultado().getGolesLocal());
                stmt.setInt(2, partido.getResultado().getGolesVisitante());
            } else {
                stmt.setNull(1, Types.INTEGER);
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, partido.getIdPartido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Partido obtenerPartidoPorId(int idPartido) {
        String sql = "SELECT * FROM partidos WHERE idPartido = ?";
        Partido partido = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPartido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idCampeonato = rs.getInt("idCampeonato");
                int idEquipoLocal = rs.getInt("idEquipoLocal");
                int idEquipoVisitante = rs.getInt("idEquipoVisitante");
                Equipo equipoLocal = obtenerEquipoPorId(idEquipoLocal);
                Equipo equipoVisitante = obtenerEquipoPorId(idEquipoVisitante);

                partido = new Partido(rs.getInt("idPartido"), equipoLocal, equipoVisitante,
                        rs.getTimestamp("fechaHora"), idCampeonato);

                if (rs.getObject("golesLocal") != null && rs.getObject("golesVisitante") != null) {
                    partido.setResultado(new Resultado(rs.getInt("golesLocal"), rs.getInt("golesVisitante")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partido;
    }

    public List<Campeonato> obtenerTodosLosCampeonatos() {
        List<Campeonato> campeonatos = new ArrayList<>();
        String sql = "SELECT * FROM campeonatos";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Campeonato campeonato = new Campeonato(rs.getInt("idCampeonato"), rs.getString("nombre"),
                        rs.getInt("anio"));
                campeonato.setEstado(rs.getString("estado"));
                campeonatos.add(campeonato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campeonatos;
    }

    public Equipo obtenerEquipoPorId(int id) {
        String sql = "SELECT * FROM equipos WHERE idEquipo = ?";
        Equipo equipo = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Entrenador entrenador = obtenerEntrenadorPorId(rs.getInt("idEntrenador"));
                equipo = new Equipo(rs.getInt("idEquipo"), rs.getString("nombre"),
                        rs.getString("pais"), entrenador, rs.getInt("idCampeonato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipo;
    }

    public List<Entrenador> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM entrenadores";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Entrenador entrenador = new Entrenador(rs.getInt("idEntrenador"), rs.getString("nombre"),
                        rs.getInt("edad"), rs.getString("nacionalidad"));
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }

}

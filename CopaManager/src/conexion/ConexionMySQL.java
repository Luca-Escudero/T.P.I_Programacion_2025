package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/copamanager_bd"; // URL de la base de datos
    private static final String USER = "root"; // Usuario de la base de datos
    private static final String PASSWORD = "Luca1234"; // Contraseña de la base de datos

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        try {
            // Intentamos cargar el controlador de MySQL (aunque en versiones más recientes de JDBC esto es opcional)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver no encontrado", e);
        }
    }
}
# 🏆 CopaManager - Sistema de Gestión de Fútbol Amateur

Desarrollado como proyecto final para **Programación II - UTN Villa María (2025)**.

## 📝 Descripción
CopaManager es una solución integral en Java diseñada para la administración de torneos de fútbol. El sistema permite gestionar la logística completa: desde la inscripción de equipos hasta la actualización automática de tablas de posiciones basada en los resultados cargados.

## 🚀 Funcionalidades Clave
* **Gestión de Planteles:** Registro de equipos, entrenadores y jugadores con validaciones de integridad.
* **Fixture Inteligente:** Generación y visualización de encuentros organizados por campeonato.
* **Persistencia Robusta:** Carga de resultados con manejo de estados (Pendiente, En curso, Finalizado) sincronizados con base de datos.
* **Arquitectura Limpia:** Implementación estricta del patrón **MVC (Model-View-Controller)** para facilitar el mantenimiento.

## 🛠️ Stack Técnico
* **Lenguaje:** Java 21 (JDK)
* **Arquitectura:** MVC (Modelo-Vista-Controlador)
* **Base de Datos:** MySQL con conectividad JDBC
* **Librerías:** MySQL Connector J 9.2.0

## 🔧 Configuración e Instalación
1. **Base de Datos:** Importar el script `database_schema.sql` (disponible en el directorio /sql) en tu servidor MySQL.
2. **Conexión:** Configurar `url`, `user` y `password` en la clase `ConexionMySQL.java`.
3. **Ejecución:** Ejecutar la clase `Main.java` para iniciar la interfaz de consola.

## 👥 Integrantes
* **Tomás Grasso**
* **Luca Escudero**

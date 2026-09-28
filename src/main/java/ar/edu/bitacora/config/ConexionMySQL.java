package ar.edu.bitacora.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionMySQL {

    // Guarda los datos de conexión (URL, usuario, contraseña)
    private static Properties propiedades = new Properties();

    // Este bloque se ejecuta una sola vez, cuando la clase se carga por primera vez.
    // Lee el archivo db.properties que está en src/main/resources
    static {
        try (InputStream archivo = ConexionMySQL.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (archivo == null) {
                throw new RuntimeException("No se encontró db.properties. Copiá db.properties.example y completá tu contraseña.");
            }
            propiedades.load(archivo);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer db.properties: " + e.getMessage());
        }
    }

    // Devuelve una conexión nueva a la base de datos, usando los datos leídos arriba
    public static Connection obtenerConexion() throws SQLException {
        String url = propiedades.getProperty("db.url");
        String usuario = propiedades.getProperty("db.user");
        String password = propiedades.getProperty("db.password");
        return DriverManager.getConnection(url, usuario, password);
    }
}
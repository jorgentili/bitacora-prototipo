package ar.edu.bitacora.dao;

import ar.edu.bitacora.config.ConexionMySQL;
import ar.edu.bitacora.modelo.Estudiante;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// Esta clase sabe cómo guardar, leer y borrar estudiantes en MySQL.
// Cada método abre su propia conexión, hace su trabajo, y la cierra.
public class EstudianteDAO {

    // ---------- INSERTAR ----------
    // Guarda un estudiante nuevo en la tabla "estudiantes"
    public void insertar(Estudiante e) throws SQLException {

        String sql = "INSERT INTO estudiantes (legajo, nombre, apellido, dni, email, fecha_nacimiento, activo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // "try-with-resources": abre la conexión y el PreparedStatement,
        // y Java se encarga de cerrarlos solo al terminar (aunque haya un error).
        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Los "?" del SQL se completan en orden con setString, setDate, etc.
            // Esto se llama PreparedStatement: es más seguro que armar el SQL
            // pegando texto, porque evita el ataque conocido como "inyección SQL".
            ps.setString(1, e.getLegajo());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getDni());

            if (e.getEmail() == null || e.getEmail().isBlank()) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, e.getEmail());
            }

            if (e.getFechaNacimiento() == null) {
                ps.setNull(6, Types.DATE);
            } else {
                ps.setDate(6, Date.valueOf(e.getFechaNacimiento()));
            }

            ps.setBoolean(7, e.isActivo());

            ps.executeUpdate();

            // MySQL generó un id_estudiante automático (AUTO_INCREMENT).
            // Este bloque lo recupera y se lo guarda al objeto Estudiante.
            try (ResultSet idsGenerados = ps.getGeneratedKeys()) {
                if (idsGenerados.next()) {
                    e.setIdEstudiante(idsGenerados.getInt(1));
                }
            }
        }
    }

    // ---------- CONSULTAR ----------
    // Devuelve la lista completa de estudiantes guardados en la base
    public List<Estudiante> listar() throws SQLException {

        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY apellido, nombre";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // rs.next() avanza fila por fila hasta que no queden más
            while (rs.next()) {
                Date fecha = rs.getDate("fecha_nacimiento");

                Estudiante e = new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        fecha == null ? null : fecha.toLocalDate(),
                        rs.getBoolean("activo")
                );

                lista.add(e);
            }
        }

        return lista;
    }

    // ---------- BORRAR ----------
    // Elimina un estudiante según su id_estudiante
    public void eliminar(int idEstudiante) throws SQLException {

        String sql = "DELETE FROM estudiantes WHERE id_estudiante = ?";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);
            ps.executeUpdate();
        }
    }
}
package ar.edu.bitacora.dao;

import ar.edu.bitacora.config.ConexionMySQL;
import ar.edu.bitacora.modelo.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// Sabe cómo guardar, leer y borrar docentes en MySQL. Mismo patrón que EstudianteDAO.
public class DocenteDAO {

    // ---------- INSERTAR ----------
    public void insertar(Docente d) throws SQLException {

        String sql = "INSERT INTO docentes (legajo, nombre, apellido, dni, email, activo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getLegajo());
            ps.setString(2, d.getNombre());
            ps.setString(3, d.getApellido());
            ps.setString(4, d.getDni());

            if (d.getEmail() == null || d.getEmail().isBlank()) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, d.getEmail());
            }

            ps.setBoolean(6, d.isActivo());

            ps.executeUpdate();

            try (ResultSet idsGenerados = ps.getGeneratedKeys()) {
                if (idsGenerados.next()) {
                    d.setIdDocente(idsGenerados.getInt(1));
                }
            }
        }
    }

    // ---------- CONSULTAR ----------
    public List<Docente> listar() throws SQLException {

        List<Docente> lista = new ArrayList<>();
        String sql = "SELECT * FROM docentes ORDER BY apellido, nombre";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Docente d = new Docente(
                        rs.getInt("id_docente"),
                        rs.getString("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getBoolean("activo")
                );

                lista.add(d);
            }
        }

        return lista;
    }

    // ---------- BORRAR ----------
    public void eliminar(int idDocente) throws SQLException {

        String sql = "DELETE FROM docentes WHERE id_docente = ?";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idDocente);
            ps.executeUpdate();
        }
    }
}
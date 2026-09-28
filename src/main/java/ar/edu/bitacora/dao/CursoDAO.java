package ar.edu.bitacora.dao;

import ar.edu.bitacora.config.ConexionMySQL;
import ar.edu.bitacora.modelo.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Sabe cómo guardar, leer y borrar cursos en MySQL. Mismo patrón que EstudianteDAO y DocenteDAO.
public class CursoDAO {

    // ---------- INSERTAR ----------
    public void insertar(Curso c) throws SQLException {

        String sql = "INSERT INTO cursos (nombre, anio_lectivo, division) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getAnioLectivo());
            ps.setString(3, c.getDivision());

            ps.executeUpdate();

            try (ResultSet idsGenerados = ps.getGeneratedKeys()) {
                if (idsGenerados.next()) {
                    c.setIdCurso(idsGenerados.getInt(1));
                }
            }
        }
    }

    // ---------- CONSULTAR ----------
    public List<Curso> listar() throws SQLException {

        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos ORDER BY anio_lectivo, nombre, division";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre"),
                        rs.getInt("anio_lectivo"),
                        rs.getString("division")
                );

                lista.add(c);
            }
        }

        return lista;
    }

    // ---------- BORRAR ----------
    public void eliminar(int idCurso) throws SQLException {

        String sql = "DELETE FROM cursos WHERE id_curso = ?";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            ps.executeUpdate();
        }
    }
}
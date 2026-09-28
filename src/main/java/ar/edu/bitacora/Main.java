package ar.edu.bitacora;

import ar.edu.bitacora.dao.CursoDAO;
import ar.edu.bitacora.dao.DocenteDAO;
import ar.edu.bitacora.dao.EstudianteDAO;
import ar.edu.bitacora.modelo.Curso;
import ar.edu.bitacora.modelo.Docente;
import ar.edu.bitacora.modelo.Estudiante;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        probarEstudiantes();
        System.out.println("\n----------------------------------------\n");
        probarDocentes();
        System.out.println("\n----------------------------------------\n");
        probarCursos();
    }

    // ---------- Prueba del módulo Estudiante ----------
    private static void probarEstudiantes() {

        EstudianteDAO dao = new EstudianteDAO();

        try {
            System.out.println("MÓDULO ESTUDIANTE");
            System.out.println("Insertando un estudiante nuevo...");

            Estudiante nuevo = new Estudiante();
            nuevo.setLegajo("ALU-003");
            nuevo.setNombre("Lucas");
            nuevo.setApellido("Fernández");
            nuevo.setDni("41333444");
            nuevo.setEmail("lucas.fernandez@bitacora.edu");
            nuevo.setFechaNacimiento(LocalDate.of(2009, 3, 10));
            nuevo.setActivo(true);

            dao.insertar(nuevo);
            System.out.println("Insertado con id_estudiante = " + nuevo.getIdEstudiante());

            System.out.println("Listado de estudiantes:");
            for (Estudiante e : dao.listar()) {
                System.out.println(e.getIdEstudiante() + " - " + e.getApellido() + ", " + e.getNombre());
            }

            dao.eliminar(nuevo.getIdEstudiante());
            System.out.println("Estudiante de prueba borrado.");

        } catch (Exception e) {
            System.out.println("Error en módulo Estudiante: " + e.getMessage());
        }
    }

    // ---------- Prueba del módulo Docente ----------
    private static void probarDocentes() {

        DocenteDAO dao = new DocenteDAO();

        try {
            System.out.println("MÓDULO DOCENTE");
            System.out.println("Insertando un docente nuevo...");

            Docente nuevo = new Docente();
            nuevo.setLegajo("DOC-001");
            nuevo.setNombre("Marta");
            nuevo.setApellido("Suárez");
            nuevo.setDni("30222111");
            nuevo.setEmail("marta.suarez@bitacora.edu");
            nuevo.setActivo(true);

            dao.insertar(nuevo);
            System.out.println("Insertado con id_docente = " + nuevo.getIdDocente());

            System.out.println("Listado de docentes:");
            List<Docente> docentes = dao.listar();
            for (Docente d : docentes) {
                System.out.println(d.getIdDocente() + " - " + d.getApellido() + ", " + d.getNombre());
            }

            dao.eliminar(nuevo.getIdDocente());
            System.out.println("Docente de prueba borrado.");

        } catch (Exception e) {
            System.out.println("Error en módulo Docente: " + e.getMessage());
        }
    }

    // ---------- Prueba del módulo Curso ----------
    private static void probarCursos() {

        CursoDAO dao = new CursoDAO();

        try {
            System.out.println("MÓDULO CURSO");
            System.out.println("Insertando un curso nuevo...");

            // OJO: insertamos un curso de PRUEBA, distinto a los 3 que ya
            // cargó el script (Primer/Segundo/Tercer Año), porque esos
            // ya tienen inscripciones relacionadas y no se pueden borrar.
            Curso nuevo = new Curso();
            nuevo.setNombre("Cuarto Año");
            nuevo.setAnioLectivo(2026);
            nuevo.setDivision("C");

            dao.insertar(nuevo);
            System.out.println("Insertado con id_curso = " + nuevo.getIdCurso());

            System.out.println("Listado de cursos:");
            List<Curso> cursos = dao.listar();
            for (Curso c : cursos) {
                System.out.println(c.getIdCurso() + " - " + c.getNombre() + " " + c.getAnioLectivo() + " \"" + c.getDivision() + "\"");
            }

            dao.eliminar(nuevo.getIdCurso());
            System.out.println("Curso de prueba borrado.");

        } catch (Exception e) {
            System.out.println("Error en módulo Curso: " + e.getMessage());
        }
    }
}
package ar.edu.bitacora.modelo;

// Representa un curso: por ejemplo "Primer Año", 2026, división "A".
public class Curso {

    private int idCurso;
    private String nombre;
    private int anioLectivo;
    private String division;

    public Curso() {
    }

    public Curso(int idCurso, String nombre, int anioLectivo, String division) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.anioLectivo = anioLectivo;
        this.division = division;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioLectivo() {
        return anioLectivo;
    }

    public void setAnioLectivo(int anioLectivo) {
        this.anioLectivo = anioLectivo;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
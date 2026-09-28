package ar.edu.bitacora.modelo;

import java.time.LocalDate;

// Esta clase representa a un estudiante: es el "molde" con sus datos.
public class Estudiante {

    private int idEstudiante;
    private String legajo;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;
    private boolean activo = true;

    // Constructor vacío: sirve para crear un Estudiante "en blanco"
    // y después ir cargando sus datos con los métodos set...()
    public Estudiante() {
    }

    // Constructor completo: sirve para crear un Estudiante con todos
    // los datos de una sola vez (por ejemplo, cuando lo leemos de la base)
    public Estudiante(int idEstudiante, String legajo, String nombre, String apellido,
                       String dni, String email, LocalDate fechaNacimiento, boolean activo) {
        this.idEstudiante = idEstudiante;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
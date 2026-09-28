package ar.edu.bitacora.modelo;

// Esta clase representa a un docente. Mismo patrón que Estudiante:
public class Docente {

    private int idDocente;
    private String legajo;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private boolean activo = true;

    public Docente() {
    }

    public Docente(int idDocente, String legajo, String nombre, String apellido,
                   String dni, String email, boolean activo) {
        this.idDocente = idDocente;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.activo = activo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
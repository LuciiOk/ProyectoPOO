package modelo;

public class Cliente {
    private String rut;
    private String nombres;
    private String apellido;
    private String email;
    private String telefono;

    public Cliente() {
    }
    
    public Cliente(String rut, String nombres, String apellido, String email, String telefono) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    public Cliente(String rut, String nombres, String apellido, String telefono) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = "NO TIENE";
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Rut:" + rut + " Nombre: " + nombres + " "+ apellido + "\nE-mail: " + email + " Telefono: " + telefono;
    }

}

package poo.proj;

public abstract class Persona {

    protected int id;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String correo;
    protected String telefono;
    protected String direccion;

    
    public Persona() {
    }

    public void registrar() {
    }

    public void modificar() {
    }

    public void eliminar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
package poo.proj;

public abstract class Persona {
    
    private static int contador = 1;
    protected int id;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipoDocumento;
    protected String numeroDocumento;
    protected String telefono;

    public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String telefono, String direccion) {
        this.id = contador++;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getNombreCompleto() {
    return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public abstract void mostrarDatos();
}
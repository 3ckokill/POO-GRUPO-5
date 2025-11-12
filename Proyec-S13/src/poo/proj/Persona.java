package poo.proj;

public abstract class Persona {
    
    private static int contador = 1;
    protected int id;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipoDocumento;
    protected String numeroDocumento;
    protected String correo;
    protected String telefono;
    protected String direccion;

    // EL CONSTRUCTOR AHORA USA LOS SETTERS PARA VALIDAR
    public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        this.id = contador++;
        // Se llama a los setters para asegurar que los datos iniciales sean válidos
        setNombre(nombre);
        setApellidoPaterno(apellidoPaterno);
        setApellidoMaterno(apellidoMaterno);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);
        setTelefono(telefono);
        setDireccion(direccion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // --- NUEVA VALIDACIÓN ---
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        // --- NUEVA VALIDACIÓN ---
        if (apellidoPaterno == null || apellidoPaterno.isBlank()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
        }
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        // --- NUEVA VALIDACIÓN ---
        if (apellidoMaterno == null || apellidoMaterno.isBlank()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacío.");
        }
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        // (Validación existente)
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        // (Validación existente)
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        // --- NUEVA VALIDACIÓN ---
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        // Validación simple de formato
        if (!correo.contains("@") || !correo.contains(".")) {
             throw new IllegalArgumentException("El formato del correo no es válido.");
        }
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        // --- NUEVA VALIDACIÓN ---
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        // --- NUEVA VALIDACIÓN ---
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }
    
    public abstract void mostrarDatos();
}
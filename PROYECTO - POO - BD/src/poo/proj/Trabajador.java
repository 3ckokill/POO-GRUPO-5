package poo.proj;

public abstract class Trabajador {

    protected int id;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipoDocumento;
    protected String numeroDocumento;
    protected double sueldoBase;
    protected int horasExtra;
    protected double pagoPorHoraExtra;

    public Trabajador(String nombre, String apellidoPaterno, String apellidoMaterno, 
                      String tipoDocumento, String numeroDocumento, double sueldoBase, 
                      int horasExtra, double pagoPorHoraExtra) {
        // Usamos los SETTERS aquí para que las validaciones se ejecuten al crear el objeto
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        setTipoDocumento(tipoDocumento);     // Valida DNI vs CE
        setNumeroDocumento(numeroDocumento); // Valida longitud (8 o 9)
        setSueldoBase(sueldoBase);           // Valida positivo
        this.horasExtra = horasExtra;
        this.pagoPorHoraExtra = pagoPorHoraExtra;
    }

    // --- GETTERS Y SETTERS ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getTipoDocumento() { return tipoDocumento; }

    // VALIDACIÓN RECUPERADA
    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento.equalsIgnoreCase("DNI") || tipoDocumento.equalsIgnoreCase("CE")) {
            this.tipoDocumento = tipoDocumento.toUpperCase(); // Lo guardamos en mayúsculas
        } else {
            throw new IllegalArgumentException("Tipo de documento no válido (Use DNI o CE)");
        }
    }

    public String getNumeroDocumento() { return numeroDocumento; }

    // VALIDACIÓN RECUPERADA
    public void setNumeroDocumento(String numeroDocumento) {
        if (this.tipoDocumento == null) {
            // Esto evita errores si intentas poner el número antes que el tipo
            // Por defecto asumiremos validación DNI si es nulo, o lanzamos error.
            throw new IllegalStateException("Primero asigne el tipo de documento.");
        }

        if ("DNI".equals(this.tipoDocumento) && numeroDocumento.length() == 8) {
            this.numeroDocumento = numeroDocumento;
        } else if ("CE".equals(this.tipoDocumento) && numeroDocumento.length() == 9) {
            this.numeroDocumento = numeroDocumento;
        } else {
            throw new IllegalArgumentException("Longitud de documento incorrecta para " + this.tipoDocumento);
        }
    }

    public double getSueldoBase() { return sueldoBase; }

    // VALIDACIÓN RECUPERADA
    public void setSueldoBase(double sueldoBase) {
        if (sueldoBase <= 0) {
            throw new IllegalArgumentException("El sueldo base debe ser mayor a 0");
        }
        this.sueldoBase = sueldoBase;
    }

    public int getHorasExtra() { return horasExtra; }
    public void setHorasExtra(int horasExtra) { this.horasExtra = horasExtra; }

    public double getPagoPorHoraExtra() { return pagoPorHoraExtra; }
    public void setPagoPorHoraExtra(double pagoPorHoraExtra) { this.pagoPorHoraExtra = pagoPorHoraExtra; }

    public abstract double calcularSueldoTotal();
    
    public String nombreCompleto(){
        return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
    }
}
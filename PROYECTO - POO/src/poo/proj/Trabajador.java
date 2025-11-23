package poo.proj;

public abstract class Trabajador {

    private static int contadorId = 1;

    protected int id;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipoDocumento;
    protected String numeroDocumento;
    protected double sueldoBase;
    protected int horasExtra;
    protected double pagoPorHoraExtra;

    public Trabajador(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, double sueldoBase, int horasExtra, double pagoPorHoraExtra) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        this.numeroDocumento = numeroDocumento;
        this.sueldoBase = sueldoBase;
        this.horasExtra = horasExtra;
        this.pagoPorHoraExtra = pagoPorHoraExtra;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Trabajador.contadorId = contadorId;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if(tipoDocumento.equalsIgnoreCase("DNI")|| tipoDocumento.equalsIgnoreCase("CE")){
                this.tipoDocumento = tipoDocumento;
        }
        else {
            System.out.println("Tipo de documento no valido");
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if("DNI".equalsIgnoreCase(this.numeroDocumento)&&numeroDocumento.length() == 8 ){
             this.numeroDocumento = numeroDocumento;
        }
        else if("CE".equalsIgnoreCase(this.numeroDocumento)&&numeroDocumento.length() == 9 ){
             this.numeroDocumento = numeroDocumento;
        }
        else {
            System.out.println("Numero de documento no valido");
        }
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public double getPagoPorHoraExtra() {
        return pagoPorHoraExtra;
    }

    public void setPagoPorHoraExtra(double pagoPorHoraExtra) {
        this.pagoPorHoraExtra = pagoPorHoraExtra;
    }
     public double calcularSueldoTotal() {
        return sueldoBase + (horasExtra * pagoPorHoraExtra);
    }
    
    public String nombreCompleto(){
        return "Nombre: " + this.nombre +" "+ this.apellidoPaterno + " " + this.apellidoMaterno;
    }
    
    public void mostrarDatos(){
        
        System.out.println("ID: " + id);
        nombreCompleto();
        System.out.println("Tipo de Documento: " + tipoDocumento );
        System.out.println("Numero de Documento: " + numeroDocumento );
        System.out.println("Sueldo Base: " + sueldoBase);
        System.out.println("Horas Extra: " + horasExtra);
        System.out.println("Pago por Hora Extra: " + pagoPorHoraExtra);
        System.out.println("Sueldo Total: " + calcularSueldoTotal());
    }
     
    
}

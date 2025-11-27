package poo.proj;


public abstract class Trabajador {


private static int contadorId = 1;


protected String password;
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
                  int horasExtra, double pagoPorHoraExtra, String password) {
    
    this.password = password;
    this.id = contadorId++;
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    setTipoDocumento(tipoDocumento);
    setNumeroDocumento(numeroDocumento);
    this.sueldoBase = sueldoBase;
    this.horasExtra = horasExtra;
    this.pagoPorHoraExtra = pagoPorHoraExtra;
}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
if(tipoDocumento == null) throw new IllegalArgumentException("Tipo de documento no puede ser nulo");
if(tipoDocumento.equalsIgnoreCase("DNI")|| tipoDocumento.equalsIgnoreCase("CE")){
this.tipoDocumento = tipoDocumento;
}
else {
throw new IllegalArgumentException("Tipo de documento no válido");
}
}
public String getNumeroDocumento() {
return numeroDocumento;
}


public void setNumeroDocumento(String numeroDocumento) {


if (this.tipoDocumento == null) {
throw new IllegalStateException("Primero asigna un tipo de documento válido");
}


if("DNI".equalsIgnoreCase(this.tipoDocumento)&&numeroDocumento.length() == 8 ){
this.numeroDocumento = numeroDocumento;
}
else if("CE".equalsIgnoreCase(this.tipoDocumento)&&numeroDocumento.length() == 9 ){
this.numeroDocumento = numeroDocumento;
}
else {
throw new IllegalArgumentException("Número de documento no válido");
}
}


public double getSueldoBase() {
return sueldoBase;
}


public void setSueldoBase(double sueldoBase) {
if (sueldoBase <= 0) {
throw new IllegalArgumentException("Sueldo base no puede ser negativo o cero");
}
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
System.out.println("Nombre : "+nombreCompleto());
System.out.println("Tipo de Documento: " + tipoDocumento );
System.out.println("Numero de Documento: " + numeroDocumento );
System.out.println("Sueldo Base: " + sueldoBase);
System.out.println("Horas Extra: " + horasExtra);
System.out.println("Pago por Hora Extra: " + pagoPorHoraExtra);
System.out.println("Sueldo Total: " + calcularSueldoTotal());
}
}

package poo.proj;

public abstract class Trabajador extends Persona {

    protected String cargo;
    protected double salario;
    protected String fechaIngreso;

    public Trabajador(String cargo, double salario, String fechaIngreso, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void registrarAsistencia() {
        System.out.println(getNombreCompleto() + " ha registrado su asistencia.");
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=== Datos del Empleado ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario: S/." + salario);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
    }
    
    
    
    

}
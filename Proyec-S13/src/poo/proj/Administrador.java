package poo.proj;


public class Administrador extends Trabajador {

    static final double SUELDO_BASE = 2000.0;

    public Administrador(String fechaIngreso, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super("Administrador", SUELDO_BASE, fechaIngreso, id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
    }

    public void registrarEmpleado() {
        
        System.out.println("El administrador " + getNombreCompleto() + " está registrando un nuevo empleado...");
    }

    public void generarReporte() {
        System.out.println("El administrador " + getNombreCompleto() + " está generando un reporte del sistema...");
    }

    @Override
    public void mostrarDatos() {
       System.out.println("=== Datos del Administrador ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario fijo: S/." + salario);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("===============================");
    }
}
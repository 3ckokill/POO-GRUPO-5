package poo.proj;


public class Gerente extends Trabajador {

    public static final double SUELDO_BASE = 2500.0;

    public Gerente(String fechaIngreso, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super("Gerente", SUELDO_BASE, fechaIngreso, id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
    }
    public void revisarReportes() {
        
        System.out.println("El gerente " + getNombreCompleto() + " está revisando los reportes del sistema...");
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== Datos del Gerente ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario fijo: S/." + salario);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("==========================");
    }
}

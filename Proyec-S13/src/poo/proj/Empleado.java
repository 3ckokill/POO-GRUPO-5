package poo.proj;


public class  Empleado extends Trabajador {
        
    static final double SUELDO_BASE = 1130.00;

    public Empleado( String fechaIngreso, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super("Empleado", SUELDO_BASE, fechaIngreso, id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
    }
    
    public void atenderCliente(String nombreCliente) {
        System.out.println("El empleado " + getNombreCompleto() + " está atendiendo al cliente " + nombreCliente + ".");
    }
    public void registrarVenta(String producto, int cantidad, double total) {
        System.out.println("Venta registrada por " + getNombreCompleto() + ":");
        System.out.println("- Producto: " + producto);
        System.out.println("- Cantidad: " + cantidad);
        System.out.println("- Total: S/." + total);
        System.out.println("--------------------------------------");
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== Datos del Empleado de Ventas ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario fijo: S/." + salario);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("====================================");
    }
}

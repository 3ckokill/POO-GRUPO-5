package poo.proj;

public class EmpleadoVentas extends Trabajador {


    public EmpleadoVentas(String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, 
            double sueldoBase, int horasExtra, double pagoPorHoraExtra) {
        
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, sueldoBase, horasExtra, pagoPorHoraExtra);
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("----Empleado de Ventas----");
        super.mostrarDatos();
    }

void registrarVenta(Controlador controlador, String nombreProd, int cant) {

    if (controlador == null) {
        throw new IllegalArgumentException("Controlador no puede ser nulo");
    }

    // Buscar producto por nombre
    Producto prod = controlador.buscarProductoPorNombre(nombreProd);

    if (prod == null) {
        throw new IllegalArgumentException("Producto '" + nombreProd + "' no encontrado");
    }

    if (cant <= 0) {
        throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
    }

    if (prod.getStock() < cant) {
        throw new IllegalArgumentException(
            "Stock insuficiente. Stock disponible: " + prod.getStock()
        );
    }

    // Crear la venta
    Venta venta = new Venta(prod, cant);

    // Actualizar stock
    prod.setStock(prod.getStock() - cant);

    // Registrar venta en el controlador
    controlador.registrarVenta(venta);

    System.out.println(
        "Venta registrada por " + nombreCompleto() +
        " -> Producto: " + prod.getNombre() +
        ", Cantidad: " + cant +
        ", Total: " + venta.getTotal()
    );
}

}

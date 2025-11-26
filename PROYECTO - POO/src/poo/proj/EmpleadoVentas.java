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

void registrarVenta(Controlador controlador, int idProd, int cant) {
    if (controlador == null) {
        throw new IllegalArgumentException("Controlador no puede ser nulo");
    }
    Producto prod = controlador.buscarProductoPorId(idProd);
    if (prod == null) {
        throw new IllegalArgumentException("Producto con ID " + idProd + " no encontrado");
    }

    if (prod.getStock() < cant) {
        throw new IllegalArgumentException("Stock insuficiente. Stock disponible: " + prod.getStock());
    }

    // Crear la venta
    Venta venta = new Venta(prod, cant);

    // Actualizar stock
    prod.setStock(prod.getStock() - cant);

    // Registrar venta en el controlador
    controlador.registrarVenta(venta);

    System.out.println("Venta registrada por " + nombreCompleto() + " -> Producto: " + prod.getNombre() + 
                       ", Cantidad: " + cant + ", Total: " + venta.getTotal());
}

}

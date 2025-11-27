package poo.proj;

public class EmpleadoVentas extends Trabajador {

    public EmpleadoVentas(String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, double sueldoBase, int horasExtra, double pagoPorHoraExtra, String password) {
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, sueldoBase, horasExtra, pagoPorHoraExtra, password);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("----Empleado de Ventas----");
        super.mostrarDatos();
    }

    // AQUI ESTA LA MAGIA QUE FALTABA
    // YA NO PIDE CLIENTE NI DNI
    public void registrarVenta(Controlador controlador, Producto prod, int cant) {
        
        // Validamos stock nuevamente por seguridad
        if (prod.getStock() < cant) {
            throw new IllegalArgumentException("No hay suficiente stock.");
        }
        
        // Actualizar stock y registrar
        prod.setStock(prod.getStock() - cant);
        
        Venta v = new Venta(prod, cant);
        controlador.registrarVenta(v);
        
        System.out.println("Venta registrada. Total: " + v.getTotal());
    }
}
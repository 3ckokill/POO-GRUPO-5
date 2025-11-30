package poo.proj;

public class Administrador extends Trabajador {

    public Administrador(String nombre, String apellidoPaterno, String apellidoMaterno, 
            String tipoDocumento, String numeroDocumento, double sueldoBase, int horasExtra, double pagoPorHoraExtra) {
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, sueldoBase, horasExtra, pagoPorHoraExtra);
    }
    
    @Override
    public double calcularSueldoTotal() {
        return getSueldoBase();
    }   

    // Estos métodos ahora delegan al controlador que conecta con la BD
    public void registrarTrabajador(Controlador controlador, String nombre, String apPaterno, String apMaterno, String tipoDocumento, String numeroDocumento, double sueldoBase) {
        EmpleadoVentas emp = new EmpleadoVentas(nombre, apPaterno, apMaterno, tipoDocumento, numeroDocumento, sueldoBase, 0, 0);
        controlador.registrarEmpleado(emp);
    }

    public void registrarProducto(Controlador controlador, String nombre, double precio, int stock) {
        Producto p = new Producto(nombre, precio, stock);
        controlador.registrarProducto(p);
    }
}
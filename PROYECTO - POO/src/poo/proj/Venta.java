package poo.proj;

public class Venta {

    private static int contadorId = 1;

    private int id;
    private Producto producto;
    private int cantidad;
    private double total;
    private String nombreCliente;
    private String dniCliente;
    private Trabajador trabajador; // quien hizo la venta

    public Venta(Producto producto, int cantidad, 
                 String nombreCliente, String dniCliente,
                 Trabajador trabajador) {

        this.id = contadorId++;
        this.producto = producto;
        this.cantidad = cantidad;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.trabajador = trabajador;

        this.total = producto.getPrecio() * cantidad;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Venta.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if(cantidad > 0){
        this.cantidad = cantidad;
    } else {
        System.out.println("Cantidad inválida");
    }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public void mostrarVenta() {
    System.out.println("ID Venta: " + id);
    System.out.println("Producto: " + producto.getNombre());
    System.out.println("Cantidad: " + cantidad);
    System.out.println("Total: " + total);
    System.out.println("Cliente: " + nombreCliente + " - DNI: " + dniCliente);
    if (trabajador != null) {
        System.out.println("Atendido por: " + trabajador.nombreCompleto());
    } else {
        System.out.println("Atendido por: (no especificado)");
    }
}

}

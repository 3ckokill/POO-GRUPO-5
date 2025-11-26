package poo.proj;

public class Venta {

    private static int contadorId = 1;

    private int id;
    private Producto producto;
    private int cantidad;
    private double total;

    public Venta(Producto producto, int cantidad) {
        this.id = contadorId++;
        setProducto(producto);
        setCantidad(cantidad);
        calcularTotal();
    }

    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    private void calcularTotal() {
        this.total = producto.getPrecio() * cantidad;
    }

    public void mostrarDatos() {
        System.out.println("ID Venta: " + id);
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Precio Unitario: " + producto.getPrecio());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total: " + total);
    }
}
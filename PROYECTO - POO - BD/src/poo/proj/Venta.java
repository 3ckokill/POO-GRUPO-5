package poo.proj;

public class Venta {
    private int id;
    private Producto producto;
    private int cantidad;
    private double total;

    public Venta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = producto.getPrecio() * cantidad;
    }

    public int getId() { return id; } // id de BD si fuera necesario recuperar historial
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }

    public void mostrarDatos() {
        System.out.println("Venta: " + producto.getNombre() + " x" + cantidad + " = " + total);
    }
}
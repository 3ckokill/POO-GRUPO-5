package poo.proj;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public int getStock() { return stock; }
    
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id + " | " + nombre + " | S/." + precio + " | Stock: " + stock);
    }
    
    // Importante para que el ComboBox muestre el nombre y no el código raro
    @Override
    public String toString() {
        return nombre;
    }
}
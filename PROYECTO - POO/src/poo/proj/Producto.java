package poo.proj;

public class Producto {

    private static int contador = 1;

    private int idProducto;
    private String nombre;
    private double precio;

    public Producto(String nombre, String desc, double precio) {
        this.idProducto = contador++;
        setNombre(nombre);
        setPrecio(precio);
    }

    public int getIdProducto() { return idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre de producto inválido");
        this.nombre = nombre;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if(precio < 0)
            throw new IllegalArgumentException("Precio inválido");
        this.precio = precio;
    }

    public void mostrarDatos() {
        System.out.println(idProducto + " - " + nombre + " (S/." + precio + ")");
    }
}

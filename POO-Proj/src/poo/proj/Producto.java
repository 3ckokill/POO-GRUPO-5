package poo.proj;

import java.util.ArrayList;

public class Producto {

    private static int contador = 1;

    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<Material> materiales;

    // Constructor básico
    public Producto(String nombre, double precio) {
        this.idProducto = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = "";
        this.materiales = new ArrayList<>();
    }

    // Constructor completo
    public Producto(String nombre, String descripcion, double precio) {
        this(nombre, precio);
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("El precio no puede ser negativo.");
        }
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }

    // Métodos funcionales
    public void agregarMaterial(Material material) {
        if (material != null) {
            materiales.add(material);
        }
    }

    public void eliminarMaterial(Material material) {
        materiales.remove(material);
    }

    public double calcularCostoProduccion() {
        double costo = 0.0;
        for (Material m : materiales) {
            costo += m.getCostoUnitario();
        }
        return costo;
    }

    public void mostrarDatos() {
        System.out.println("=== PRODUCTO ===");
        System.out.println("ID: " + idProducto);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: S/." + precio);
        System.out.println("Materiales utilizados:");
        if (materiales.isEmpty()) {
            System.out.println("   - Ninguno");
        } else {
            for (Material m : materiales) {
                System.out.println("   - " + m.getNombre() + " (S/." + m.getCostoUnitario() + ")");
            }
        }
        System.out.println("===================");
    }
}

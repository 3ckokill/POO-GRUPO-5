package poo.proj;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private static int contador = 1;

    private int idPedido;
    private Cliente cliente;
    private Trabajador empleadoResponsable; // <-- cambiado a Trabajador
    private ArrayList<Producto> productos;
    private Date fechaPedido;
    private String estado; // Pendiente, En proceso, Entregado
    private double total;

    // Constructor básico
    public Pedido(Cliente cliente, Trabajador empleadoResponsable, Date fechaPedido) {
        this.idPedido = contador++;
        this.cliente = cliente;
        this.empleadoResponsable = empleadoResponsable;
        this.fechaPedido = fechaPedido;
        this.estado = "Pendiente";
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trabajador getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Trabajador empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
        recalcularTotal();
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Métodos funcionales
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
            recalcularTotal();
        }
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        recalcularTotal();
    }

    private void recalcularTotal() {
        double suma = 0.0;
        for (Producto p : productos) {
            suma += p.getPrecio();
        }
        this.total = suma;
    }

    public void cambiarEstado(String nuevoEstado) {
        if (nuevoEstado.equalsIgnoreCase("Pendiente")
                || nuevoEstado.equalsIgnoreCase("En proceso")
                || nuevoEstado.equalsIgnoreCase("Entregado")) {
            this.estado = nuevoEstado;
        } else {
            System.out.println("Estado no válido.");
        }
    }

    public void mostrarDatos() {
        System.out.println("=== PEDIDO ===");
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNombre() : "No asignado"));
        System.out.println("Trabajador responsable: " + (empleadoResponsable != null ? empleadoResponsable.getNombre() : "No asignado"));
        System.out.println("Fecha: " + fechaPedido);
        System.out.println("Estado: " + estado);
        System.out.println("Productos en el pedido:");
        if (productos.isEmpty()) {
            System.out.println("   - Ninguno");
        } else {
            for (Producto p : productos) {
                System.out.println("   - " + p.getNombre() + " (S/." + p.getPrecio() + ")");
            }
        }
        System.out.println("Total: S/." + total);
        System.out.println("====================");
    }
}

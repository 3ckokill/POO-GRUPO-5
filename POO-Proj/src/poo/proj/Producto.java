package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private String estado;
    private double costoProduccion;

    private List<Material> materiales;

    public Producto() {
        this.materiales = new ArrayList<>();
    }

    public void registrarProducto() {
    }

    public void modificarProducto() {
    }

    public void eliminarProducto() {
    }

    public void listarProductos() {
    }

    public void asociarMateriales() {
    }

    public void calcularCostoProduccion() {
    }

    public void activarDesactivarProducto() {
    }

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

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoProduccion() {
        return costoProduccion;
    }

    public void setCostoProduccion(double costoProduccion) {
        this.costoProduccion = costoProduccion;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }
}

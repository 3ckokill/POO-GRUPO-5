package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Cliente> listaClientes;
    private List<Empleado> listaEmpleados;
    private List<Producto> listaProductos;
    private List<Pedido> listaPedidos;

    public Controlador() {
        this.listaClientes = new ArrayList<>();
        this.listaEmpleados = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }

    // Métodos del diagrama
    public void iniciarSesion() {
    }

    public void gestionarClientes() {
    }

    public void gestionarEmpleados() {
    }

    public void gestionarProductos() {
    }

    public void gestionarPedidos() {
    }

    public void generarReportes() {
    }

    public void guardarDatos() {
    }

    public void cargarDatos() {
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
}

package poo.proj;

import java.util.ArrayList;

public class Controlador {
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<EmpleadoVentas> empleados = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    
    // Métodos para registrar
    public void registrarEmpleado(EmpleadoVentas emp) {
        empleados.add(emp);
    }
    
    public void registrarAdministrador(Administrador admin) {
        administradores.add(admin);
    }
    
    public void registrarProducto(Producto prod) {
        productos.add(prod);
    }
    
    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }
    
    // Métodos para obtener listas
    public ArrayList<EmpleadoVentas> getEmpleados() {
        return empleados;
    }
    
    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public ArrayList<Venta> getVentas() {
        return ventas;
    }
    
    // Métodos de búsqueda
    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public EmpleadoVentas buscarEmpleadoPorId(int id) {
        for (EmpleadoVentas e : empleados) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    
    public Administrador buscarAdministradorPorId(int id) {
        for (Administrador a : administradores) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
    
    // Métodos para mostrar (consola)
    public void mostrarEmpleados() {
        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        for (EmpleadoVentas e : empleados) {
            e.mostrarDatos();
            System.out.println("----------------------");
        }
    }
    
    public void verProductos() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        for (Producto p : productos) {
            p.mostrarDatos();
            System.out.println("----------------------");
        }
    }
    
    public void verTrabajadores() {
        System.out.println("\n=== LISTA DE TRABAJADORES ===");
        System.out.println("--- Empleados ---");
        for (EmpleadoVentas e : empleados) {
            e.mostrarDatos();
            System.out.println("----------------------");
        }
        System.out.println("--- Administradores ---");
        for (Administrador a : administradores) {
            a.mostrarDatos();
            System.out.println("----------------------");
        }
    }
    
    public void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        for (Venta v : ventas) {
            v.mostrarVenta();
            System.out.println("----------------------");
        }
    }
}

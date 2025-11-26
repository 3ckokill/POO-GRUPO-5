package poo.proj;

import java.util.ArrayList;

public class Controlador {

private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<EmpleadoVentas> empleados = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    
    public ArrayList<Producto> getProductos() {
    return productos;
    }

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
    public void mostrarEmpleados() {
        for (EmpleadoVentas e : empleados) {
            e.mostrarDatos();
            System.out.println("----------------------");
        }
    }

    public void mostrarVentas() {
        for (Venta v : ventas) {
            v.mostrarDatos();
            System.out.println("----------------------");
        }
    }

    public void verProductos() {
        System.out.println("=== LISTA DE PRODUCTOS ===");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : productos) {
            p.mostrarDatos();
            System.out.println("----------------------");
        }
    }
    public Producto buscarProductoPorNombre(String nombre) {
    for (Producto p : productos) {
        if (p.getNombre().equalsIgnoreCase(nombre)) {
            return p;
        }
    }
    return null;
}

    public Administrador validarAdministrador(String nombre, String apPaterno, 
                                          String apMaterno, String nroDoc) {

    for (Administrador admin : administradores) {

        if (admin.getNombre().equalsIgnoreCase(nombre)
            && admin.getApellidoPaterno().equalsIgnoreCase(apPaterno)
            && admin.getApellidoMaterno().equalsIgnoreCase(apMaterno)
            && admin.getNumeroDocumento().equalsIgnoreCase(nroDoc)) {

            return admin;
        }
    }

    return null;
   } 

    public EmpleadoVentas validarEmpleado(String nombre, String apPaterno, 
                                      String apMaterno, String nroDoc) {

    for (EmpleadoVentas emp : empleados) {

        if (emp.getNombre().equalsIgnoreCase(nombre)
            && emp.getApellidoPaterno().equalsIgnoreCase(apPaterno)
            && emp.getApellidoMaterno().equalsIgnoreCase(apMaterno)
            && emp.getNumeroDocumento().equalsIgnoreCase(nroDoc)) {

            return emp;
        }
    }

    return null;
}
}

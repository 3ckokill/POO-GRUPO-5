package poo.proj;

import java.util.ArrayList;

public class Controlador {

    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<EmpleadoVentas> empleados = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();

    // Métodos para registrar, listar, buscar, etc.

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

    // Más métodos según necesites

}

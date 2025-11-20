package poo.proj;

import java.util.ArrayList;

public class Controlador {

    private ArrayList<Cliente> clientes;
    private ArrayList<Trabajador> trabajadores;
    private ArrayList<Producto> productos;
    private ArrayList<Venta> pedidos;

    // Constructor
    public Controlador() {
        clientes = new ArrayList<>();
        trabajadores = new ArrayList<>();
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        
       cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {

        clientes.add(new Cliente(0.10, "Habitual", 1,
                "Juan", "Lopez", "Perez", "DNI", "12345678",
                "juan@gmail.com", "987654321", "Av. Lima"));

        clientes.add(new Cliente(0, "Nuevo", 2,
                "Ana", "Torres", "Sanchez", "DNI", "87654321",
                "ana@gmail.com", "999888777", "Jr. Arequipa"));

        trabajadores.add(new Empleado("2024-02-01", 2,
                "Pedro", "Soto", "Luna", "DNI", "22334455",
                "pedro@empresa.com", "944556677", "Jr. Callao"));

        Producto volantes = new Producto("Volantes A5", "Volantes color", 0.50);

        productos.add(volantes);
    }
    

    //  MÉTODOS PARA CLIENTES 
    public void registrarCliente(Cliente c) {
        clientes.add(c);
        System.out.println("Cliente registrado correctamente: " + c.getNombre());
    }

    public Cliente buscarClientePorDNI(String dni) {
        for (Cliente c : clientes) {
            if (c.getNumeroDocumento().equals(dni)) {
                return c;
            }
        }
        System.out.println("Cliente no encontrado.");
        return null;
    }

    public void listarClientes() {
        System.out.println("=== LISTA DE CLIENTES ===");
        for (Cliente c : clientes) {
            c.mostrarDatos();
        }
    }

    public void eliminarCliente(String dni) {
        Cliente cliente = buscarClientePorDNI(dni);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado correctamente.");
        }
    }

    // MÉTODOS PARA TRABAJADORES
    public void registrarEmpleado(Trabajador e) {
        trabajadores.add(e);
        System.out.println("Empleado registrado correctamente: " + e.getNombre());
    }

    public void listarTrabajadores() {
        System.out.println("=== LISTA DE EMPLEADOS ===");
        for (Trabajador e : trabajadores) {
            e.mostrarDatos();
        }
    }

    //  MÉTODOS PARA PRODUCTOS 
    public void registrarProducto(Producto p) {
        productos.add(p);
        System.out.println("Producto registrado correctamente: " + p.getNombre());
    }

    public void listarProductos() {
        System.out.println("=== CATÁLOGO DE PRODUCTOS ===");
        for (Producto p : productos) {
            p.mostrarDatos();
        }
    }

    public void registrarPedido(Venta p) {
        pedidos.add(p);
        System.out.println("Pedido registrado correctamente. ID: " + p.getIdPedido());
    }

    public void listarPedidos() {
        System.out.println("=== LISTA DE PEDIDOS ===");
        for (Venta p : pedidos) {
            p.mostrarDatos();
        }
    }

    public Venta buscarPedidoPorId(int id) {
        for (Venta p : pedidos) {
            if (p.getIdPedido() == id) {
                return p;
            }
        }
        System.out.println("Pedido no encontrado.");
        return null;
    }
    
    // GETTER PARA LA LISTA
    
    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    // MÉTODOS AUXILIARES 
    public int cantidadClientes() {
        return clientes.size();
    }

    public int cantidadTrabajadores() {
        return trabajadores.size();
    }

    public int cantidadPedidos() {
        return pedidos.size();
    }
}

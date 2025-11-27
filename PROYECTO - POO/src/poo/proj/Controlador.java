package poo.proj;


import java.util.ArrayList;


public class Controlador {


    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<EmpleadoVentas> empleados = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();


// -------------------------
// REGISTROS
// -------------------------
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


// -------------------------
// MOSTRAR DATOS
// -------------------------
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


// -------------------------
// BÚSQUEDAS ÚTILES
// -------------------------
public Producto buscarProductoPorId(int id) {
for (Producto p : productos) {
if (p.getId() == id) return p;
}
return null;
}
public Trabajador login(String dni, String password) {
        // 1. Buscar en Administradores
        for (Administrador a : administradores) {
            if (a.getNumeroDocumento().equals(dni) && a.getPassword().equals(password)) {
                return a;
            }
        }
        // 2. Buscar en Empleados de Ventas
        for (EmpleadoVentas e : empleados) {
            if (e.getNumeroDocumento().equals(dni) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null; // Si no encuentra a nadie
    }

// NUEVO: Para llenar el ComboBox en la interfaz
    public ArrayList<Producto> getListaProductos() {
        return productos;
    }

    // Búsqueda inteligente (que contenga el texto y no distinga mayúsculas)
    public Producto buscarProductoPorNombre(String textoBusqueda) {
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase())) {
                return p;
            }
        }
        return null; // No encontrado
    }
    // Método para filtrar productos (Autocompletado)
    public ArrayList<Producto> buscarProductosFiltro(String textoBusqueda) {
        ArrayList<Producto> resultado = new ArrayList<>();
        String texto = textoBusqueda.toLowerCase();
        
        for (Producto p : productos) {
            // Buscamos si el nombre contiene el texto escrito
            if (p.getNombre().toLowerCase().contains(texto)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    
    // --- MÉTODOS PARA REPORTES ---
    public ArrayList<EmpleadoVentas> getListaEmpleados() {
        return empleados;
    }
    
    public ArrayList<Venta> getListaVentas() {
        return ventas;
    }
    
    // Método para validar si un DNI ya existe (para no repetir empleados)
    public boolean existeEmpleado(String dni) {
        for (Administrador a : administradores) if (a.getNumeroDocumento().equals(dni)) return true;
        for (EmpleadoVentas e : empleados) if (e.getNumeroDocumento().equals(dni)) return true;
        return false;
    }
}

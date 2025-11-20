package poo.proj;

import java.util.Date;
import java.util.Scanner;

public class Vista {

    private static Scanner sc = new Scanner(System.in);
    private static Controlador control = new Controlador();

    public void iniciarSistema() {
    int opcion;
    do {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE IMPRENTA EDU =====");
        System.out.println("1. Gestión de Clientes");
        System.out.println("2. Gestión de Trabajadores");
        System.out.println("3. Gestión de Productos");
        System.out.println("4. Gestión de Materiales");
        System.out.println("5. Gestión de Pedidos");
        System.out.println("6. Mostrar Reporte General");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = leerEntero();

        switch (opcion) {
            case 1 -> menuClientes();
            case 2 -> menuEmpleados();
            case 3 -> menuProductos();
            case 4 -> menuMateriales();
            case 5 -> menuPedidos();
            case 6 -> mostrarReporteGeneral();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }

    } while (opcion != 0);
}


    // ===== MENÚ CLIENTES =====
    public static void menuClientes() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarCliente();
                case 2 -> control.listarClientes();
                case 3 -> eliminarCliente();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    public static void registrarCliente() {
    System.out.println("\n=== Registro de Cliente ===");
    System.out.print("Nombre: ");
    String nombre = sc.nextLine();
    System.out.print("Apellido Paterno: ");
    String apPat = sc.nextLine();
    System.out.print("Apellido Materno: ");
    String apMat = sc.nextLine();
    System.out.print("Tipo de Documento (DNI/CE): ");
    String tipoDoc = sc.nextLine().trim();

    while (!tipoDoc.equalsIgnoreCase("DNI") && !tipoDoc.equalsIgnoreCase("CE")) {
        System.out.println("Error: Tipo de documento no válido. Ingrese nuevamente (DNI o CE): ");
        tipoDoc = sc.nextLine().trim();
    }
    System.out.print("Número de Documento: ");
    String numDoc = sc.nextLine().trim();

    if (tipoDoc.equalsIgnoreCase("DNI")) {
    while (numDoc.length() != 8) {
        System.out.println("Error: El DNI debe tener 8 dígitos. Ingrese nuevamente: ");
        numDoc = sc.nextLine().trim();
        }
    } 
    System.out.print("Correo: ");
    String correo = sc.nextLine();
    System.out.print("Teléfono: ");
    String telefono = sc.nextLine();
    System.out.print("Dirección: ");
    String direccion = sc.nextLine();
    System.out.print("Tipo de cliente (Habitual/Nuevo/Empresa): ");
    String tipoCliente = sc.nextLine();
    System.out.print("Descuento (ej: 0.10 para 10% o 0 para ninguno): ");
    double descuento = leerDouble();

    // el id se genera de forma simple, podrías mejorarlo con un contador global si prefieres
    int id = control.cantidadClientes() + 1;

    Cliente c = new Cliente(descuento, tipoCliente, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
    control.registrarCliente(c);
}

    public static void eliminarCliente() {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = sc.nextLine();
        control.eliminarCliente(dni);
    }

    // ===== MENÚ EMPLEADOS =====
    public static void menuEmpleados() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarTrabajador();
                case 2 -> control.listarTrabajadores();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

   public static void registrarTrabajador() {
    System.out.println("\n=== Registro de Trabajador ===");

    System.out.print("Nombre: ");
    String nombre = sc.nextLine();
    System.out.print("Apellido Paterno: ");
    String apPat = sc.nextLine();
    System.out.print("Apellido Materno: ");
    String apMat = sc.nextLine();
    System.out.print("Ingrese tipo de documento (DNI o CE): ");
    String tipoDoc = sc.nextLine().trim();

    while (!tipoDoc.equalsIgnoreCase("DNI") && !tipoDoc.equalsIgnoreCase("CE")) {
        System.out.println("Error: Tipo de documento no válido. Ingrese nuevamente (DNI o CE): ");
        tipoDoc = sc.nextLine().trim();
    }
    System.out.print("Ingrese número de documento: ");
    String numDoc = sc.nextLine().trim();

    if (tipoDoc.equalsIgnoreCase("DNI")) {
    while (numDoc.length() != 8) {
        System.out.println("Error: El DNI debe tener 8 dígitos. Ingrese nuevamente: ");
        numDoc = sc.nextLine().trim();
        }
    }  
    else {
    while (numDoc.length() < 9 || numDoc.length() > 12) {
        System.out.println("Error: El carné de extranjería debe tener entre 9 y 12 caracteres. Ingrese nuevamente: ");
        numDoc = sc.nextLine().trim();
        }
    }
    System.out.print("Correo: ");
    String correo = sc.nextLine();
    System.out.print("Teléfono: ");
    String telefono = sc.nextLine();
    System.out.print("Dirección: ");
    String direccion = sc.nextLine();
    System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
    String fechaIngreso = sc.nextLine();

    System.out.println("Seleccione el tipo de trabajador:");
    System.out.println("1. Administrador");
    System.out.println("2. Empleado de Ventas");
    System.out.print("Opción: ");
    int tipo = leerEntero();
    
    int id = control.cantidadTrabajadores() + 1;
    Trabajador t = null;

    switch (tipo) {
        case 1 -> t = new Administrador( fechaIngreso, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
        case 2 -> t = new Empleado(fechaIngreso, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
        default -> {
            System.out.println("Tipo no válido. Registro cancelado.");
            return;
        }
    }

    control.registrarEmpleado(t);
}



    // ===== MENÚ PRODUCTOS =====
    public static void menuProductos() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarProducto();
                case 2 -> control.listarProductos();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    public static void registrarProducto() {
        System.out.println("\n=== Registro de Producto ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();

        Producto p = new Producto(nombre, descripcion, precio);
        control.registrarProducto(p);
    }

    // ===== MENÚ VENTA =====
    public static void menuPedidos() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarPedido();
                case 2 -> control.listarPedidos();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    public static void registrarPedido() {
        System.out.println("\n=== Registro de Pedido ===");
        System.out.print("Ingrese DNI del cliente: ");
        String dni = sc.nextLine();
        Cliente cliente = control.buscarClientePorDNI(dni);

        if (cliente == null) {
            System.out.println("No se puede registrar pedido: cliente no encontrado.");
            return;
        }

        System.out.print("Ingrese nombre del empleado responsable: ");
        String nombreEmp = sc.nextLine();
        Trabajador empleado = null;
        for (Trabajador e : control.getTrabajadores()) {

            if (e.getNombre().equalsIgnoreCase(nombreEmp)) {
                empleado = e;
                break;
            }
        }

        if (empleado == null) {
            System.out.println("Empleado no encontrado. Pedido no registrado.");
            return;
        }

        Venta pedido = new Venta(cliente, empleado, new Date());
        control.registrarPedido(pedido);
    }

    // ===== MÉTODOS AUXILIARES =====
    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    public static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}

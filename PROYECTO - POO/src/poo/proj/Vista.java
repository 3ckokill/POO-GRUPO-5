package poo.proj;

import java.util.Date;
import java.util.Scanner;

public class Vista {

    private static Scanner sc = new Scanner(System.in);
    public static Controlador control = new Controlador();

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
        int op; // <-- 1. Declarar la variable 'op'
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Editar cliente"); // <-- Tu línea nueva
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            op = leerEntero(); // <-- 2. Leer el valor para 'op'

            switch (op) {
                case 1 -> registrarCliente();
                case 2 -> control.listarClientes();
                case 3 -> eliminarCliente();
                case 4 -> editarCliente(); // <-- Tu línea nueva
                case 0 -> {} // No hace nada, solo vuelve al menú
                default -> System.out.println("Opción inválida.");
            }

        } while (op != 0); // <-- 3. El bucle 'do-while' debe cerrar aquí
    }
        
    public static void editarCliente() {
        System.out.print("Ingrese el DNI del cliente a editar: ");
        String dni = sc.nextLine();

        // Usamos el método de búsqueda que YA ARREGLAMOS
        Cliente cliente = control.buscarClientePorDNI(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente encontrado: " + cliente.getNombreCompleto());
        System.out.println("¿Qué desea editar?");
        System.out.println("1. Teléfono");
        System.out.println("2. Dirección");
        System.out.println("3. Correo");
        System.out.println("0. Cancelar");
        int op = leerEntero();

        switch (op) {
            case 1:
                System.out.print("Ingrese el nuevo teléfono: ");
                String nuevoTelefono = sc.nextLine();
                cliente.setTelefono(nuevoTelefono); // <-- USAMOS EL SETTER
                break;
            case 2:
                System.out.print("Ingrese la nueva dirección: ");
                String nuevaDir = sc.nextLine();
                cliente.setDireccion(nuevaDir); // <-- USAMOS EL SETTER
                break;
            case 3:
                System.out.print("Ingrese el nuevo correo: ");
                String nuevoCorreo = sc.nextLine();
                cliente.setCorreo(nuevoCorreo); // <-- USAMOS EL SETTER
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida.");
        }
        System.out.println("Datos del cliente actualizados.");
    }

    public static void registrarCliente() {
        System.out.println("\n=== Registro de Cliente ===");

        try { // <-- INICIA EL BLOQUE try

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellido Paterno: ");
            String apPat = sc.nextLine();

            System.out.print("Apellido Materno: ");
            String apMat = sc.nextLine();

            // --- INICIO DE LA VALIDACIÓN (COPIADO DE registrarTrabajador) ---
            System.out.print("Tipo de Documento (DNI/CE): ");
            String tipoDoc = sc.nextLine().trim(); // .trim() quita espacios

            // Bucle para asegurar que solo sea DNI o CE
            while (!tipoDoc.equalsIgnoreCase("DNI") && !tipoDoc.equalsIgnoreCase("CE")) {
                System.out.println("Error: Tipo de documento no válido. Ingrese nuevamente (DNI o CE): ");
                tipoDoc = sc.nextLine().trim();
            }

            System.out.print("Número de Documento: ");
            String numDoc = sc.nextLine().trim();

            // Validación de la longitud del documento
            if (tipoDoc.equalsIgnoreCase("DNI")) {
                while (numDoc.length() != 8) {
                    System.out.println("Error: El DNI debe tener 8 dígitos. Ingrese nuevamente: ");
                    numDoc = sc.nextLine().trim();
                }
            } else { // Si es "CE"
                while (numDoc.length() < 9 || numDoc.length() > 12) {
                    System.out.println("Error: El carné de extranjería debe tener entre 9 y 12 caracteres. Ingrese nuevamente: ");
                    numDoc = sc.nextLine().trim();
                }
            }
            // --- FIN DE LA VALIDACIÓN ---


            // --- INICIO DE LA VERIFICACIÓN DE DUPLICADO (LO QUE PEDISTE) ---
            // Justo después de validar el DNI, buscamos si ya existe
            Cliente clienteExistente = control.buscarClientePorDNI(numDoc);

            if (clienteExistente != null) {
                System.out.println("Error: Ya existe un cliente con el DNI " + numDoc);
                System.out.println("Registro cancelado.");
                return; // <-- ESTO "CORTA" EL MÉTODO Y VUELVE AL MENÚ
            }
            // --- FIN DE LA VERIFICACIÓN ---


            // Si el método no se "cortó", es porque el DNI es nuevo y seguimos pidiendo datos:
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

            // int id = control.cantidadClientes() + 1; // Ya no es necesario, el constructor lo maneja

            // Creamos el cliente y lo registramos
            // NOTA: Asumo que el constructor de Cliente NO pide ID, como hicimos en Persona
            Cliente c = new Cliente(descuento, tipoCliente, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);

            control.registrarCliente(c); // El controlador ahora solo lo agregará

            System.out.println("¡Cliente registrado exitosamente con ID " + c.getId() + "!");

        } catch (IllegalArgumentException e) {
            // --- AQUÍ SE ATRAPA EL ERROR ---
            // Si el constructor (o un setter) lanza un error, se captura aquí
            System.out.println("Error en el registro: " + e.getMessage());
            System.out.println("Registro cancelado.");
        }
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
            System.out.println("3. Eliminar empleado"); // <-- AÑADIR ESTA LÍNEA
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarTrabajador();
                case 2 -> control.listarTrabajadores();
                case 3 -> eliminarTrabajador(); // <-- AÑADIR ESTA LÍNEA
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }
    
    public static void eliminarTrabajador() {
        System.out.print("Ingrese el DNI del trabajador a eliminar: ");
        String dni = sc.nextLine();
        control.eliminarTrabajador(dni);
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
    System.out.println("3. Gerente");
    System.out.print("Opción: ");
    int tipo = leerEntero();
    
    int id = control.cantidadTrabajadores() + 1;
    Trabajador t = null;

    switch (tipo) {
        case 1 -> t = new Administrador(fechaIngreso, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
        case 2 -> t = new Empleado(fechaIngreso, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
        case 3 -> t = new Gerente(fechaIngreso, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
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

    // ===== MENÚ MATERIALES =====
    public static void menuMateriales() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE MATERIALES ---");
            System.out.println("1. Registrar material");
            System.out.println("2. Listar materiales");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarMaterial();
                case 2 -> control.listarMateriales();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    public static void registrarMaterial() {
        System.out.println("\n=== Registro de Material ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Cantidad disponible: ");
        double cantidad = leerDouble();
        System.out.print("Unidad de medida: ");
        String unidad = sc.nextLine();
        System.out.print("Costo unitario: ");
        double costo = leerDouble();

        Material m = new Material(nombre, cantidad, unidad, costo);
        control.registrarMaterial(m);
    }

    // ===== MENÚ PEDIDOS =====
    public static void menuPedidos() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Registrar pedido nuevo");
            System.out.println("2. Listar todos los pedidos");
            System.out.println("3. Cambiar estado de un pedido"); // <-- AÑADIR
            System.out.println("4. (Añadir productos a un pedido) - (Queda pendiente, es más complejo)");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarPedido();
                case 2 -> control.listarPedidos();
                case 3 -> cambiarEstadoPedido(); // <-- AÑADIR
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }
    
    public static void cambiarEstadoPedido() {
        System.out.print("Ingrese el ID del pedido a modificar: ");
        int idPedido = leerEntero();

        // Necesitamos buscar el pedido en el controlador
        Pedido pedido = control.buscarPedidoPorId(idPedido);

        if (pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }

        System.out.println("Estado actual: " + pedido.getEstado());
        System.out.print("Ingrese el nuevo estado (Pendiente, En proceso, Entregado): ");
        String nuevoEstado = sc.nextLine();

        // El método cambiarEstado() de la clase Pedido YA HACE LA VALIDACIÓN
        pedido.cambiarEstado(nuevoEstado);

        // Verificamos si el estado se cambió (ya que el método imprime un error si es inválido)
        if (pedido.getEstado().equalsIgnoreCase(nuevoEstado)) {
             System.out.println("Estado del pedido actualizado.");
        }
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

        Pedido pedido = new Pedido(cliente, empleado, new Date());
        control.registrarPedido(pedido);
    }

    // ===== REPORTES =====
    public static void mostrarReporteGeneral() {
        System.out.println("\n=== REPORTE GENERAL ===");
        System.out.println("Clientes registrados: " + control.cantidadClientes());
        System.out.println("Empleados registrados: " + control.cantidadTrabajadores());
        System.out.println("Pedidos registrados: " + control.cantidadPedidos());
    }

    // ===== MÉTODOS AUXILIARES =====
    public static int leerEntero() {
            while (true) {
                try {
                    // Lee la línea COMPLETA y luego la convierte
                    return Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.print("Ingrese un número válido: ");
                }
            }
        }

    public static double leerDouble() {
        while (true) {
            try {
                // Lee la línea COMPLETA y luego la convierte
                return Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}

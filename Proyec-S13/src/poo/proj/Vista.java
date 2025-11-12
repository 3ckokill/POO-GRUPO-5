package poo.proj;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Vista {

    private static Scanner sc = new Scanner(System.in);
    private static Controlador control = new Controlador();

    public void iniciarSistema() {
    int opcion;
    do {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE IMPRENTA HALO =====");
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
            System.out.println("4. Editar cliente");
            System.out.println("5. Buscar cliente"); // <-- NUEVA OPCIÓN
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarCliente();
                case 2 -> control.listarClientes();
                case 3 -> eliminarCliente();
                case 4 -> editarCliente();
                case 5 -> buscarCliente(); // <-- NUEVA LLAMADA
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    public static void registrarCliente() {
        System.out.println("\n=== Registro de Cliente ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        while (nombre.isBlank()) { // <-- Validación en la VISTA
            System.out.println("Error: El nombre no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        }
        System.out.print("Apellido Paterno: ");
            String apPat = sc.nextLine();
            while (apPat.isBlank()) {
                System.out.println("Error: El apellido paterno no puede estar vacío. Ingrese nuevamente: ");
                System.out.print("Apellido Paterno: ");
                apPat = sc.nextLine();
            }

            System.out.print("Apellido Materno: ");
            String apMat = sc.nextLine();
            while (apMat.isBlank()) {
                System.out.println("Error: El apellido materno no puede estar vacío. Ingrese nuevamente: ");
                System.out.print("Apellido Materno: ");
                apMat = sc.nextLine();
            }

        // --- VALIDACIÓN DE DOCUMENTO ---
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
        } else { // Es "CE"
            while (numDoc.length() < 9 || numDoc.length() > 12) {
                System.out.println("Error: El carné de extranjería debe tener entre 9 y 12 caracteres. Ingrese nuevamente: ");
                numDoc = sc.nextLine().trim();
            }
        }
        // --- FIN VALIDACIÓN ---

        // --- VALIDACIÓN DUPLICADO (HU01.3 - Implementada antes) ---
        if (control.buscarClientePorDNI(numDoc) != null) {
            System.out.println("ERROR: Ya existe un cliente registrado con el DNI " + numDoc + ".");
            System.out.println("Registro cancelado.");
            return; // Sale del método
        }
        // --- FIN VALIDACIÓN ---

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

        int id = control.cantidadClientes() + 1;

        Cliente c = new Cliente(descuento, tipoCliente, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
        control.registrarCliente(c);
    }

    public static void eliminarCliente() {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = sc.nextLine();
        control.eliminarCliente(dni);
    }
    
    public static void editarCliente() {
    System.out.println("\n--- Editar Cliente ---");
    System.out.print("Ingrese el DNI del cliente a editar: ");
    String dni = sc.nextLine();

    Cliente cliente = control.buscarClientePorDNI(dni);

    if (cliente == null) {
        System.out.println("Cliente no encontrado con DNI " + dni + ".");
        return;
    }

    System.out.println("Editando datos de: " + cliente.getNombreCompleto());
    System.out.println("(Deje el campo vacío y presione ENTER si no desea cambiar el dato)");

    // Editar Correo
    System.out.print("Nuevo correo (Actual: " + cliente.getCorreo() + "): ");
    String correo = sc.nextLine();
    if (!correo.isEmpty()) {
        cliente.setCorreo(correo);
    }

    // Editar Teléfono
    System.out.print("Nuevo teléfono (Actual: " + cliente.getTelefono() + "): ");
    String telefono = sc.nextLine();
    if (!telefono.isEmpty()) {
        cliente.setTelefono(telefono);
    }

    // Editar Dirección
    System.out.print("Nueva dirección (Actual: " + cliente.getDireccion() + "): ");
    String direccion = sc.nextLine();
    if (!direccion.isEmpty()) {
        cliente.setDireccion(direccion);
    }

    // Editar Tipo de Cliente
    System.out.print("Nuevo tipo de cliente (Actual: " + cliente.getTipoCliente() + "): ");
    String tipoCliente = sc.nextLine();
    if (!tipoCliente.isEmpty()) {
        cliente.setTipoCliente(tipoCliente);
    }

    // Editar Descuento
    System.out.print("Nuevo descuento (ej: 0.1) (Actual: " + cliente.getDescuento() + "): ");
    String descuentoStr = sc.nextLine();
    if (!descuentoStr.isEmpty()) {
        try {
            double descuento = Double.parseDouble(descuentoStr);
            cliente.setDescuento(descuento);
        } catch (NumberFormatException e) {
            System.out.println("Descuento no válido, se mantuvo el anterior.");
        }
    }
        System.out.println("Cliente actualizado correctamente.");
        cliente.mostrarDatos();
    }
    
        public static void buscarCliente() {
        System.out.println("\n--- 5. Buscar Cliente ---");
        System.out.print("Ingrese el DNI (exacto) o el Nombre (parcial) del cliente: ");
        String criterio = sc.nextLine();

        if (criterio.isBlank()) {
            System.out.println("Criterio de búsqueda vacío. Operación cancelada.");
            return;
        }

        // 1. Llamamos al nuevo método del controlador
        ArrayList<Cliente> resultados = control.buscarClientesPorCriterio(criterio);

        // 2. Manejamos los escenarios de la HU02
        if (resultados.isEmpty()) {
            // HU02 - Escenario 2: Sin resultados
            System.out.println("Cliente no encontrado con el criterio: '" + criterio + "'");
        } else {
            // HU02 - Escenario 1: Búsqueda exacta (o múltiple por nombre)
            System.out.println("Se encontraron (" + resultados.size() + ") clientes:");
            for (Cliente c : resultados) {
                c.mostrarDatos();
            }
        }
    }

    // ===== MENÚ EMPLEADOS =====
    public static void menuEmpleados() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Editar empleado");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarTrabajador();
                case 2 -> control.listarTrabajadores();
                case 3 -> eliminarTrabajador();
                case 4 -> editarTrabajador(); 
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

   // ===== METODOS TRABAJADOR =====
    public static void registrarTrabajador() {
        System.out.println("\n=== Registro de Trabajador ===");

        // --- VALIDACIÓN DE CAMPOS VACÍOS ---
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        while (nombre.isBlank()) {
            System.out.println("Error: El nombre no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        }

        System.out.print("Apellido Paterno: ");
        String apPat = sc.nextLine();
        while (apPat.isBlank()) {
            System.out.println("Error: El apellido paterno no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Apellido Paterno: ");
            apPat = sc.nextLine();
        }

        System.out.print("Apellido Materno: ");
        String apMat = sc.nextLine();
        while (apMat.isBlank()) {
            System.out.println("Error: El apellido materno no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Apellido Materno: ");
            apMat = sc.nextLine();
        }

        // --- VALIDACIÓN DE DOCUMENTO ---
        System.out.print("Ingrese tipo de documento (DNI o CE): ");
        String tipoDoc = sc.nextLine().trim();
        while (tipoDoc.isBlank() || (!tipoDoc.equalsIgnoreCase("DNI") && !tipoDoc.equalsIgnoreCase("CE"))) {
            if (tipoDoc.isBlank()) {
                System.out.println("Error: El tipo de documento no puede estar vacío.");
            } else {
                System.out.println("Error: Tipo de documento no válido. Ingrese nuevamente (DNI o CE): ");
            }
            System.out.print("Ingrese tipo de documento (DNI o CE): ");
            tipoDoc = sc.nextLine().trim();
        }

        System.out.print("Ingrese número de documento: ");
        String numDoc = sc.nextLine().trim();

        // Bucle mejorado para validar número de documento
        while (true) {
            if (numDoc.isBlank()) {
                System.out.println("Error: El número de documento no puede estar vacío.");
            } else if (tipoDoc.equalsIgnoreCase("DNI") && numDoc.length() != 8) {
                System.out.println("Error: El DNI debe tener 8 dígitos.");
            } else if (tipoDoc.equalsIgnoreCase("CE") && (numDoc.length() < 9 || numDoc.length() > 12)) {
                System.out.println("Error: El carné de extranjería debe tener entre 9 y 12 caracteres.");
            } else {
                // Si no falló ninguna validación, el número es correcto
                break; 
            }

            // Si falló algo, vuelve a pedir
            System.out.print("Ingrese número de documento: ");
            numDoc = sc.nextLine().trim();
        }

        // --- VALIDACIÓN DE CAMPOS VACÍOS (CONTINUACIÓN) ---
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        while (correo.isBlank()) {
            System.out.println("Error: El correo no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Correo: ");
            correo = sc.nextLine();
        }

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        while (telefono.isBlank()) {
            System.out.println("Error: El teléfono no puede estar vacío. Ingrese nuevamente: ");
            System.out.print("Teléfono: ");
            telefono = sc.nextLine();
        }

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        while (direccion.isBlank()) {
            System.out.println("Error: La dirección no puede estar vacía. Ingrese nuevamente: ");
            System.out.print("Dirección: ");
            direccion = sc.nextLine();
        }

        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaIngreso = sc.nextLine();
        while (fechaIngreso.isBlank()) {
            System.out.println("Error: La fecha de ingreso no puede estar vacía. Ingrese nuevamente: ");
            System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
            fechaIngreso = sc.nextLine();
        }

        // --- SELECCIÓN DE ROL ---
        System.out.println("Seleccione el tipo de trabajador:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado de Ventas");
        System.out.println("3. Gerente");
        System.out.print("Opción: ");
        int tipo = leerEntero();

        int id = control.cantidadTrabajadores() + 1;
        Trabajador t = null;

        // --- CREACIÓN DEL OBJETO CON TRY-CATCH ---
        // (Ahora la Vista pre-valida campos vacíos, y el try-catch
        // atrapará errores más avanzados, como el formato del correo)
        try {
            switch (tipo) {
                case 1 -> t = new Administrador(fechaIngreso, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
                case 2 -> t = new Empleado(fechaIngreso, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
                case 3 -> t = new Gerente(fechaIngreso, id, nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
                default -> {
                    System.out.println("Tipo no válido. Registro cancelado.");
                    return;
                }
            }

            control.registrarEmpleado(t);
            System.out.println("¡Empleado registrado exitosamente!"); // Mensaje de éxito

        } catch (IllegalArgumentException e) {
            System.out.println("--- ERROR EN EL REGISTRO ---");
            System.out.println("MENSAJE: " + e.getMessage());
            System.out.println("Registro cancelado.");
        }
    }

   public static void eliminarTrabajador() {
        System.out.println("\n--- Eliminar Trabajador ---");
        System.out.print("Ingrese el DNI del trabajador a eliminar: ");
        String dni = sc.nextLine();
        control.eliminarTrabajador(dni); // Llama al método del controlador
    }
   
   public static void editarTrabajador() {
        System.out.println("\n--- Editar Trabajador ---");
        System.out.print("Ingrese el DNI del trabajador a editar: ");
        String dni = sc.nextLine();

        Trabajador t = control.buscarTrabajadorPorDNI(dni);

        if (t == null) {
            System.out.println("Trabajador no encontrado con DNI " + dni + ".");
            return;
        }

        System.out.println("Editando datos de: " + t.getNombreCompleto());
        System.out.println("(Deje el campo vacío y presione ENTER si no desea cambiar el dato)");

        try {
            // Editar Correo
            System.out.print("Nuevo correo (Actual: " + t.getCorreo() + "): ");
            String correo = sc.nextLine();
            if (!correo.isEmpty()) {
                t.setCorreo(correo); // Usa el setter que valida
            }

            // Editar Teléfono
            System.out.print("Nuevo teléfono (Actual: " + t.getTelefono() + "): ");
            String telefono = sc.nextLine();
            if (!telefono.isEmpty()) {
                t.setTelefono(telefono); // Usa el setter que valida
            }

            // Editar Dirección
            System.out.print("Nueva dirección (Actual: " + t.getDireccion() + "): ");
            String direccion = sc.nextLine();
            if (!direccion.isEmpty()) {
                t.setDireccion(direccion); // Usa el setter que valida
            }

            // Editar Salario
            System.out.print("Nuevo salario (Actual: " + t.getSalario() + "): ");
            String salarioStr = sc.nextLine();
            if (!salarioStr.isEmpty()) {
                double salario = Double.parseDouble(salarioStr);
                if (salario > 0) {
                    t.setSalario(salario);
                } else {
                    System.out.println("Salario no válido, se mantuvo el anterior.");
                }
            }

            System.out.println("Trabajador actualizado correctamente.");
            t.mostrarDatos();

        } catch (IllegalArgumentException e) { // Captura errores de setCorreo, etc.
            System.out.println("Error al actualizar: " + e.getMessage());
        }
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
            System.out.println("1. Registrar pedido");
            System.out.println("2. Listar todos los pedidos");
            System.out.println("3. Listar pedidos por cliente"); // <-- NUEVA OPCIÓN
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarPedido();
                case 2 -> control.listarPedidos();
                case 3 -> listarPedidosPorCliente(); // <-- NUEVA LLAMADA
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }
    public static void listarPedidosPorCliente() {
        System.out.println("\n--- Listar Pedidos por Cliente ---");
        System.out.print("Ingrese el DNI del cliente para ver sus pedidos: ");
        String dni = sc.nextLine();
        control.listarPedidosPorCliente(dni);
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

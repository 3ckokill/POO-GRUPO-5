package poo.proj;

import java.util.Scanner;

public class Vista {

    private Scanner sc = new Scanner(System.in);
    private EmpleadoVentas empleadoActual = null;

    public void menuPrincipal(Controlador c,Administrador admin) {
        
        int opcion;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Registrar trabajador");
            System.out.println("2. Registrar producto");
            System.out.println("3. Registrar venta");
            System.out.println("4. Ver productos");
            System.out.println("5. Ver trabajadores");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    registrarTrabajador(c,admin);
                    break;

                case 2:
                    registrarProducto(c,admin);
                    break;

                case 3:
                    registrarVenta(c);
                    break;

                case 4:
                    c.verProductos();
                    break;

                case 5:
                    c.verTrabajadores();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }
    
    public void menuEmpleado(Controlador c,EmpleadoVentas emp) {

    int opcion;

    do {
        System.out.println("\n=== MENU EMPLEADO ===");
        System.out.println("1. Registrar venta");
        System.out.println("2. Ver productos");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {

            case 1:
                registrarVenta(c,emp); 
                break;

            case 2:
                c.verProductos();
                break;

            case 0:
                System.out.println("Saliendo...");
                break;

            default:
                System.out.println("Opción inválida");
        }

    } while (opcion != 0);

}

    // ---------------------------------------
    private void registrarTrabajador(Controlador c, Administrador admin) {

    System.out.print("Nombre: ");
    String nombre = sc.nextLine();

    System.out.print("Apellido paterno: ");
    String apP = sc.nextLine();

    System.out.print("Apellido materno: ");
    String apM = sc.nextLine();

    System.out.print("Tipo de documento: ");
    String tipoDoc = sc.nextLine();

    System.out.print("Número de documento: ");
    String numeroDoc = sc.nextLine();

    System.out.print("Sueldo base: ");
    double sueldo = sc.nextDouble();
    sc.nextLine();

    admin.registrarTrabajador(c, nombre, apP, apM, tipoDoc, numeroDoc, sueldo);
}


    // ---------------------------------------
    private void registrarProducto(Controlador c, Administrador admin) {

    System.out.print("Nombre del producto: ");
    String nombre = sc.nextLine();

    System.out.print("Precio: ");
    double precio = sc.nextDouble();
    sc.nextLine();

    System.out.print("Stock: ");
    int stock = sc.nextInt();
    sc.nextLine();

    admin.registrarProducto(c, nombre, precio, stock);
}


    private void registrarVenta(Controlador c, EmpleadoVentas emp) {

    System.out.print("Nombre del cliente: ");
    String nombre = sc.nextLine();

    System.out.print("DNI del cliente: ");
    String dni = sc.nextLine();

    System.out.print("ID del producto: ");
    int idProducto = sc.nextInt();
    sc.nextLine();

    System.out.print("Cantidad: ");
    int cantidad = sc.nextInt();
    sc.nextLine();

    emp.registrarVenta(c, nombre, dni, idProducto, cantidad);
}

    
    public void menuAdmin(Controlador c, Administrador admin) {

        int opcion;

        do {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Registrar trabajador");
            System.out.println("2. Registrar producto");
            System.out.println("3. Registrar venta");
            System.out.println("4. Ver productos");
            System.out.println("5. Ver trabajadores");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    registrarTrabajador(c,admin);
                    break;

                case 2:
                    registrarProducto(c,admin);
                    break;

                case 3:
                    registrarVenta(c);
                    break;

                case 4:
                    c.verProductos();
                    break;

                case 5:
                    c.verTrabajadores();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }
}

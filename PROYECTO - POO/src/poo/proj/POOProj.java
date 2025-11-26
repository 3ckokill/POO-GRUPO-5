package poo.proj;

import javax.swing.SwingUtilities;


public class POOProj {
    public static void main(String[] args) {
        // Crear controlador y algunos datos iniciales
        Controlador controlador = new Controlador();

        Administrador admin = new Administrador(
            "A", "B", "C", "DNI", "12345678", 3000.0, 0, 0.0
        );

        EmpleadoVentas emp = new EmpleadoVentas(
            "rafael", "alonzo", "rabanal", "DNI", "87654321", 1500.0, 0, 0.0
        );

        controlador.registrarAdministrador(admin);
        controlador.registrarEmpleado(emp);

        Producto prod = new Producto("Lapicero", 1.5, 100);
        controlador.registrarProducto(prod);

        SwingUtilities.invokeLater(() -> {
            LoginWindow login = new LoginWindow(controlador);
            login.setVisible(true);
        });
    }
}

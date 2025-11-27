package poo.proj;

import javax.swing.SwingUtilities;


public class POOProj {
    public static void main(String[] args) {
        
        Controlador controlador = new Controlador();

        // 1. Crear ADMIN con password "admin123"
        Administrador admin = new Administrador("Jefe", "Perez", "Gomez", "DNI", "11111111", 3000, 0, 0, "admin123");
        
        // 2. Crear VENDEDOR con password "ventas123"
        EmpleadoVentas vendedor = new EmpleadoVentas("Ana", "Ventas", "Diaz", "DNI", "22222222", 1200, 0, 0, "123");

        // Registrar
        controlador.registrarAdministrador(admin);
        controlador.registrarEmpleado(vendedor);
        
        // Producto de prueba
        controlador.registrarProducto(new Producto("Mouse Gamer", 50.0, 20));
        controlador.registrarProducto(new Producto("Pepsi", 1.5, 20));

        // 3. INICIAR CON LOGIN
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaLogin(controlador).setVisible(true);
        });

    }
}

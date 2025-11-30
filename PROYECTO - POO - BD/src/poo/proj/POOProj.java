package poo.proj;

import javax.swing.SwingUtilities;

public class POOProj {
    public static void main(String[] args) {
        
        // 1. Creamos la instancia del Controlador.
        // Ahora este controlador se conecta a la BD, no necesita listas iniciales.
        Controlador controlador = new Controlador();

        // 2. Iniciamos la aplicación gráfica.
        SwingUtilities.invokeLater(() -> {
            LoginWindow login = new LoginWindow(controlador);
            login.setVisible(true);
        });
    }
}
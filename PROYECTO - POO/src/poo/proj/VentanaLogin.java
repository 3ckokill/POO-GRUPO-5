package poo.proj;

import java.awt.GridLayout;
import javax.swing.*;

public class VentanaLogin extends JFrame {

    private Controlador controlador;

    public VentanaLogin(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Login - Sistema");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtUsuario = new JTextField(); // Usaremos DNI como usuario
        JPasswordField txtPass = new JPasswordField();
        JButton btnIngresar = new JButton("Ingresar");

        panel.add(new JLabel("DNI Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtPass);
        panel.add(new JLabel("")); // Espacio vacío
        panel.add(btnIngresar);

        add(panel);

        // --- ACCIÓN DEL BOTÓN ---
        btnIngresar.addActionListener(e -> {
            String dni = txtUsuario.getText();
            String pass = new String(txtPass.getPassword());

            Trabajador usuario = controlador.login(dni, pass);

            if (usuario != null) {
                // Login Exitoso: Abrir la ventana principal
                abrirVentanaPrincipal(usuario);
                this.dispose(); // Cerrar ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void abrirVentanaPrincipal(Trabajador usuarioLogueado) {
        // Pasamos el usuario logueado a la ventana principal
        VentanaPrincipal v = new VentanaPrincipal(controlador, usuarioLogueado);
        v.setVisible(true);
    }
}
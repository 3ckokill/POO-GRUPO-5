/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;

/**
 *
 * @author Rafael
 */
import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private Controlador controlador;

    public LoginWindow(Controlador controlador) {
        this.controlador = controlador;

        // -----------------------------
        // ICONO DE LA VENTANA (esquina superior izquierda)
        // -----------------------------
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        Image iconoEscalado = iconoVentana.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        setIconImage(iconoEscalado);
        setTitle("Sistema Imprenta Halo");

        // -----------------------------
        // TAMAÑO: 50% DE LA PANTALLA
        // -----------------------------
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.50);
        int height = (int) (screen.height * 0.50);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -----------------------------
        // PANEL SUPERIOR: botón Cerrar Sesión
        // -----------------------------
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(new Color(200, 210, 220));
        JButton btnCerrarSesion = crearBoton("Cerrar Sesión", new Color(255, 100, 100));
        panelTop.add(btnCerrarSesion, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);

        // -----------------------------
        // PANEL CENTRAL: contenido + logo
        // -----------------------------
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(200, 210, 220)); // fondo gris suave

        // Panel contenido (izquierda)
        JPanel panelContenido = new JPanel(new GridLayout(5, 2, 15, 15));
        panelContenido.setPreferredSize(new Dimension(350, 220));
        panelContenido.setBackground(new Color(200, 210, 220));

        // Campos de texto
        JTextField txtNombre = new JTextField();
        JTextField txtApP = new JTextField();
        JTextField txtApM = new JTextField();
        JTextField txtDoc = new JTextField();
        Color labelColor = new Color(30, 30, 60);

        panelContenido.add(crearLabel("Nombre:", labelColor));
        panelContenido.add(txtNombre);
        panelContenido.add(crearLabel("Apellido Paterno:", labelColor));
        panelContenido.add(txtApP);
        panelContenido.add(crearLabel("Apellido Materno:", labelColor));
        panelContenido.add(txtApM);
        panelContenido.add(crearLabel("Documento:", labelColor));
        panelContenido.add(txtDoc);

        // Botones de login
        JButton btnLoginAdmin = crearBoton("Ingresar como Administrador", new Color(100, 180, 255));
        JButton btnLoginEmpleado = crearBoton("Ingresar como Empleado Ventas", new Color(100, 255, 180));
        panelContenido.add(btnLoginAdmin);
        panelContenido.add(btnLoginEmpleado);

        // Panel logo (derecha)
        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(new Color(200, 210, 220));
        ImageIcon icono = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        icono = new ImageIcon(icono.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH));
        JLabel lblLogo = new JLabel(icono);
        panelLogo.add(lblLogo);

        // -----------------------------
        // UBICACIÓN CON GRIDBAG
        // -----------------------------
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelCentral.add(panelContenido, gbc);

        gbc.gridx = 1; // derecha
        panelCentral.add(panelLogo, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // -----------------------------
        // LOGICA BOTONES
        // -----------------------------
        btnLoginAdmin.addActionListener(e -> {
            Administrador admin = controlador.validarAdministrador(
                    txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText()
            );

            if (admin != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                new VentanaPrincipal(controlador, admin, null).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos para Administrador");
            }
        });

        btnLoginEmpleado.addActionListener(e -> {
            EmpleadoVentas emp = controlador.validarEmpleado(
                    txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText()
            );

            if (emp != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Empleado de Ventas");
                new VentanaPrincipal(controlador, null, emp).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos para Empleado");
            }
        });

        btnCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea cerrar la aplicación?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    // ==========================
    // MÉTODO AUXILIAR: LABEL
    // ==========================
    private JLabel crearLabel(String texto, Color color) {
        JLabel label = new JLabel(texto);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // ==========================
    // MÉTODO AUXILIAR: BOTÓN ESTÉTICO
    // ==========================
    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorBase);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase);
            }
        });

        return boton;
    }
}
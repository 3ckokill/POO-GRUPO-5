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

        setTitle("Login del Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -----------------------------
        // TAMAÑO: 50% DE LA PANTALLA
        // -----------------------------
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.50);
        int height = (int) (screen.height * 0.50);
        setSize(width, height);
        setLocationRelativeTo(null);

        // -----------------------------
        // PANEL PRINCIPAL CENTRADO
        // -----------------------------
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(200, 210, 220)); // fondo gris suave

        JPanel panelContenido = new JPanel(new GridLayout(5, 2, 15, 15));
        panelContenido.setPreferredSize(new Dimension(350, 220));
        panelContenido.setBackground(new Color(200, 210, 220)); // panel contenido claro

        // -----------------------------
        // CAMPOS DE TEXTO
        // -----------------------------
        JTextField txtNombre = new JTextField();
        JTextField txtApP = new JTextField();
        JTextField txtApM = new JTextField();
        JTextField txtDoc = new JTextField();

        // Colores de los labels
        Color labelColor = new Color(30, 30, 60); // azul oscuro

        panelContenido.add(crearLabel("Nombre:", labelColor));
        panelContenido.add(txtNombre);
        panelContenido.add(crearLabel("Apellido Paterno:", labelColor));
        panelContenido.add(txtApP);
        panelContenido.add(crearLabel("Apellido Materno:", labelColor));
        panelContenido.add(txtApM);
        panelContenido.add(crearLabel("Documento:", labelColor));
        panelContenido.add(txtDoc);

        // -----------------------------
        // BOTONES ESTÉTICOS
        // -----------------------------
        JButton btnLoginAdmin = crearBoton("Ingresar como Administrador", new Color(100, 180, 255));
        JButton btnLoginEmpleado = crearBoton("Ingresar como Empleado Ventas", new Color(100, 255, 180));

        panelContenido.add(btnLoginAdmin);
        panelContenido.add(btnLoginEmpleado);

        panelCentral.add(panelContenido);
        add(panelCentral);

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
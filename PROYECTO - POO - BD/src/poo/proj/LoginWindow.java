package poo.proj;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginWindow extends JFrame {

    private Controlador controlador;

    public LoginWindow(Controlador controlador) {
        this.controlador = controlador;

        // -----------------------------
        // CONFIGURACIÓN BÁSICA
        // -----------------------------
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        Image iconoEscalado = iconoVentana.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        setIconImage(iconoEscalado);
        setTitle("Sistema Imprenta Halo - Login");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.75);
        int height = (int) (screen.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -----------------------------
        // PANEL SUPERIOR (Botones de utilidad)
        // -----------------------------
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alineado a la derecha
        panelTop.setBackground(new Color(200, 210, 220));
        
        // --- NUEVO BOTÓN: VERIFICAR CONEXIÓN ---
        JButton btnTestDB = crearBoton("Verificar BD", new Color(100, 100, 100)); // Gris oscuro
        JButton btnCerrarSesion = crearBoton("Cerrar Aplicación", new Color(255, 100, 100)); // Rojo
        
        panelTop.add(btnTestDB);
        panelTop.add(btnCerrarSesion);
        
        add(panelTop, BorderLayout.NORTH);

        // -----------------------------
        // PANEL CENTRAL (Formulario + Logo)
        // -----------------------------
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(200, 210, 220));

        // Panel Formulario
        JPanel panelContenido = new JPanel(new GridLayout(5, 2, 15, 15));
        panelContenido.setPreferredSize(new Dimension(350, 220));
        panelContenido.setBackground(new Color(200, 210, 220));

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

        JButton btnLoginAdmin = crearBoton("Ingresar como Admin", new Color(100, 180, 255));
        JButton btnLoginEmpleado = crearBoton("Ingresar como Vendedor", new Color(100, 255, 180));
        panelContenido.add(btnLoginAdmin);
        panelContenido.add(btnLoginEmpleado);

        // Panel Logo
        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(new Color(200, 210, 220));
        ImageIcon icono = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        JLabel lblLogo = new JLabel(new ImageIcon(icono.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
        panelLogo.add(lblLogo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(10, 10, 10, 10);
        panelCentral.add(panelContenido, gbc);
        gbc.gridx = 1;
        panelCentral.add(panelLogo, gbc);
        add(panelCentral, BorderLayout.CENTER);

        // -----------------------------
        // LÓGICA DE BOTONES
        // -----------------------------

        // --- ACCIÓN: PROBAR CONEXIÓN ---
        btnTestDB.addActionListener(e -> {
            try (Connection c = Conexion.getConexion()) {
                if (c != null) {
                    JOptionPane.showMessageDialog(null, "¡Conexión a Base de Datos EXITOSA! ✅");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: La conexión devolvió NULL ❌", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Excepción al conectar: " + ex.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Login Admin
        btnLoginAdmin.addActionListener(e -> {
            Administrador admin = controlador.validarAdministrador(txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText());
            if (admin != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador: " + admin.getNombre());
                new VentanaPrincipal(controlador, admin, null).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas o no es Administrador.");
            }
        });

        // Login Empleado
        btnLoginEmpleado.addActionListener(e -> {
            EmpleadoVentas emp = controlador.validarEmpleado(txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText());
            if (emp != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Vendedor: " + emp.getNombre());
                new VentanaPrincipal(controlador, null, emp).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas o no es Vendedor.");
            }
        });

        btnCerrarSesion.addActionListener(e -> System.exit(0));
    }

    private JLabel crearLabel(String texto, Color color) {
        JLabel label = new JLabel(texto);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorBase);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        return boton;
    }
}
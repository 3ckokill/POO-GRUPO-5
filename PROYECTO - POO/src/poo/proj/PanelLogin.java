/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel {
    private MainFrame mainFrame;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JComboBox<String> cboTipoUsuario;
    
    public PanelLogin(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 245));
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Panel central con el formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.insets = new Insets(8, 8, 8, 8);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Sistema de Imprenta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(51, 51, 51));
        gbcForm.gridx = 0;
        gbcForm.gridy = 0;
        gbcForm.gridwidth = 2;
        gbcForm.insets = new Insets(0, 0, 30, 0);
        panelFormulario.add(lblTitulo, gbcForm);
        
        // Tipo de usuario
        gbcForm.gridwidth = 1;
        gbcForm.insets = new Insets(8, 8, 8, 8);
        gbcForm.gridy = 1;
        gbcForm.gridx = 0;
        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblTipo, gbcForm);
        
        gbcForm.gridx = 1;
        cboTipoUsuario = new JComboBox<>(new String[]{"Administrador", "Empleado"});
        cboTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        cboTipoUsuario.setPreferredSize(new Dimension(250, 35));
        panelFormulario.add(cboTipoUsuario, gbcForm);
        
        // Usuario
        gbcForm.gridy = 2;
        gbcForm.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblUsuario, gbcForm);
        
        gbcForm.gridx = 1;
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setPreferredSize(new Dimension(250, 35));
        panelFormulario.add(txtUsuario, gbcForm);
        
        // Contraseña
        gbcForm.gridy = 3;
        gbcForm.gridx = 0;
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblPassword, gbcForm);
        
        gbcForm.gridx = 1;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(250, 35));
        panelFormulario.add(txtPassword, gbcForm);
        
        // Botón de login
        gbcForm.gridy = 4;
        gbcForm.gridx = 0;
        gbcForm.gridwidth = 2;
        gbcForm.insets = new Insets(20, 8, 8, 8);
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(0, 120, 215));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(250, 40));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e -> iniciarSesion());
        panelFormulario.add(btnLogin, gbcForm);
        
        // Permitir login con Enter
        txtPassword.addActionListener(e -> iniciarSesion());
        
        // Nota informativa
        gbcForm.gridy = 5;
        gbcForm.insets = new Insets(20, 8, 0, 8);
        JLabel lblNota = new JLabel("<html><center>Demo: usuario 'admin' / password 'admin'<br>o 'empleado' / 'empleado'</center></html>");
        lblNota.setFont(new Font("Arial", Font.ITALIC, 11));
        lblNota.setForeground(Color.GRAY);
        panelFormulario.add(lblNota, gbcForm);
        
        // Agregar panel de formulario al centro
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelFormulario, gbc);
    }
    
    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());
        String tipoUsuario = (String) cboTipoUsuario.getSelectedItem();
        
        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor complete todos los campos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validación simple (en producción esto debería validar contra BD)
        if (tipoUsuario.equals("Administrador") && usuario.equals("admin") && password.equals("admin")) {
            // Crear un administrador de ejemplo
            Administrador admin = new Administrador("Juan", "Pérez", "García", 
                "DNI", "12345678", 3000, 0, 0);
            mainFrame.getControlador().registrarAdministrador(admin);
            mainFrame.mostrarPanelAdmin(admin);
            limpiarCampos();
        } else if (tipoUsuario.equals("Empleado") && usuario.equals("empleado") && password.equals("empleado")) {
            // Crear un empleado de ejemplo
            EmpleadoVentas empleado = new EmpleadoVentas("María", "López", "Sánchez",
                "DNI", "87654321", 1500, 0, 20);
            mainFrame.getControlador().registrarEmpleado(empleado);
            mainFrame.mostrarPanelEmpleado(empleado);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos",
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;
import javax.swing.*;
import java.awt.*;
import poo.proj.*;
/**
 *
 * @author luisd
 */
public class PanelRegistroTrabajador extends JPanel {
    private Controlador controlador;
    private JTextField txtNombre, txtApellidoP, txtApellidoM, txtNumDoc, txtSueldo;
    private JComboBox<String> cboTipoDoc, cboTipoTrabajador;
    private JButton btnGuardar, btnLimpiar;
    
    public PanelRegistroTrabajador(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel lblTitulo = new JLabel("Registrar Trabajador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Tipo de trabajador
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Tipo de Trabajador:"), gbc);
        gbc.gridx = 1;
        cboTipoTrabajador = new JComboBox<>(new String[]{"Empleado de Ventas", "Administrador"});
        cboTipoTrabajador.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(cboTipoTrabajador, gbc);
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(25);
        txtNombre.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(txtNombre, gbc);
        
        // Apellido Paterno
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Apellido Paterno:"), gbc);
        gbc.gridx = 1;
        txtApellidoP = new JTextField(25);
        txtApellidoP.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(txtApellidoP, gbc);
        
        // Apellido Materno
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Apellido Materno:"), gbc);
        gbc.gridx = 1;
        txtApellidoM = new JTextField(25);
        txtApellidoM.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(txtApellidoM, gbc);
        
        // Tipo Documento
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Tipo de Documento:"), gbc);
        gbc.gridx = 1;
        cboTipoDoc = new JComboBox<>(new String[]{"DNI", "CE"});
        cboTipoDoc.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(cboTipoDoc, gbc);
        
        // Número Documento
        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Número de Documento:"), gbc);
        gbc.gridx = 1;
        txtNumDoc = new JTextField(25);
        txtNumDoc.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(txtNumDoc, gbc);
        
        // Sueldo Base
        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(new JLabel("Sueldo Base:"), gbc);
        gbc.gridx = 1;
        txtSueldo = new JTextField(25);
        txtSueldo.setPreferredSize(new Dimension(300, 30));
        panelFormulario.add(txtSueldo, gbc);
        
        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 8, 8, 8);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(120, 35));
        btnGuardar.setBackground(new Color(0, 120, 215));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> guardarTrabajador());
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelFormulario.add(panelBotones, gbc);
        
        add(panelFormulario, BorderLayout.CENTER);
    }
    
    private void guardarTrabajador() {
        try {
            String tipo = (String) cboTipoTrabajador.getSelectedItem();
            String nombre = txtNombre.getText().trim();
            String apellidoP = txtApellidoP.getText().trim();
            String apellidoM = txtApellidoM.getText().trim();
            String tipoDoc = (String) cboTipoDoc.getSelectedItem();
            String numDoc = txtNumDoc.getText().trim();
            double sueldo = Double.parseDouble(txtSueldo.getText().trim());
            
            if (nombre.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || numDoc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (tipo.equals("Empleado de Ventas")) {
                EmpleadoVentas emp = new EmpleadoVentas(nombre, apellidoP, apellidoM, tipoDoc, numDoc, sueldo, 0, 20);
                controlador.registrarEmpleado(emp);
            } else {
                Administrador admin = new Administrador(nombre, apellidoP, apellidoM, tipoDoc, numDoc, sueldo, 0, 0);
                controlador.registrarAdministrador(admin);
            }
            
            JOptionPane.showMessageDialog(this, "Trabajador registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El sueldo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidoP.setText("");
        txtApellidoM.setText("");
        txtNumDoc.setText("");
        txtSueldo.setText("");
        cboTipoTrabajador.setSelectedIndex(0);
        cboTipoDoc.setSelectedIndex(0);
    }
}

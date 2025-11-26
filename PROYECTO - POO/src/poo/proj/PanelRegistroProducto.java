/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author luisd
 */
public class PanelRegistroProducto extends JPanel {
    private Controlador controlador;
    private JTextField txtNombre, txtPrecio, txtStock;
    private JButton btnGuardar, btnLimpiar;
    
    public PanelRegistroProducto(Controlador controlador) {
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
        JLabel lblTitulo = new JLabel("Registrar Producto");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nombre del producto
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNombre = new JLabel("Nombre del Producto:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        txtNombre = new JTextField(25);
        txtNombre.setPreferredSize(new Dimension(300, 30));
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtNombre, gbc);
        
        // Precio
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblPrecio, gbc);
        
        gbc.gridx = 1;
        txtPrecio = new JTextField(25);
        txtPrecio.setPreferredSize(new Dimension(300, 30));
        txtPrecio.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtPrecio, gbc);
        
        // Stock
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(lblStock, gbc);
        
        gbc.gridx = 1;
        txtStock = new JTextField(25);
        txtStock.setPreferredSize(new Dimension(300, 30));
        txtStock.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtStock, gbc);
        
        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 10, 10, 10);
        
        btnGuardar = new JButton("Guardar Producto");
        btnGuardar.setPreferredSize(new Dimension(150, 35));
        btnGuardar.setBackground(new Color(0, 120, 215));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> guardarProducto());
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 13));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelFormulario.add(panelBotones, gbc);
        
        add(panelFormulario, BorderLayout.CENTER);
    }
    
    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "El nombre del producto no puede estar vacío",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Producto producto = new Producto(nombre, precio, stock);
            controlador.registrarProducto(producto);
            
            JOptionPane.showMessageDialog(this,
                "Producto registrado exitosamente\nID: " + producto.getId(),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Precio y stock deben ser números válidos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtNombre.requestFocus();
    }
}
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
public class PanelRegistroVenta extends JPanel {
    private Controlador controlador;
    private Trabajador trabajadorActual;
    private boolean esAdmin;
    
    private JTextField txtIdProducto, txtCantidad;
    private JButton btnGuardar, btnLimpiar, btnBuscarProducto;
    private JTextArea txtAreaDetalles;
    
    public PanelRegistroVenta(Controlador controlador, boolean esAdmin) {
        this.controlador = controlador;
        this.esAdmin = esAdmin;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel lblTitulo = new JLabel("Registrar Venta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel principal con formulario y detalles
        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 0));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Panel formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // ID Producto
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID del Producto:"), gbc);
        gbc.gridx = 1;
        JPanel panelProducto = new JPanel(new BorderLayout(5, 0));
        panelProducto.setBackground(Color.WHITE);
        txtIdProducto = new JTextField(15);
        txtIdProducto.setPreferredSize(new Dimension(180, 30));
        btnBuscarProducto = new JButton("🔍");
        btnBuscarProducto.setPreferredSize(new Dimension(50, 30));
        btnBuscarProducto.setFocusPainted(false);
        btnBuscarProducto.addActionListener(e -> buscarProducto());
        panelProducto.add(txtIdProducto, BorderLayout.CENTER);
        panelProducto.add(btnBuscarProducto, BorderLayout.EAST);
        panelFormulario.add(panelProducto, gbc);
        
        // Cantidad
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        txtCantidad = new JTextField(20);
        txtCantidad.setPreferredSize(new Dimension(250, 30));
        panelFormulario.add(txtCantidad, gbc);
        
        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBotones.setBackground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 8, 8, 8);
        
        btnGuardar = new JButton("Registrar Venta");
        btnGuardar.setPreferredSize(new Dimension(140, 35));
        btnGuardar.setBackground(new Color(0, 150, 136));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> registrarVenta());
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(100, 35));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelFormulario.add(panelBotones, gbc);
        
        // Panel de detalles
        JPanel panelDetalles = new JPanel(new BorderLayout());
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles del Producto"));
        panelDetalles.setPreferredSize(new Dimension(300, 0));
        
        txtAreaDetalles = new JTextArea(10, 25);
        txtAreaDetalles.setEditable(false);
        txtAreaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaDetalles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollDetalles = new JScrollPane(txtAreaDetalles);
        panelDetalles.add(scrollDetalles, BorderLayout.CENTER);
        
        panelPrincipal.add(panelFormulario, BorderLayout.WEST);
        panelPrincipal.add(panelDetalles, BorderLayout.CENTER);
        
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    private void buscarProducto() {
        try {
            int idProducto = Integer.parseInt(txtIdProducto.getText().trim());
            Producto producto = controlador.buscarProductoPorId(idProducto);
            
            if (producto != null) {
                txtAreaDetalles.setText(
                    "ID: " + producto.getId() + "\n" +
                    "Nombre: " + producto.getNombre() + "\n" +
                    "Precio: S/ " + String.format("%.2f", producto.getPrecio()) + "\n" +
                    "Stock: " + producto.getStock() + " unidades\n"
                );
            } else {
                txtAreaDetalles.setText("Producto no encontrado");
                JOptionPane.showMessageDialog(this,
                    "No se encontró el producto con ID: " + idProducto,
                    "Producto no encontrado",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Ingrese un ID válido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarVenta() {
        try {
            int idProducto = Integer.parseInt(txtIdProducto.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            
            if (trabajadorActual == null) {
                JOptionPane.showMessageDialog(this,
                    "No hay trabajador asignado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Producto producto = controlador.buscarProductoPorId(idProducto);
            if (producto == null) {
                JOptionPane.showMessageDialog(this,
                    "Producto no encontrado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (producto.getStock() < cantidad) {
                JOptionPane.showMessageDialog(this,
                    "Stock insuficiente. Disponible: " + producto.getStock(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Venta venta = new Venta(producto, cantidad, trabajadorActual);
            controlador.registrarVenta(venta);
            
            // Actualizar stock
            producto.setStock(producto.getStock() - cantidad);
            
            JOptionPane.showMessageDialog(this,
                "Venta registrada exitosamente\n" +
                "ID Venta: " + venta.getId() + "\n" +
                "Total: S/ " + String.format("%.2f", venta.getTotal()),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "ID de producto y cantidad deben ser números válidos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtIdProducto.setText("");
        txtCantidad.setText("");
        txtAreaDetalles.setText("");
    }
    
    public void setTrabajadorActual(Trabajador trabajador) {
        this.trabajadorActual = trabajador;
    }
}
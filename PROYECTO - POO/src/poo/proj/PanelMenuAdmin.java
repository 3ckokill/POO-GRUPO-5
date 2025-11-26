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


public class PanelMenuAdmin extends JPanel {
    private MainFrame mainFrame;
    private Controlador controlador;
    private Administrador administrador;
    
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JLabel lblBienvenida;
    
    // Paneles de contenido
    private PanelRegistroTrabajador panelRegistroTrabajador;
    private PanelRegistroProducto panelRegistroProducto;
    private PanelRegistroVenta panelRegistroVenta;
    private PanelVerProductos panelVerProductos;
    private PanelVerTrabajadores panelVerTrabajadores;
    private PanelVerVentas panelVerVentas;
    
    public PanelMenuAdmin(MainFrame mainFrame, Controlador controlador) {
        this.mainFrame = mainFrame;
        this.controlador = controlador;
        setLayout(new BorderLayout());
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel superior con bienvenida
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel lateral con menú
        JPanel panelMenu = crearPanelMenu();
        add(panelMenu, BorderLayout.WEST);
        
        // Panel central con contenido
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(Color.WHITE);
        
        // Crear paneles
        panelRegistroTrabajador = new PanelRegistroTrabajador(controlador);
        panelRegistroProducto = new PanelRegistroProducto(controlador);
        panelRegistroVenta = new PanelRegistroVenta(controlador, true);
        panelVerProductos = new PanelVerProductos(controlador);
        panelVerTrabajadores = new PanelVerTrabajadores(controlador);
        panelVerVentas = new PanelVerVentas(controlador);
        
        // Agregar paneles
        panelContenido.add(crearPanelInicio(), "INICIO");
        panelContenido.add(panelRegistroTrabajador, "TRABAJADOR");
        panelContenido.add(panelRegistroProducto, "PRODUCTO");
        panelContenido.add(panelRegistroVenta, "VENTA");
        panelContenido.add(panelVerProductos, "VER_PRODUCTOS");
        panelContenido.add(panelVerTrabajadores, "VER_TRABAJADORES");
        panelContenido.add(panelVerVentas, "VER_VENTAS");
        
        add(panelContenido, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 120, 215));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        lblBienvenida = new JLabel("Bienvenido, Administrador");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setForeground(Color.WHITE);
        panel.add(lblBienvenida, BorderLayout.WEST);
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBackground(Color.WHITE);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        panel.add(btnCerrarSesion, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panel.setPreferredSize(new Dimension(220, 0));
        
        // Botones del menú
        agregarBotonMenu(panel, "🏠 Inicio", "INICIO");
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        agregarBotonMenu(panel, "👤 Registrar Trabajador", "TRABAJADOR");
        agregarBotonMenu(panel, "📦 Registrar Producto", "PRODUCTO");
        agregarBotonMenu(panel, "💰 Registrar Venta", "VENTA");
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        agregarBotonMenu(panel, "📋 Ver Productos", "VER_PRODUCTOS");
        agregarBotonMenu(panel, "👥 Ver Trabajadores", "VER_TRABAJADORES");
        agregarBotonMenu(panel, "📊 Ver Ventas", "VER_VENTAS");
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private void agregarBotonMenu(JPanel panel, String texto, String nombrePanel) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            cardLayout.show(panelContenido, nombrePanel);
            if (nombrePanel.equals("VER_PRODUCTOS")) {
                panelVerProductos.actualizarTabla();
            } else if (nombrePanel.equals("VER_TRABAJADORES")) {
                panelVerTrabajadores.actualizarTabla();
            } else if (nombrePanel.equals("VER_VENTAS")) {
                panelVerVentas.actualizarTabla();
            }
        });
        panel.add(btn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel lblInicio = new JLabel("<html><center><h1>Sistema de Gestión de Imprenta</h1>" +
            "<p>Seleccione una opción del menú lateral</p></center></html>");
        lblInicio.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(lblInicio);
        
        return panel;
    }
    
    public void setAdministrador(Administrador admin) {
        this.administrador = admin;
        lblBienvenida.setText("Bienvenido, " + admin.getNombre() + " " + admin.getApellidoPaterno());
        panelRegistroVenta.setTrabajadorActual(admin);
    }
    
    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea cerrar sesión?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            mainFrame.mostrarPanel("LOGIN");
        }
    }
}
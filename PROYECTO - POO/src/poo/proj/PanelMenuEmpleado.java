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


public class PanelMenuEmpleado extends JPanel {
    private MainFrame mainFrame;
    private Controlador controlador;
    private EmpleadoVentas empleado;
    
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JLabel lblBienvenida;
    
    private PanelRegistroVenta panelRegistroVenta;
    private PanelVerProductos panelVerProductos;
    
    public PanelMenuEmpleado(MainFrame mainFrame, Controlador controlador) {
        this.mainFrame = mainFrame;
        this.controlador = controlador;
        setLayout(new BorderLayout());
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel superior
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel lateral
        JPanel panelMenu = crearPanelMenu();
        add(panelMenu, BorderLayout.WEST);
        
        // Panel central
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(Color.WHITE);
        
        panelRegistroVenta = new PanelRegistroVenta(controlador, false);
        panelVerProductos = new PanelVerProductos(controlador);
        
        panelContenido.add(crearPanelInicio(), "INICIO");
        panelContenido.add(panelRegistroVenta, "VENTA");
        panelContenido.add(panelVerProductos, "VER_PRODUCTOS");
        
        add(panelContenido, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 150, 136));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        lblBienvenida = new JLabel("Bienvenido, Empleado");
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
        
        agregarBotonMenu(panel, "🏠 Inicio", "INICIO");
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        agregarBotonMenu(panel, "💰 Registrar Venta", "VENTA");
        agregarBotonMenu(panel, "📋 Ver Productos", "VER_PRODUCTOS");
        
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
            }
        });
        panel.add(btn);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel lblInicio = new JLabel("<html><center><h1>Sistema de Ventas</h1>" +
            "<p>Seleccione una opción del menú lateral</p></center></html>");
        lblInicio.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(lblInicio);
        
        return panel;
    }
    
    public void setEmpleado(EmpleadoVentas emp) {
        this.empleado = emp;
        lblBienvenida.setText("Bienvenido, " + emp.getNombre() + " " + emp.getApellidoPaterno());
        panelRegistroVenta.setTrabajadorActual(emp);
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

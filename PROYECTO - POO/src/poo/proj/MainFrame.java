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
public class MainFrame extends JFrame {
    private Controlador controlador;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Paneles
    private PanelLogin panelLogin;
    private PanelMenuAdmin panelMenuAdmin;
    private PanelMenuEmpleado panelMenuEmpleado;
    
    public MainFrame() {
        controlador = new Controlador();
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Crear paneles
        panelLogin = new PanelLogin(this);
        panelMenuAdmin = new PanelMenuAdmin(this, controlador);
        panelMenuEmpleado = new PanelMenuEmpleado(this, controlador);
        
        // Agregar paneles al CardLayout
        mainPanel.add(panelLogin, "LOGIN");
        mainPanel.add(panelMenuAdmin, "ADMIN");
        mainPanel.add(panelMenuEmpleado, "EMPLEADO");
        
        add(mainPanel);
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Imprenta");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));
    }
    
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
    }
    
    public void mostrarPanelAdmin(Administrador admin) {
        panelMenuAdmin.setAdministrador(admin);
        mostrarPanel("ADMIN");
    }
    
    public void mostrarPanelEmpleado(EmpleadoVentas empleado) {
        panelMenuEmpleado.setEmpleado(empleado);
        mostrarPanel("EMPLEADO");
    }
    
    public Controlador getControlador() {
        return controlador;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}
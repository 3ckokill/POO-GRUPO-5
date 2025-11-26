/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVerVentas extends JPanel {
    private Controlador controlador;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JLabel lblTotalVentas;
    
    public PanelVerVentas(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JLabel lblTitulo = new JLabel("Lista de Ventas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo, BorderLayout.WEST);
        
        btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, 13));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(e -> actualizarTabla());
        panelTitulo.add(btnActualizar, BorderLayout.EAST);
        
        add(panelTitulo, BorderLayout.NORTH);
        
        // Tabla
        String[] columnas = {"ID", "Producto", "Cantidad", "Total", "Atendido por"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(230, 230, 230));
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con totales
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        lblTotalVentas = new JLabel("Total Ventas: S/ 0.00");
        lblTotalVentas.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalVentas.setForeground(new Color(0, 120, 0));
        panelInferior.add(lblTotalVentas);
        
        add(panelInferior, BorderLayout.SOUTH);
        
        actualizarTabla();
    }
    
    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        double totalGeneral = 0;
        
        for (Venta v : controlador.getVentas()) {
            String nombreTrabajador = v.getTrabajador() != null ? 
                v.getTrabajador().getNombre() + " " + v.getTrabajador().getApellidoPaterno() : 
                "No especificado";
            
            Object[] fila = {
                v.getId(),
                v.getProducto().getNombre(),
                v.getCantidad(),
                String.format("S/ %.2f", v.getTotal()),
                nombreTrabajador
            };
            modeloTabla.addRow(fila);
            totalGeneral += v.getTotal();
        }
        
        lblTotalVentas.setText(String.format("Total Ventas: S/ %.2f", totalGeneral));
    }
}
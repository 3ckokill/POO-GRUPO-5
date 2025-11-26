/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import poo.proj.*;

/**
 *
 * @author luisd
 */
public class PanelVerProductos extends JPanel {
    private Controlador controlador;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    
    public PanelVerProductos(Controlador controlador) {
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
        
        JLabel lblTitulo = new JLabel("Lista de Productos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo, BorderLayout.WEST);
        
        btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, 13));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(e -> actualizarTabla());
        panelTitulo.add(btnActualizar, BorderLayout.EAST);
        
        add(panelTitulo, BorderLayout.NORTH);
        
        // Tabla
        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
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
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
        
        actualizarTabla();
    }
    
    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Producto p : controlador.getProductos()) {
            Object[] fila = {
                p.getId(),
                p.getNombre(),
                String.format("S/ %.2f", p.getPrecio()),
                p.getStock()
            };
            modeloTabla.addRow(fila);
        }
    }
}

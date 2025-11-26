/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Rafael
 */
public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private Administrador admin;
    private EmpleadoVentas emp;

    public VentanaPrincipal(Controlador controlador, Administrador admin, EmpleadoVentas emp) {
    this.controlador = controlador;
    this.admin = admin;
    this.emp = emp;

    setTitle("Sistema de Ventas");
    setSize(650, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JTabbedPane pestañas = new JTabbedPane();

    if (admin != null) {
        pestañas.addTab("Administrador", panelAdministrador());
    }

    if (emp != null) {
        pestañas.addTab("Ventas", panelVentas());
    }

    add(pestañas);
}

    private JPanel panelAdministrador() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnTrabajador = new JButton("Registrar Trabajador");
        btnTrabajador.addActionListener(e -> registrarTrabajador());

        JButton btnProducto = new JButton("Registrar Producto");
        btnProducto.addActionListener(e -> registrarProducto());

        JButton btnStock = new JButton("Ver Productos");
        btnStock.addActionListener(e -> controlador.verProductos());

        panel.add(btnTrabajador);
        panel.add(btnProducto);
        panel.add(btnStock);

        return panel;
    }

    private JPanel panelVentas() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtCliente = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtIdProd = new JTextField();
        JTextField txtCantidad = new JTextField();

        JButton btnVender = new JButton("Registrar Venta");

        panel.add(new JLabel("ID Producto:"));
        panel.add(txtIdProd);

        panel.add(new JLabel("Cantidad:"));
        panel.add(txtCantidad);

        panel.add(btnVender);

        btnVender.addActionListener(e -> {
            try {
                String nombre = txtCliente.getText();
                String dni = txtDni.getText();
                int idProd = Integer.parseInt(txtIdProd.getText());
                int cant = Integer.parseInt(txtCantidad.getText());

                emp.registrarVenta(controlador, idProd, cant);

                JOptionPane.showMessageDialog(null, "Venta registrada correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    private void registrarTrabajador() {
        JTextField nombre = new JTextField();
        JTextField apP = new JTextField();
        JTextField apM = new JTextField();
        JTextField tipo = new JTextField();
        JTextField nro = new JTextField();
        JTextField sueldo = new JTextField();

        Object[] msg = {
                "Nombre:", nombre,
                "Apellido paterno:", apP,
                "Apellido materno:", apM,
                "Tipo documento:", tipo,
                "Número documento:", nro,
                "Sueldo base:", sueldo
        };

        int opcion = JOptionPane.showConfirmDialog(null, msg, "Registrar Trabajador", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                admin.registrarTrabajador(
                        controlador,
                        nombre.getText(),
                        apP.getText(),
                        apM.getText(),
                        tipo.getText(),
                        nro.getText(),
                        Double.parseDouble(sueldo.getText())
                );
                JOptionPane.showMessageDialog(null, "Registrado correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private void registrarProducto() {
        JTextField nombre = new JTextField();
        JTextField precio = new JTextField();
        JTextField stock = new JTextField();

        Object[] msg = {
                "Nombre:", nombre,
                "Precio:", precio,
                "Stock:", stock
        };

        int opcion = JOptionPane.showConfirmDialog(null, msg, "Registrar Producto", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                admin.registrarProducto(
                        controlador,
                        nombre.getText(),
                        Double.parseDouble(precio.getText()),
                        Integer.parseInt(stock.getText())
                );
                JOptionPane.showMessageDialog(null, "Producto registrado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
}
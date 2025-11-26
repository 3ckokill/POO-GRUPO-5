/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private Administrador admin;
    private EmpleadoVentas emp;

    private JLabel lblSubtotal;
    private JTextField txtSubtotal;

    public VentanaPrincipal(Controlador controlador, Administrador admin, EmpleadoVentas emp) {

        this.controlador = controlador;
        this.admin = admin;
        this.emp = emp;

        // ------------------------------------------
    // TAMAÑO DE LA VENTANA: 65% DE LA PANTALLA
    // ------------------------------------------
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.50);
        int height = (int) (screen.height * 0.50);
        setSize(width, height);
        setLocationRelativeTo(null);

        setTitle("Sistema de Ventas");
        setTitle("Sistema de Ventas");

        // -----------------------------
        // PESTAÑAS
        // -----------------------------
        JTabbedPane pestañas = new JTabbedPane();

        if (admin != null) {
            pestañas.addTab("Administrador", panelAdministrador());
        }
        if (emp != null) {
            pestañas.addTab("Ventas", panelVentas());
        }

        add(pestañas, BorderLayout.CENTER);

        setVisible(true);
    }

    // ============================================================
    // PANEL ADMINISTRADOR
    // ============================================================
    private JPanel panelAdministrador() {
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    panelPrincipal.setBackground(new Color(245, 245, 250));

    // Panel izquierdo: botones
    JPanel panelIzquierda = new JPanel();
    panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
    panelIzquierda.setPreferredSize(new Dimension(200, getHeight()));
    panelIzquierda.setBackground(new Color(220, 225, 230));

    JButton btnTrabajador = crearBotonAdmin("Registrar Trabajador", new Color(100, 180, 255));
    JButton btnProducto = crearBotonAdmin("Registrar Producto", new Color(100, 255, 180));
    JButton btnStock = crearBotonAdmin("Ver Productos", new Color(255, 180, 100));
    JButton btnCerrarSesion = crearBotonAdmin("Cerrar Sesión", new Color(255, 100, 100));

    panelIzquierda.add(Box.createVerticalStrut(30));
    panelIzquierda.add(btnTrabajador);
    panelIzquierda.add(Box.createVerticalStrut(15));
    panelIzquierda.add(btnProducto);
    panelIzquierda.add(Box.createVerticalStrut(15));
    panelIzquierda.add(btnStock);
    panelIzquierda.add(Box.createVerticalGlue());
    panelIzquierda.add(btnCerrarSesion);

    // Panel derecho: contenido dinámico
    JPanel panelDerecha = new JPanel(new BorderLayout());
    panelDerecha.setBackground(new Color(245, 245, 250));

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierda, panelDerecha);
    splitPane.setDividerLocation(200);
    splitPane.setResizeWeight(0);
    splitPane.setEnabled(false);

    panelPrincipal.add(splitPane, BorderLayout.CENTER);

    // Acciones botones
    btnTrabajador.addActionListener(e -> {
        panelDerecha.removeAll();
        panelDerecha.add(crearPanelRegistrarTrabajador(), BorderLayout.CENTER);
        panelDerecha.revalidate();
        panelDerecha.repaint();
    });

    btnProducto.addActionListener(e -> {
        panelDerecha.removeAll();
        panelDerecha.add(crearPanelRegistrarProducto(), BorderLayout.CENTER);
        panelDerecha.revalidate();
        panelDerecha.repaint();
    });

    btnStock.addActionListener(e -> {
        panelDerecha.removeAll();
        panelDerecha.add(crearPanelVerProductos(), BorderLayout.CENTER);
        panelDerecha.revalidate();
        panelDerecha.repaint();
    });

    btnCerrarSesion.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Desea cerrar sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginWindow(controlador).setVisible(true);
        }
    });

    return panelPrincipal;
}


// ==============================
// MÉTODO AUXILIAR: Botones estilizados
// ==============================
private JButton crearBotonAdmin(String texto, Color colorBase) {
    JButton boton = new JButton(texto);
    boton.setMaximumSize(new Dimension(180, 40));
    boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    boton.setBackground(colorBase);
    boton.setForeground(Color.WHITE);
    boton.setFocusPainted(false);
    boton.setFont(new Font("Arial", Font.BOLD, 13));
    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    boton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorBase.darker());
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorBase);
        }
    });

    return boton;
}

// ==============================
// MÉTODOS AUXILIARES: Paneles dinámicos
// ==============================
private JPanel crearPanelRegistrarTrabajador() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(200, 210, 220));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;

    JTextField txtNombre = new JTextField(15);
    JTextField txtApP = new JTextField(15);
    JTextField txtApM = new JTextField(15);
    JTextField txtTipoDoc = new JTextField(15);
    JTextField txtNroDoc = new JTextField(15);
    JTextField txtSueldo = new JTextField(15);

    panel.add(new JLabel("Nombre:"), gbc); gbc.gridx = 1;
    panel.add(txtNombre, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Apellido Paterno:"), gbc); gbc.gridx = 1;
    panel.add(txtApP, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Apellido Materno:"), gbc); gbc.gridx = 1;
    panel.add(txtApM, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Tipo Documento:"), gbc); gbc.gridx = 1;
    panel.add(txtTipoDoc, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Número Documento:"), gbc); gbc.gridx = 1;
    panel.add(txtNroDoc, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Sueldo Base:"), gbc); gbc.gridx = 1;
    panel.add(txtSueldo, gbc); gbc.gridx = 0; gbc.gridy++;

    JButton btnRegistrar = new JButton("Registrar Trabajador");
    btnRegistrar.setBackground(new Color(100, 180, 255));
    btnRegistrar.setForeground(Color.WHITE);
    btnRegistrar.setFocusPainted(false);
    btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));

    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(btnRegistrar, gbc);

    btnRegistrar.addActionListener(e -> {
        try {
            admin.registrarTrabajador(
                controlador,
                txtNombre.getText(),
                txtApP.getText(),
                txtApM.getText(),
                txtTipoDoc.getText(),
                txtNroDoc.getText(),
                Double.parseDouble(txtSueldo.getText())
            );
            JOptionPane.showMessageDialog(null, "Trabajador registrado correctamente");
            txtNombre.setText(""); txtApP.setText(""); txtApM.setText("");
            txtTipoDoc.setText(""); txtNroDoc.setText(""); txtSueldo.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    });

    return panel;
}

    private JPanel crearPanelRegistrarProducto() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(200, 210, 220));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;

    JTextField txtNombre = new JTextField(15);
    JTextField txtPrecio = new JTextField(15);
    JTextField txtStock = new JTextField(15);

    panel.add(new JLabel("Nombre Producto:"), gbc); gbc.gridx = 1;
    panel.add(txtNombre, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Precio:"), gbc); gbc.gridx = 1;
    panel.add(txtPrecio, gbc); gbc.gridx = 0; gbc.gridy++;

    panel.add(new JLabel("Stock:"), gbc); gbc.gridx = 1;
    panel.add(txtStock, gbc); gbc.gridx = 0; gbc.gridy++;

    JButton btnRegistrar = new JButton("Registrar Producto");
    btnRegistrar.setBackground(new Color(100, 255, 180));
    btnRegistrar.setForeground(Color.WHITE);
    btnRegistrar.setFocusPainted(false);
    btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));

    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(btnRegistrar, gbc);

    btnRegistrar.addActionListener(e -> {
        try {
            admin.registrarProducto(
                controlador,
                txtNombre.getText(),
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText())
            );
            JOptionPane.showMessageDialog(null, "Producto registrado correctamente");
            txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    });

    return panel;
}

    private JPanel crearPanelVerProductos() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(200, 210, 220));

    String[] columnas = {"Nombre", "Precio", "Stock"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    JTable tabla = new JTable(modelo);

    // Llenar tabla con productos actuales
    for (Producto p : controlador.getProductos()) {
        modelo.addRow(new Object[]{p.getNombre(), p.getPrecio(), p.getStock()});
    }

    panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
    return panel;
}


    // ============================================================
    // PANEL VENTAS (AQUÍ VA EL BOTÓN CERRAR SESIÓN)
    // ============================================================
private JPanel panelVentas() {
    JPanel panel = new JPanel(new BorderLayout(30, 30));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setBackground(new Color(245, 245, 250));

    // Panel izquierdo: formulario
    JPanel left = new JPanel(new GridBagLayout());
    left.setBackground(new Color(230, 235, 240));
    left.setPreferredSize(new Dimension(260, 400));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;

    // ComboBox con productos disponibles
    JComboBox<String> comboProductos = new JComboBox<>();
    for (Producto p : controlador.getProductos()) comboProductos.addItem(p.getNombre());
    comboProductos.setMaximumRowCount(5); // Muestra máximo 5 productos, scroll si hay más

    JTextField txtCantidad = new JTextField();

    JButton btnAgregar = new JButton("Agregar");
    JButton btnVender = new JButton("Vender");
    JButton btnCerrar = new JButton("Salir");

    Dimension btnSize = new Dimension(120, 32);
    btnAgregar.setPreferredSize(btnSize);
    btnVender.setPreferredSize(btnSize);
    btnCerrar.setPreferredSize(btnSize);

    btnAgregar.setBackground(new Color(100, 180, 255));
    btnAgregar.setForeground(Color.WHITE);
    btnVender.setBackground(new Color(100, 255, 180));
    btnVender.setForeground(Color.WHITE);
    btnCerrar.setBackground(new Color(255, 100, 100));
    btnCerrar.setForeground(Color.WHITE);

    left.add(new JLabel("Producto:"), gbc);
    gbc.gridy++;
    left.add(comboProductos, gbc);
    gbc.gridy++;

    left.add(new JLabel("Cantidad:"), gbc);
    gbc.gridy++;
    left.add(txtCantidad, gbc);
    gbc.gridy++;

    left.add(btnAgregar, gbc);
    gbc.gridy++;
    left.add(btnVender, gbc);
    gbc.gridy++;

    gbc.insets = new Insets(40, 10, 10, 10);
    left.add(btnCerrar, gbc);

    // Panel derecho: tabla y subtotal
    JPanel right = new JPanel(new BorderLayout(15, 15));
    right.setBackground(new Color(255, 255, 255));

    String[] columnas = {"Producto", "Cantidad", "Precio"};
    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    JTable tabla = new JTable(modeloTabla);
    right.add(new JScrollPane(tabla), BorderLayout.CENTER);

    JPanel subtotalPanel = new JPanel(new GridLayout(1, 2));
    lblSubtotal = new JLabel("Subtotal: S/.");
    txtSubtotal = new JTextField("0.00");
    txtSubtotal.setEditable(false);
    subtotalPanel.add(lblSubtotal);
    subtotalPanel.add(txtSubtotal);
    right.add(subtotalPanel, BorderLayout.SOUTH);

    panel.add(left, BorderLayout.WEST);
    panel.add(right, BorderLayout.CENTER);

    // Lógica de botones
    btnAgregar.addActionListener(e -> {
        try {
            String nombre = comboProductos.getSelectedItem().toString();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            Producto p = controlador.getProductos().stream()
                    .filter(pr -> pr.getNombre().equalsIgnoreCase(nombre))
                    .findFirst().orElse(null);

            if (p == null) { JOptionPane.showMessageDialog(null, "Producto no encontrado."); return; }
            if (cantidad <= 0) { JOptionPane.showMessageDialog(null, "Cantidad inválida."); return; }
            if (cantidad > p.getStock()) { JOptionPane.showMessageDialog(null, "Stock insuficiente."); return; }

            double precio = p.getPrecio() * cantidad;
            modeloTabla.addRow(new Object[]{p.getNombre(), cantidad, precio});
            actualizarSubtotal(modeloTabla);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    });

    btnVender.addActionListener(e -> {
        try {
            if (modeloTabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay productos en la venta.");
                return;
            }

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String nombreProd = modeloTabla.getValueAt(i, 0).toString();
                int cant = (int) modeloTabla.getValueAt(i, 1);

                for (Producto p : controlador.getProductos()) {
                    if (p.getNombre().equalsIgnoreCase(nombreProd)) {
                        p.setStock(p.getStock() - cant);
                    }
                }
            }

            modeloTabla.setRowCount(0);
            actualizarSubtotal(modeloTabla);

            JOptionPane.showMessageDialog(null, "Venta realizada con éxito.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    });

    btnCerrar.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Desea cerrar sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginWindow(controlador).setVisible(true);
        }
    });

    return panel;
}
    // ============================================================
    // SUBTOTAL
    // ============================================================
    private void actualizarSubtotal(DefaultTableModel modeloTabla) {

        double subtotal = 0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += Double.parseDouble(modeloTabla.getValueAt(i, 2).toString());
        }

        txtSubtotal.setText(String.format("%.2f", subtotal));
    }

    // ============================================================
    // REGISTROS
    // ============================================================
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

        int opcion = JOptionPane.showConfirmDialog(null, msg,
                "Registrar Trabajador", JOptionPane.OK_CANCEL_OPTION);

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

        int opcion = JOptionPane.showConfirmDialog(null, msg,
                "Registrar Producto", JOptionPane.OK_CANCEL_OPTION);

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

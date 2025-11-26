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
    ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
    // Opcional: escalar si quieres
    Image iconoEscalado = iconoVentana.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    setIconImage(iconoEscalado);
    setTitle("Sistema Admistración");
    // Panel izquierdo: botones
    JPanel panelIzquierda = new JPanel();
    panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
    panelIzquierda.setPreferredSize(new Dimension(200, getHeight()));
    panelIzquierda.setBackground(new Color(220, 225, 230));

    JButton btnTrabajador = crearBotonAdmin("Registrar Trabajador", new Color(100, 180, 255));
    JButton btnProducto = crearBotonAdmin("Registrar Producto", new Color(100, 255, 180));
    JButton btnStock = crearBotonAdmin("Ver Productos", new Color(255, 180, 100));
    JButton btnCerrarSesion = crearBotonAdmin("Salir", new Color(255, 100, 100));

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
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    JTable tabla = new JTable(modelo);

    actualizarTablaProductos(modelo);

    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    JButton btnEditar = new JButton("Editar");
    JButton btnEliminar = new JButton("Eliminar");

    btnEditar.setBackground(new Color(100, 180, 255));
    btnEditar.setForeground(Color.WHITE);
    btnEliminar.setBackground(new Color(255, 100, 100));
    btnEliminar.setForeground(Color.WHITE);

    panelBotones.add(btnEditar);
    panelBotones.add(btnEliminar);

    panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
    panel.add(panelBotones, BorderLayout.SOUTH);

    // Botón Editar
    btnEditar.addActionListener(e -> {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para editar.");
            return;
        }

        Producto p = controlador.getProductos().get(fila);

        JTextField txtNombre = new JTextField(p.getNombre());
        JTextField txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
        JTextField txtStock = new JTextField(String.valueOf(p.getStock()));

        Object[] msg = {
                "Nombre:", txtNombre,
                "Precio:", txtPrecio,
                "Stock:", txtStock
        };

        int opcion = JOptionPane.showConfirmDialog(null, msg, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                p.setNombre(txtNombre.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setStock(Integer.parseInt(txtStock.getText()));
                actualizarTablaProductos(modelo);
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    });

    // Botón Eliminar
    btnEliminar.addActionListener(e -> {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este producto?", "Eliminar Producto", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.getProductos().remove(fila);
            actualizarTablaProductos(modelo);
        }
    });

    return panel;
}


// Método auxiliar para refrescar la tabla
private void actualizarTablaProductos(DefaultTableModel modelo) {
    modelo.setRowCount(0);
    for (Producto p : controlador.getProductos()) {
        modelo.addRow(new Object[]{p.getNombre(), p.getPrecio(), p.getStock()});
    }
}



    // ============================================================
    // PANEL VENTAS (AQUÍ VA EL BOTÓN CERRAR SESIÓN)
    // ============================================================
private JPanel panelVentas() {
    JPanel panel = new JPanel(new BorderLayout(30, 30));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setBackground(new Color(245, 245, 250));
    ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
    // Opcional: escalar si quieres
    Image iconoEscalado = iconoVentana.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    setIconImage(iconoEscalado);
    // Panel izquierdo
    JPanel left = new JPanel(new GridBagLayout());
    left.setBackground(new Color(230, 235, 240));
    left.setPreferredSize(new Dimension(260, 400));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 10, 10, 10);

    // Label Producto
    JLabel lblProducto = new JLabel("Producto:");
    left.add(lblProducto, gbc);
    gbc.gridy++;

    // ComboBox al inicio
    JComboBox<String> comboProductos = new JComboBox<>();
    for (Producto p : controlador.getProductos()) comboProductos.addItem(p.getNombre());
    comboProductos.setMaximumRowCount(5);
    left.add(comboProductos, gbc);
    gbc.gridy++;

    // Espacio vertical de aproximadamente 4 cm antes del primer campo
    left.add(Box.createRigidArea(new Dimension(0, 120)), gbc);
    gbc.gridy++;

    // Label Cantidad
    JLabel lblCantidad = new JLabel("Cantidad:");
    left.add(lblCantidad, gbc);
    gbc.gridy++;

    // Campo de cantidad
    JTextField txtCantidad = new JTextField("1",10);
    left.add(txtCantidad, gbc);
    gbc.gridy++;

    // Botones
    JButton btnAgregar = new JButton("Agregar");
    JButton btnVender = new JButton("Vender");
    JButton btnLimpiar = new JButton("Limpiar");
    JButton btnCerrar = new JButton("Salir");

    Dimension btnSize = new Dimension(120, 32);
    btnAgregar.setPreferredSize(btnSize);
    btnVender.setPreferredSize(btnSize);
    btnLimpiar.setPreferredSize(btnSize);
    btnCerrar.setPreferredSize(btnSize);

    // Colores de los botones
    btnAgregar.setBackground(new Color(100, 180, 255)); btnAgregar.setForeground(Color.WHITE);
    btnVender.setBackground(new Color(100, 255, 180)); btnVender.setForeground(Color.WHITE);
    btnLimpiar.setBackground(new Color(255, 180, 100)); btnLimpiar.setForeground(Color.WHITE);
    btnCerrar.setBackground(new Color(255, 100, 100)); btnCerrar.setForeground(Color.WHITE);

    left.add(btnAgregar, gbc); gbc.gridy++;
    left.add(btnVender, gbc); gbc.gridy++;
    left.add(btnLimpiar, gbc); gbc.gridy++;
    gbc.insets = new Insets(40, 10, 10, 10);
    left.add(btnCerrar, gbc);

    // Panel derecho: tabla y subtotal
    JPanel right = new JPanel(new BorderLayout(15, 15));
    right.setBackground(new Color(255, 255, 255));

    String[] columnas = {"Producto", "Cantidad", "Precio"};
    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
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

    // Botones Editar / Eliminar por fila
    JPanel panelBotonesVenta = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    JButton btnEditarVenta = new JButton("Editar");
    JButton btnEliminarVenta = new JButton("Eliminar");
    btnEditarVenta.setBackground(new Color(100, 180, 255)); btnEditarVenta.setForeground(Color.WHITE);
    btnEliminarVenta.setBackground(new Color(255, 100, 100)); btnEliminarVenta.setForeground(Color.WHITE);
    panelBotonesVenta.add(btnEditarVenta); panelBotonesVenta.add(btnEliminarVenta);
    right.add(panelBotonesVenta, BorderLayout.NORTH);

    panel.add(left, BorderLayout.WEST);
    panel.add(right, BorderLayout.CENTER);

    // ----------------- LOGICA BOTONES -----------------
    btnAgregar.addActionListener(e -> {
    try {
        String nombre = comboProductos.getSelectedItem().toString();
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        if (cantidad <= 0) { JOptionPane.showMessageDialog(null, "Cantidad inválida."); return; }

        Producto p = controlador.getProductos().stream()
                .filter(pr -> pr.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElse(null);
        if (p == null) { JOptionPane.showMessageDialog(null, "Producto no encontrado."); return; }

        // Buscar si el producto ya está en la tabla
        boolean encontrado = false;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String nombreFila = modeloTabla.getValueAt(i, 0).toString();
            if (nombreFila.equalsIgnoreCase(nombre)) {
                int cantidadActual = (int) modeloTabla.getValueAt(i, 1);
                int nuevaCantidad = cantidadActual + cantidad;

                if (nuevaCantidad > p.getStock()) {
                    JOptionPane.showMessageDialog(null, "Stock insuficiente.");
                    return;
                }

                modeloTabla.setValueAt(nuevaCantidad, i, 1);
                modeloTabla.setValueAt(p.getPrecio() * nuevaCantidad, i, 2);
                encontrado = true;
                break;
            }
        }

        // Si no estaba en la tabla, se agrega nueva fila
        if (!encontrado) {
            if (cantidad > p.getStock()) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente.");
                return;
            }
            double precio = p.getPrecio() * cantidad;
            modeloTabla.addRow(new Object[]{p.getNombre(), cantidad, precio});
        }

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

        StringBuilder resumen = new StringBuilder();
        double totalVenta = 0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String nombreProd = modeloTabla.getValueAt(i, 0).toString();
            int cant = (int) modeloTabla.getValueAt(i, 1);

            Producto p = controlador.getProductos().stream()
                    .filter(pr -> pr.getNombre().equalsIgnoreCase(nombreProd))
                    .findFirst().orElse(null);

            if (p == null) continue;

            double precioTotalFila = p.getPrecio() * cant;
            totalVenta += precioTotalFila;

            resumen.append(nombreProd)
                    .append("\n Cantidad: ").append(cant)
                    .append("\n Precio : S/. ").append(String.format("%.2f", p.getPrecio()))
                    .append("\n");

            // Actualizar stock
            p.setStock(p.getStock() - cant);
        }

        resumen.append("\nTOTAL: S/. ").append(String.format("%.2f", totalVenta));

        // Limpiar tabla
        modeloTabla.setRowCount(0);
        actualizarSubtotal(modeloTabla);

        // Mostrar resumen de venta
        JOptionPane.showMessageDialog(null, resumen.toString(), "Venta Realizada", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
});


    btnLimpiar.addActionListener(e -> {
        modeloTabla.setRowCount(0);
        actualizarSubtotal(modeloTabla);
        txtCantidad.setText("1");
        comboProductos.setSelectedIndex(0);
    });

    btnCerrar.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) { dispose(); new LoginWindow(controlador).setVisible(true); }
    });

    btnEditarVenta.addActionListener(e -> {
        int fila = tabla.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione un producto para editar."); return; }
        int cantidadActual = (int) modeloTabla.getValueAt(fila, 1);
        String nombreProd = modeloTabla.getValueAt(fila, 0).toString();

        JTextField txtCantidadEdit = new JTextField(String.valueOf(cantidadActual));
        Object[] msg = {"Cantidad para " + nombreProd + ":", txtCantidadEdit};
        int opcion = JOptionPane.showConfirmDialog(null, msg, "Editar Producto en Venta", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int nuevaCantidad = Integer.parseInt(txtCantidadEdit.getText());
                Producto p = controlador.getProductos().stream()
                        .filter(pr -> pr.getNombre().equalsIgnoreCase(nombreProd)).findFirst().orElse(null);
                if (p == null) return;
                if (nuevaCantidad <= 0 || nuevaCantidad > p.getStock()) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida o superior al stock.");
                    return;
                }
                modeloTabla.setValueAt(nuevaCantidad, fila, 1);
                modeloTabla.setValueAt(p.getPrecio() * nuevaCantidad, fila, 2);
                actualizarSubtotal(modeloTabla);
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); }
        }
    });

    btnEliminarVenta.addActionListener(e -> {
        int fila = tabla.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar."); return; }
        modeloTabla.removeRow(fila);
        actualizarSubtotal(modeloTabla);
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

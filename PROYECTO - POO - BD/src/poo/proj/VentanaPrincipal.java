package poo.proj;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private Administrador admin;
    private EmpleadoVentas emp;

    private JLabel lblSubtotal;
    private JTextField txtSubtotal;

    // Listas temporales para gestionar indices de tabla
    private ArrayList<EmpleadoVentas> listaEmpleadosActual = new ArrayList<>();
    private ArrayList<Producto> listaProductosActual = new ArrayList<>();

    public VentanaPrincipal(Controlador controlador, Administrador admin, EmpleadoVentas emp) {
        this.controlador = controlador;
        this.admin = admin;
        this.emp = emp;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.75);
        int height = (int) (screen.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        setIconImage(iconoVentana.getImage());

        JTabbedPane pestañas = new JTabbedPane();
        if (admin != null) pestañas.addTab("Administrador", panelAdministrador());
        if (emp != null) pestañas.addTab("Ventas", panelVentas());

        add(pestañas, BorderLayout.CENTER);
    }

    // ============================================================
    // PANEL ADMINISTRADOR (MODIFICADO: AGREGADO BOTÓN VENTAS)
    // ============================================================
    private JPanel panelAdministrador() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel Izquierdo (Menú)
        JPanel panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setPreferredSize(new Dimension(200, getHeight()));
        panelIzquierda.setBackground(new Color(220, 225, 230));

        JButton btnTrabajador = crearBotonAdmin("Registrar Trabajador", new Color(100, 180, 255));
        JButton btnProducto = crearBotonAdmin("Registrar Producto", new Color(100, 255, 180));
        JButton btnStock = crearBotonAdmin("Ver Productos", new Color(255, 180, 100));
        JButton btnVerTrabajadores = crearBotonAdmin("Ver Trabajadores", new Color(180, 140, 255));
        
        // --- NUEVO BOTÓN ---
        JButton btnReporteVentas = crearBotonAdmin("Reporte Ventas", new Color(255, 100, 200)); // Color rosa/morado
        
        JButton btnCerrarSesion = crearBotonAdmin("Salir", new Color(255, 100, 100));

        panelIzquierda.add(Box.createVerticalStrut(30));
        panelIzquierda.add(btnTrabajador); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnProducto); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnStock); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnVerTrabajadores); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnReporteVentas); panelIzquierda.add(Box.createVerticalGlue()); // Agregado aquí
        panelIzquierda.add(btnCerrarSesion);

        // Panel Derecho (Contenido)
        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setBackground(new Color(245, 245, 250));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierda, panelDerecha);
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);
        panelPrincipal.add(splitPane, BorderLayout.CENTER);

        // Eventos de Navegación
        btnTrabajador.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelRegistrarTrabajador()));
        btnProducto.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelRegistrarProducto()));
        btnStock.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelVerProductos()));
        btnVerTrabajadores.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelVerTrabajadores()));
        
        // --- EVENTO NUEVO ---
        btnReporteVentas.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelReporteVentas()));

        btnCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Cerrar Sesión?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
                new LoginWindow(controlador).setVisible(true);
            }
        });

        return panelPrincipal;
    }

    private void cambiarPanel(JPanel contenedor, JPanel nuevoPanel) {
        contenedor.removeAll();
        contenedor.add(nuevoPanel, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }

    // --- NUEVO PANEL: REPORTE DE VENTAS ---
    private JPanel crearPanelReporteVentas() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Título o Cabecera
        JLabel lblTitulo = new JLabel("Historial de Ventas Recientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Producto", "Cantidad", "Total (S/.)", "Fecha y Hora"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable tabla = new JTable(modelo);
        
        // Llenar tabla usando el Controlador
        ArrayList<Object[]> ventas = controlador.getVentasRealizadas();
        for(Object[] v : ventas) {
            modelo.addRow(v);
        }

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        // Botón Actualizar
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> {
            modelo.setRowCount(0);
            for(Object[] v : controlador.getVentasRealizadas()) {
                modelo.addRow(v);
            }
        });
        JPanel pnlSur = new JPanel();
        pnlSur.add(btnActualizar);
        panel.add(pnlSur, BorderLayout.SOUTH);

        return panel;
    }

    // --- PANEL VER TRABAJADORES ---
    private JPanel crearPanelVerTrabajadores() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Nombre", "Apellido P.", "Apellido M.", "Documento", "Sueldo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable tabla = new JTable(modelo);
        actualizarTablaTrabajadores(modelo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEditar.setBackground(new Color(100, 180, 255)); btnEditar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(255, 100, 100)); btnEliminar.setForeground(Color.WHITE);
        panelBotones.add(btnEditar); panelBotones.add(btnEliminar);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione un trabajador."); return; }
            
            EmpleadoVentas empEdit = listaEmpleadosActual.get(fila);
            JTextField txtNombre = new JTextField(empEdit.getNombre());
            JTextField txtApP = new JTextField(empEdit.getApellidoPaterno());
            JTextField txtApM = new JTextField(empEdit.getApellidoMaterno());
            JTextField txtDoc = new JTextField(empEdit.getNumeroDocumento());
            JTextField txtSueldo = new JTextField(String.valueOf(empEdit.getSueldoBase()));

            Object[] msg = {"Nombre:", txtNombre, "Ap. Paterno:", txtApP, "Ap. Materno:", txtApM, "Documento:", txtDoc, "Sueldo:", txtSueldo};

            if (JOptionPane.showConfirmDialog(null, msg, "Editar", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                empEdit.setNombre(txtNombre.getText());
                empEdit.setApellidoPaterno(txtApP.getText());
                empEdit.setApellidoMaterno(txtApM.getText());
                empEdit.setNumeroDocumento(txtDoc.getText());
                empEdit.setSueldoBase(Double.parseDouble(txtSueldo.getText()));
                
                controlador.actualizarEmpleado(empEdit); // UPDATE BD
                actualizarTablaTrabajadores(modelo);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1 && JOptionPane.showConfirmDialog(null, "¿Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                controlador.eliminarEmpleado(listaEmpleadosActual.get(fila).getId()); // DELETE BD
                actualizarTablaTrabajadores(modelo);
            }
        });

        return panel;
    }

    private void actualizarTablaTrabajadores(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        listaEmpleadosActual = controlador.getEmpleados(); // Fetch BD
        for (EmpleadoVentas emp : listaEmpleadosActual) {
            modelo.addRow(new Object[]{emp.getNombre(), emp.getApellidoPaterno(), emp.getApellidoMaterno(), emp.getNumeroDocumento(), emp.getSueldoBase()});
        }
    }

    // --- PANEL REGISTRAR TRABAJADOR ---
    private JPanel crearPanelRegistrarTrabajador() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(200, 210, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridx = 0; gbc.gridy = 0;

        JTextField txtNombre = new JTextField(15);
        JTextField txtApP = new JTextField(15);
        JTextField txtApM = new JTextField(15);
        JTextField txtTipoDoc = new JTextField("DNI", 15);
        JTextField txtNroDoc = new JTextField(15);
        JTextField txtSueldo = new JTextField(15);

        panel.add(new JLabel("Nombre:"), gbc); gbc.gridx = 1; panel.add(txtNombre, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Ap. Paterno:"), gbc); gbc.gridx = 1; panel.add(txtApP, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Ap. Materno:"), gbc); gbc.gridx = 1; panel.add(txtApM, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Tipo Doc:"), gbc); gbc.gridx = 1; panel.add(txtTipoDoc, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Nro Doc:"), gbc); gbc.gridx = 1; panel.add(txtNroDoc, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Sueldo:"), gbc); gbc.gridx = 1; panel.add(txtSueldo, gbc); gbc.gridx = 0; gbc.gridy++;

        JButton btnRegistrar = new JButton("Guardar");
        btnRegistrar.setBackground(new Color(100, 180, 255)); btnRegistrar.setForeground(Color.WHITE);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy++;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            try {
                admin.registrarTrabajador(controlador, txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtTipoDoc.getText(), txtNroDoc.getText(), Double.parseDouble(txtSueldo.getText()));
                JOptionPane.showMessageDialog(null, "Trabajador registrado en BD.");
                txtNombre.setText(""); txtNroDoc.setText("");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); }
        });
        return panel;
    }

    // --- PANEL REGISTRAR PRODUCTO ---
    private JPanel crearPanelRegistrarProducto() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(200, 210, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridx = 0; gbc.gridy = 0;

        JTextField txtNombre = new JTextField(15);
        JTextField txtPrecio = new JTextField(15);
        JTextField txtStock = new JTextField(15);

        panel.add(new JLabel("Nombre:"), gbc); gbc.gridx = 1; panel.add(txtNombre, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Precio:"), gbc); gbc.gridx = 1; panel.add(txtPrecio, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Stock:"), gbc); gbc.gridx = 1; panel.add(txtStock, gbc); gbc.gridx = 0; gbc.gridy++;

        JButton btnRegistrar = new JButton("Guardar");
        btnRegistrar.setBackground(new Color(100, 255, 180)); btnRegistrar.setForeground(Color.WHITE);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy++;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            try {
                admin.registrarProducto(controlador, txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()));
                JOptionPane.showMessageDialog(null, "Producto registrado en BD.");
                txtNombre.setText("");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); }
        });
        return panel;
    }

    // --- PANEL VER PRODUCTOS ---
    private JPanel crearPanelVerProductos() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Nombre", "Precio", "Stock"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) { public boolean isCellEditable(int row, int col) { return false; } };
        JTable tabla = new JTable(modelo);
        actualizarTablaProductos(modelo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEditar.setBackground(new Color(100, 180, 255)); btnEditar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(255, 100, 100)); btnEliminar.setForeground(Color.WHITE);
        panelBotones.add(btnEditar); panelBotones.add(btnEliminar);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione producto."); return; }
            Producto p = listaProductosActual.get(fila);
            JTextField txtNombre = new JTextField(p.getNombre());
            JTextField txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
            JTextField txtStock = new JTextField(String.valueOf(p.getStock()));
            Object[] msg = {"Nombre:", txtNombre, "Precio:", txtPrecio, "Stock:", txtStock};

            if (JOptionPane.showConfirmDialog(null, msg, "Editar", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                p.setNombre(txtNombre.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setStock(Integer.parseInt(txtStock.getText()));
                controlador.actualizarProducto(p); // UPDATE BD
                actualizarTablaProductos(modelo);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1 && JOptionPane.showConfirmDialog(null, "¿Eliminar?", "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                controlador.eliminarProducto(listaProductosActual.get(fila).getId()); // DELETE BD
                actualizarTablaProductos(modelo);
            }
        });
        return panel;
    }

    private void actualizarTablaProductos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        listaProductosActual = controlador.getProductos(); // Fetch BD
        for (Producto p : listaProductosActual) {
            modelo.addRow(new Object[]{p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    // ============================================================
    // PANEL VENTAS (CON BOTÓN EDITAR CANTIDAD)
    // ============================================================
    private JPanel panelVentas() {
        JPanel panel = new JPanel(new BorderLayout(30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 250));

        // IZQUIERDA: Formulario
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(230, 235, 240));
        left.setPreferredSize(new Dimension(260, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(10, 10, 10, 10);

        left.add(new JLabel("Producto:"), gbc); gbc.gridy++;
        JComboBox<Producto> comboProductos = new JComboBox<>();
        // Cargar productos desde BD al iniciar
        for (Producto p : controlador.getProductos()) comboProductos.addItem(p);
        left.add(comboProductos, gbc); gbc.gridy++;

        left.add(Box.createRigidArea(new Dimension(0, 80)), gbc); gbc.gridy++;
        left.add(new JLabel("Cantidad:"), gbc); gbc.gridy++;
        JTextField txtCantidad = new JTextField("1", 10);
        left.add(txtCantidad, gbc); gbc.gridy++;

        JButton btnAgregar = new JButton("Agregar");
        JButton btnVender = new JButton("Vender");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Salir");
        Dimension btnSize = new Dimension(120, 32);
        btnAgregar.setPreferredSize(btnSize); btnVender.setPreferredSize(btnSize);
        btnLimpiar.setPreferredSize(btnSize); btnCerrar.setPreferredSize(btnSize);
        btnAgregar.setBackground(new Color(100, 180, 255)); btnAgregar.setForeground(Color.WHITE);
        btnVender.setBackground(new Color(100, 255, 180)); btnVender.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(255, 180, 100)); btnLimpiar.setForeground(Color.WHITE);
        btnCerrar.setBackground(new Color(255, 100, 100)); btnCerrar.setForeground(Color.WHITE);

        left.add(btnAgregar, gbc); gbc.gridy++; left.add(btnVender, gbc); gbc.gridy++;
        left.add(btnLimpiar, gbc); gbc.gridy++; gbc.insets = new Insets(40, 10, 10, 10); left.add(btnCerrar, gbc);

        // DERECHA: Tabla
        JPanel right = new JPanel(new BorderLayout(15, 15));
        String[] cols = {"Producto", "Cantidad", "Precio"};
        DefaultTableModel modeloTabla = new DefaultTableModel(cols, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        JTable tabla = new JTable(modeloTabla);
        right.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel subtotalPanel = new JPanel(new GridLayout(1, 2));
        lblSubtotal = new JLabel("Subtotal: S/.");
        txtSubtotal = new JTextField("0.00"); txtSubtotal.setEditable(false);
        subtotalPanel.add(lblSubtotal); subtotalPanel.add(txtSubtotal);
        right.add(subtotalPanel, BorderLayout.SOUTH);

        // --- BOTONES SUPERIORES DE LA TABLA ---
        JPanel panelBotonesVenta = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton btnEditarCant = new JButton("Editar Cantidad");
        btnEditarCant.setBackground(new Color(100, 180, 255)); 
        btnEditarCant.setForeground(Color.WHITE);
        
        JButton btnEliminarFila = new JButton("Quitar Item");
        btnEliminarFila.setBackground(new Color(255, 100, 100)); 
        btnEliminarFila.setForeground(Color.WHITE);

        panelBotonesVenta.add(btnEditarCant);
        panelBotonesVenta.add(btnEliminarFila);
        right.add(panelBotonesVenta, BorderLayout.NORTH);

        panel.add(left, BorderLayout.WEST); panel.add(right, BorderLayout.CENTER);

        // --- LOGICA VENTAS ---
        btnAgregar.addActionListener(e -> {
            Producto pSeleccionado = (Producto) comboProductos.getSelectedItem();
            try {
                int cant = Integer.parseInt(txtCantidad.getText());
                if (cant <= 0) { JOptionPane.showMessageDialog(null, "Cantidad inválida."); return; }
                if (cant > pSeleccionado.getStock()) {
                    JOptionPane.showMessageDialog(null, "Stock insuficiente (Disp: " + pSeleccionado.getStock() + ")");
                    return;
                }
                boolean existe = false;
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    if (modeloTabla.getValueAt(i, 0).toString().equals(pSeleccionado.getNombre())) {
                        int cantActual = (int) modeloTabla.getValueAt(i, 1);
                        if (cantActual + cant > pSeleccionado.getStock()) {
                            JOptionPane.showMessageDialog(null, "Excede stock total."); return;
                        }
                        modeloTabla.setValueAt(cantActual + cant, i, 1);
                        modeloTabla.setValueAt((cantActual + cant) * pSeleccionado.getPrecio(), i, 2);
                        existe = true; break;
                    }
                }
                if (!existe) modeloTabla.addRow(new Object[]{pSeleccionado.getNombre(), cant, pSeleccionado.getPrecio() * cant});
                actualizarSubtotal(modeloTabla);
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(null, "Ingrese un número válido."); }
        });

        // --- LÓGICA BOTÓN EDITAR CANTIDAD ---
        btnEditarCant.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione un producto."); return; }
            String nombreProd = modeloTabla.getValueAt(fila, 0).toString();
            int cantActual = (int) modeloTabla.getValueAt(fila, 1);
            Producto pReal = null;
            for (int k = 0; k < comboProductos.getItemCount(); k++) {
                if (comboProductos.getItemAt(k).getNombre().equals(nombreProd)) { pReal = comboProductos.getItemAt(k); break; }
            }
            if (pReal != null) {
                String input = JOptionPane.showInputDialog(null, "Nueva cantidad para " + nombreProd + ":", cantActual);
                if (input != null && !input.isEmpty()) {
                    try {
                        int nuevaCant = Integer.parseInt(input);
                        if (nuevaCant > 0 && nuevaCant <= pReal.getStock()) {
                            modeloTabla.setValueAt(nuevaCant, fila, 1);
                            modeloTabla.setValueAt(nuevaCant * pReal.getPrecio(), fila, 2);
                            actualizarSubtotal(modeloTabla);
                        } else JOptionPane.showMessageDialog(null, "Cantidad inválida o stock insuficiente.");
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(null, "Número inválido."); }
                }
            }
        });

        // --- LÓGICA VENDER ---
        btnVender.addActionListener(e -> {
            if (modeloTabla.getRowCount() == 0) return;
            try {
                StringBuilder ticket = new StringBuilder("VENTA REALIZADA:\n");
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    String nombre = modeloTabla.getValueAt(i, 0).toString();
                    int cant = (int) modeloTabla.getValueAt(i, 1);
                    Producto pReal = null;
                    for(int k=0; k<comboProductos.getItemCount(); k++){
                        if(comboProductos.getItemAt(k).getNombre().equals(nombre)) { pReal = comboProductos.getItemAt(k); break; }
                    }
                    if (pReal != null) {
                        pReal.setStock(pReal.getStock() - cant);
                        controlador.actualizarProducto(pReal);
                        controlador.registrarVenta(new Venta(pReal, cant));
                        ticket.append(nombre).append(" x").append(cant).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(null, ticket.toString());
                modeloTabla.setRowCount(0);
                actualizarSubtotal(modeloTabla);
                comboProductos.removeAllItems();
                for (Producto p : controlador.getProductos()) comboProductos.addItem(p);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnLimpiar.addActionListener(e -> { modeloTabla.setRowCount(0); actualizarSubtotal(modeloTabla); });
        
        btnEliminarFila.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) { modeloTabla.removeRow(fila); actualizarSubtotal(modeloTabla); }
            else { JOptionPane.showMessageDialog(null, "Seleccione un item para quitar."); }
        });
        
        btnCerrar.addActionListener(e -> { dispose(); new LoginWindow(controlador).setVisible(true); });

        return panel;
    }

    private void actualizarSubtotal(DefaultTableModel modeloTabla) {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += Double.parseDouble(modeloTabla.getValueAt(i, 2).toString());
        }
        txtSubtotal.setText(String.format("%.2f", subtotal));
    }

    private JButton crearBotonAdmin(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setBackground(color); boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        return boton;
    }
}
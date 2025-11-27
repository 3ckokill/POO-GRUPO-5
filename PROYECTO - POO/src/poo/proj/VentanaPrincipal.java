package poo.proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private Trabajador usuarioActual; 

    // COMPONENTES GLOBALES VENTAS
    private DefaultTableModel modeloTablaVentas;
    private JTable tablaCarrito;
    private JLabel lblGranTotal;
    private double totalAcumulado = 0.0;
    private ArrayList<DetalleTemporal> carrito = new ArrayList<>();
    private JComboBox<String> cmbBuscador;
    private boolean isUpdating = false;

    // COMPONENTES GLOBALES ADMIN (REPORTES)
    private JTable tablaReportes;
    private DefaultTableModel modeloReportes;

    public VentanaPrincipal(Controlador controlador, Trabajador usuario) {
        this.controlador = controlador;
        this.usuarioActual = usuario;

        setTitle("Sistema de Ventas - Usuario: " + usuario.getNombre());
        setSize(1000, 700); // Ventana más grande para los reportes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();

        if (usuario instanceof Administrador) {
            // El admin ahora ve un panel con sub-pestañas
            pestañas.addTab("Panel de Control", panelAdministradorCompleto((Administrador) usuario));
        } else if (usuario instanceof EmpleadoVentas) {
            pestañas.addTab("Punto de Venta", panelVentas((EmpleadoVentas) usuario));
        }

        // Botón Logout
        JPanel panelLogout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBackground(new Color(220, 53, 69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            new VentanaLogin(controlador).setVisible(true);
            this.dispose();
        });
        panelLogout.add(btnLogout);
        
        add(panelLogout, BorderLayout.NORTH);
        add(pestañas, BorderLayout.CENTER);
    }

    // ==========================================
    //  PANEL MAESTRO ADMINISTRADOR (CON SUB-PESTAÑAS)
    // ==========================================
    private JTabbedPane panelAdministradorCompleto(Administrador admin) {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Gestión Productos", panelGestProductos(admin));
        tabs.addTab("Gestión Empleados", panelGestEmpleados(admin));
        tabs.addTab("Reportes y Listas", panelReportes());
        return tabs;
    }

    // ------------------------------------------
    // 1. SUB-PANEL: GESTIÓN PRODUCTOS (Registrar y Stock)
    // ------------------------------------------
    private JPanel panelGestProductos(Administrador admin) {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 20)); // Dividido en 2 columnas
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- COLUMNA IZQUIERDA: REGISTRAR NUEVO ---
        JPanel panelReg = new JPanel(new GridLayout(5, 2, 10, 10));
        panelReg.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Producto"));
        
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();
        JButton btnRegistrar = new JButton("Guardar Producto");
        
        panelReg.add(new JLabel("Nombre:")); panelReg.add(txtNombre);
        panelReg.add(new JLabel("Precio:")); panelReg.add(txtPrecio);
        panelReg.add(new JLabel("Stock Inicial:")); panelReg.add(txtStock);
        panelReg.add(new JLabel("")); panelReg.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                admin.registrarProducto(controlador, txtNombre.getText(), 
                        Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()));
                JOptionPane.showMessageDialog(this, "Producto registrado.");
                txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // --- COLUMNA DERECHA: MODIFICAR STOCK ---
        JPanel panelMod = new JPanel(new GridLayout(6, 1, 10, 10));
        panelMod.setBorder(BorderFactory.createTitledBorder("Modificar Stock Existente"));
        
        JTextField txtBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar por Nombre");
        JLabel lblInfo = new JLabel("Producto: - | Stock Actual: -");
        JTextField txtNuevoStock = new JTextField();
        JButton btnActualizar = new JButton("Actualizar Stock");
        
        // Variable auxiliar para guardar el producto encontrado
        final Producto[] prodEncontrado = {null};

        btnBuscar.addActionListener(e -> {
            prodEncontrado[0] = controlador.buscarProductoPorNombre(txtBuscar.getText());
            if (prodEncontrado[0] != null) {
                lblInfo.setText("<html>Prod: <b>" + prodEncontrado[0].getNombre() + "</b><br>Stock Actual: " + prodEncontrado[0].getStock() + "</html>");
                txtNuevoStock.setText(String.valueOf(prodEncontrado[0].getStock()));
            } else {
                lblInfo.setText("Producto no encontrado.");
                prodEncontrado[0] = null;
            }
        });

        btnActualizar.addActionListener(e -> {
            if (prodEncontrado[0] != null) {
                try {
                    int nuevoStk = Integer.parseInt(txtNuevoStock.getText());
                    prodEncontrado[0].setStock(nuevoStk);
                    JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.");
                    lblInfo.setText("Prod: " + prodEncontrado[0].getNombre() + " | Stock Actual: " + prodEncontrado[0].getStock());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Stock inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Primero busque un producto.");
            }
        });

        panelMod.add(new JLabel("Buscar Nombre:"));
        JPanel pSearch = new JPanel(new BorderLayout()); pSearch.add(txtBuscar, BorderLayout.CENTER); pSearch.add(btnBuscar, BorderLayout.EAST);
        panelMod.add(pSearch);
        panelMod.add(lblInfo);
        panelMod.add(new JLabel("Nuevo Stock Total:"));
        panelMod.add(txtNuevoStock);
        panelMod.add(btnActualizar);

        panel.add(panelReg);
        panel.add(panelMod);
        return panel;
    }

    // ------------------------------------------
    // 2. SUB-PANEL: GESTIÓN EMPLEADOS
    // ------------------------------------------
    private JPanel panelGestEmpleados(Administrador admin) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150)); // Márgenes para centrar

        JTextField txtNom = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtSueldo = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        
        // Combo para elegir tipo
        String[] tipos = {"Vendedor", "Administrador"};
        JComboBox<String> cmbTipo = new JComboBox<>(tipos);
        
        JButton btnCrear = new JButton("Registrar Empleado");

        panel.add(new JLabel("Nombre Completo:")); panel.add(txtNom);
        panel.add(new JLabel("DNI (Usuario):")); panel.add(txtDni);
        panel.add(new JLabel("Contraseña:")); panel.add(txtPass);
        panel.add(new JLabel("Sueldo Base:")); panel.add(txtSueldo);
        panel.add(new JLabel("Rol:")); panel.add(cmbTipo);
        panel.add(new JLabel("")); panel.add(btnCrear);

        btnCrear.addActionListener(e -> {
            try {
                String dni = txtDni.getText();
                if (controlador.existeEmpleado(dni)) {
                    JOptionPane.showMessageDialog(this, "El DNI ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String pass = new String(txtPass.getPassword());
                double sueldo = Double.parseDouble(txtSueldo.getText());
                
                if (cmbTipo.getSelectedItem().equals("Vendedor")) {
                    // Crea un vendedor
                    admin.registrarTrabajador(controlador, txtNom.getText(), "", "", "DNI", dni, sueldo, pass);
                    // Nota: el método registrarTrabajador actual crea el objeto SIN password, deberíamos ajustarlo o
                    // buscar al empleado recién creado y ponerle la pass. 
                    // Solución rápida: Asignar pass manualmente buscando al ultimo:
                    EmpleadoVentas nuevo = controlador.getListaEmpleados().get(controlador.getListaEmpleados().size()-1);
                    // Como password es protected en Trabajador, necesitaríamos un setter público o un constructor mejorado.
                    // ASUMIENDO QUE HAS ACTUALIZADO EL MÉTODO registrarTrabajador PARA RECIBIR PASSWORD (recomendado)
                    // OJO: Si no lo has hecho, registraremos vendedores sin password. 
                    // Para este ejemplo, te recomiendo actualizar Administrador.registrarTrabajador.
                    
                    // TRUCO TEMPORAL (Reflection o acceso directo si está en mismo paquete):
                     nuevo.password = pass; // Funciona porque estamos en el mismo paquete poo.proj
                     
                } else {
                    // Crea un admin (Tu método registrarTrabajador solo crea Vendedores actualmente)
                    // Debes crear el Admin manualmente aquí
                    Administrador nuevoAdmin = new Administrador(txtNom.getText(), "", "", "DNI", dni, sueldo, 0, 0, pass);
                    controlador.registrarAdministrador(nuevoAdmin);
                }
                
                JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.");
                txtNom.setText(""); txtDni.setText(""); txtPass.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    // ------------------------------------------
    // 3. SUB-PANEL: REPORTES Y LISTAS
    // ------------------------------------------
    private JPanel panelReportes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Selector de vista
        JPanel panelTop = new JPanel();
        String[] opciones = {"Ver Productos", "Ver Empleados (Ventas)", "Ver Ventas Realizadas"};
        JComboBox<String> cmbReporte = new JComboBox<>(opciones);
        JButton btnCargar = new JButton("Cargar Datos");
        panelTop.add(new JLabel("Seleccione Reporte:"));
        panelTop.add(cmbReporte);
        panelTop.add(btnCargar);

        // Tabla
        modeloReportes = new DefaultTableModel();
        tablaReportes = new JTable(modeloReportes);
        JScrollPane scroll = new JScrollPane(tablaReportes);

        btnCargar.addActionListener(e -> {
            String seleccion = (String) cmbReporte.getSelectedItem();
            modeloReportes.setRowCount(0); // Limpiar tabla
            modeloReportes.setColumnCount(0); // Limpiar columnas

            if (seleccion.equals("Ver Productos")) {
                modeloReportes.addColumn("ID");
                modeloReportes.addColumn("Nombre");
                modeloReportes.addColumn("Precio");
                modeloReportes.addColumn("Stock");
                
                for (Producto p : controlador.getListaProductos()) {
                    modeloReportes.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock()});
                }

            } else if (seleccion.equals("Ver Empleados (Ventas)")) {
                modeloReportes.addColumn("ID");
                modeloReportes.addColumn("Nombre");
                modeloReportes.addColumn("DNI");
                modeloReportes.addColumn("Sueldo Base");
                
                for (EmpleadoVentas emp : controlador.getListaEmpleados()) {
                    modeloReportes.addRow(new Object[]{emp.getId(), emp.getNombre(), emp.getNumeroDocumento(), emp.getSueldoBase()});
                }

            } else if (seleccion.equals("Ver Ventas Realizadas")) {
                modeloReportes.addColumn("ID Venta");
                modeloReportes.addColumn("Producto");
                modeloReportes.addColumn("Cantidad");
                modeloReportes.addColumn("Total");
                
                for (Venta v : controlador.getListaVentas()) {
                    modeloReportes.addRow(new Object[]{v.getId(), v.getProducto().getNombre(), v.getCantidad(), v.getTotal()});
                }
            }
        });

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }


    // ==========================================
    //  PANEL VENTAS (MANTENIDO IGUAL - SOLO COPIAR Y PEGAR TU VERSIÓN ANTERIOR)
    // ==========================================
    // NOTA: Pego aquí tu versión exacta anterior para no romper nada.
    
    private JPanel panelVentas(EmpleadoVentas emp) {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelProducto = new JPanel(new GridLayout(1, 5, 10, 10));
        panelProducto.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Buscar y Agregar", TitledBorder.LEFT, TitledBorder.TOP));

        cmbBuscador = new JComboBox<>();
        cmbBuscador.setEditable(true);
        JTextComponent editorTexto = (JTextComponent) cmbBuscador.getEditor().getEditorComponent();
        editorTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                SwingUtilities.invokeLater(() -> filtrarListaProductos(editorTexto.getText()));
            }
        });

        JTextField txtCant = new JTextField();
        JButton btnAgregar = new JButton("Agregar +");
        btnAgregar.setBackground(new Color(0, 123, 255)); btnAgregar.setForeground(Color.WHITE);

        panelProducto.add(new JLabel("Producto:")); panelProducto.add(cmbBuscador);
        panelProducto.add(new JLabel("Cantidad:")); panelProducto.add(txtCant); panelProducto.add(btnAgregar);

        String[] columnas = {"Producto", "Precio Unit.", "Cantidad", "Subtotal"};
        modeloTablaVentas = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaCarrito = new JTable(modeloTablaVentas);
        JScrollPane scrollTabla = new JScrollPane(tablaCarrito);

        // Menú Click Derecho Ventas
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        JMenuItem itemModificar = new JMenuItem("Modificar Cantidad");
        popupMenu.add(itemModificar); popupMenu.add(itemEliminar);

        itemEliminar.addActionListener(e -> {
            int row = tablaCarrito.getSelectedRow();
            if (row >= 0) {
                double subtotal = carrito.get(row).productoReal.getPrecio() * carrito.get(row).cantidad;
                actualizarTotalVentas(-subtotal);
                carrito.remove(row); modeloTablaVentas.removeRow(row);
            }
        });
        
        itemModificar.addActionListener(e -> {
            int row = tablaCarrito.getSelectedRow();
            if (row >= 0) {
                DetalleTemporal item = carrito.get(row);
                String cantStr = JOptionPane.showInputDialog("Nueva cantidad:", item.cantidad);
                if (cantStr != null) {
                    int n = Integer.parseInt(cantStr);
                    if (n <= item.productoReal.getStock()) {
                        double dif = (item.productoReal.getPrecio() * n) - (item.productoReal.getPrecio() * item.cantidad);
                        item.cantidad = n;
                        modeloTablaVentas.setValueAt(n, row, 2);
                        modeloTablaVentas.setValueAt(item.productoReal.getPrecio()*n, row, 3);
                        actualizarTotalVentas(dif);
                    } else JOptionPane.showMessageDialog(this, "Stock insuficiente");
                }
            }
        });
        tablaCarrito.setComponentPopupMenu(popupMenu);

        JPanel panelInferior = new JPanel(new BorderLayout());
        lblGranTotal = new JLabel("TOTAL: S/ 0.00   ");
        lblGranTotal.setFont(new Font("Arial", Font.BOLD, 22));
        JButton btnProcesar = new JButton("CONFIRMAR VENTA");
        btnProcesar.setBackground(new Color(40, 167, 69)); btnProcesar.setForeground(Color.WHITE);
        panelInferior.add(lblGranTotal, BorderLayout.CENTER); panelInferior.add(btnProcesar, BorderLayout.EAST);

        btnAgregar.addActionListener(e -> {
            try {
                String nom = (String) cmbBuscador.getEditor().getItem();
                int cant = Integer.parseInt(txtCant.getText());
                Producto p = controlador.buscarProductoPorNombre(nom);
                if (p != null) {
                    int stockLista = 0;
                    int idx = -1;
                    for(int i=0; i<carrito.size(); i++) {
                        if(carrito.get(i).productoReal == p) { stockLista += carrito.get(i).cantidad; idx = i; }
                    }
                    if (stockLista + cant <= p.getStock()) {
                        if(idx != -1) {
                            carrito.get(idx).cantidad += cant;
                            modeloTablaVentas.setValueAt(carrito.get(idx).cantidad, idx, 2);
                            modeloTablaVentas.setValueAt(p.getPrecio()*carrito.get(idx).cantidad, idx, 3);
                        } else {
                            modeloTablaVentas.addRow(new Object[]{p.getNombre(), p.getPrecio(), cant, p.getPrecio()*cant});
                            carrito.add(new DetalleTemporal(p, cant));
                        }
                        actualizarTotalVentas(p.getPrecio()*cant);
                        cmbBuscador.getEditor().setItem(""); txtCant.setText(""); cmbBuscador.requestFocus();
                    } else JOptionPane.showMessageDialog(this, "Stock insuficiente");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error en datos"); }
        });

        btnProcesar.addActionListener(e -> {
            if(!carrito.isEmpty()){
                for(DetalleTemporal d : carrito) emp.registrarVenta(controlador, d.productoReal, d.cantidad);
                JOptionPane.showMessageDialog(this, "Venta OK");
                carrito.clear(); modeloTablaVentas.setRowCount(0); actualizarTotalVentas(-totalAcumulado);
            }
        });

        panelPrincipal.add(panelProducto, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        return panelPrincipal;
    }

    private void filtrarListaProductos(String texto) {
        if (isUpdating) return;
        isUpdating = true;
        JTextComponent editor = (JTextComponent) cmbBuscador.getEditor().getEditorComponent();
        ArrayList<Producto> res = controlador.buscarProductosFiltro(texto);
        cmbBuscador.removeAllItems();
        if (!res.isEmpty()) {
            for (Producto p : res) cmbBuscador.addItem(p.getNombre());
            if (!texto.isEmpty() && !cmbBuscador.isPopupVisible()) cmbBuscador.showPopup();
        } else cmbBuscador.hidePopup();
        editor.setText(texto);
        if (editor.getText().length() > 0) editor.setCaretPosition(editor.getText().length());
        isUpdating = false;
    }

    private void actualizarTotalVentas(double dif) {
        totalAcumulado += dif;
        if(totalAcumulado < 0) totalAcumulado = 0;
        lblGranTotal.setText("TOTAL: S/ " + String.format("%.2f", totalAcumulado) + "   ");
    }

    private class DetalleTemporal {
        Producto productoReal; int cantidad;
        public DetalleTemporal(Producto p, int c) { productoReal = p; cantidad = c; }
    }
}
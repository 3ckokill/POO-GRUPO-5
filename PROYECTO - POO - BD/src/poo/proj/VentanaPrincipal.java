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
        setTitle("Sistema de Ventas - " + (admin != null ? "Administrador" : "Vendedor"));

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/poo/proj/resources/logo.png"));
        setIconImage(iconoVentana.getImage());

        JTabbedPane pestañas = new JTabbedPane();
        if (admin != null) pestañas.addTab("Administrador", panelAdministrador());
        if (emp != null) pestañas.addTab("Ventas", panelVentas());

        add(pestañas, BorderLayout.CENTER);
    }

    // ============================================================
    // PANEL ADMINISTRADOR
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
        JButton btnReporteVentas = crearBotonAdmin("Reporte Ventas", new Color(255, 100, 200));
        JButton btnCerrarSesion = crearBotonAdmin("Salir", new Color(255, 100, 100));

        panelIzquierda.add(Box.createVerticalStrut(30));
        panelIzquierda.add(btnTrabajador); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnProducto); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnStock); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnVerTrabajadores); panelIzquierda.add(Box.createVerticalStrut(15));
        panelIzquierda.add(btnReporteVentas); panelIzquierda.add(Box.createVerticalGlue());
        panelIzquierda.add(btnCerrarSesion);

        // Panel Derecho (Contenido)
        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setBackground(new Color(245, 245, 250));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierda, panelDerecha);
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);
        panelPrincipal.add(splitPane, BorderLayout.CENTER);

        // Eventos
        btnTrabajador.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelRegistrarTrabajador()));
        btnProducto.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelRegistrarProducto()));
        btnStock.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelVerProductos()));
        btnVerTrabajadores.addActionListener(e -> cambiarPanel(panelDerecha, crearPanelVerTrabajadores()));
        
        // Reporte PDF
        btnReporteVentas.addActionListener(e -> controlador.generarReportePDF());

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

    // --- PANEL VER TRABAJADORES ---
    private JPanel crearPanelVerTrabajadores() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Nuevas columnas para ver el detalle del pago
        String[] columnas = {"Nombre Completo", "Documento", "Sueldo Base", "Horas Extra", "Total a Pagar"};
        
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        JTable tabla = new JTable(modelo);
        actualizarTablaTrabajadores(modelo); // Llenamos la tabla al iniciar

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnEditar = new JButton("Editar / Gestionar Pago");
        JButton btnEliminar = new JButton("Eliminar");
        
        btnEditar.setBackground(new Color(100, 180, 255)); btnEditar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(255, 100, 100)); btnEliminar.setForeground(Color.WHITE);
        
        panelBotones.add(btnEditar); 
        panelBotones.add(btnEliminar);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // --- BOTÓN EDITAR (LÓGICA DE PAGO) ---
        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { 
                JOptionPane.showMessageDialog(null, "Seleccione un trabajador de la tabla."); 
                return; 
            }
            
            // Obtenemos el objeto empleado seleccionado
            EmpleadoVentas empEdit = listaEmpleadosActual.get(fila);
            
            // Campos de Datos Personales
            JTextField txtNombre = new JTextField(empEdit.getNombre());
            JTextField txtApP = new JTextField(empEdit.getApellidoPaterno());
            JTextField txtApM = new JTextField(empEdit.getApellidoMaterno());
            JTextField txtDoc = new JTextField(empEdit.getNumeroDocumento());
            
            // Campos de Nómina
            JTextField txtSueldo = new JTextField(String.valueOf(empEdit.getSueldoBase()));
            JTextField txtHorasExtra = new JTextField(String.valueOf(empEdit.getHorasExtra()));
            
            // Etiqueta informativa
            JLabel lblInfo = new JLabel("NOTA: El pago por hora extra es fijo (S/. 5.00)");
            lblInfo.setForeground(Color.BLUE);
            lblInfo.setFont(new Font("Arial", Font.BOLD, 11));

            // Estructura de la ventana emergente
            Object[] msg = {
                "--- DATOS PERSONALES ---",
                "Nombre:", txtNombre,
                "Ap. Paterno:", txtApP,
                "Ap. Materno:", txtApM,
                "Documento:", txtDoc,
                " ",
                "--- GESTIÓN DE PAGO ---",
                "Sueldo Base (S/.):", txtSueldo,
                "Horas Extra trabajadas:", txtHorasExtra,
                lblInfo
            };

            int opcion = JOptionPane.showConfirmDialog(null, msg, "Editar Trabajador", JOptionPane.OK_CANCEL_OPTION);

            if (opcion == JOptionPane.OK_OPTION) {
                try {
                    // 1. Validaciones de texto vacío
                    if(txtNombre.getText().trim().isEmpty() || txtDoc.getText().trim().isEmpty()) 
                        throw new Exception("El nombre y documento son obligatorios.");

                    // 2. Actualizar datos básicos
                    empEdit.setNombre(txtNombre.getText().trim());
                    empEdit.setApellidoPaterno(txtApP.getText().trim());
                    empEdit.setApellidoMaterno(txtApM.getText().trim());
                    empEdit.setNumeroDocumento(txtDoc.getText().trim());
                    
                    // 3. Actualizar datos de pago
                    double nuevoSueldo = Double.parseDouble(txtSueldo.getText().trim());
                    int nuevasHoras = Integer.parseInt(txtHorasExtra.getText().trim());
                    
                    // Validaciones de negocio
                    if(nuevoSueldo <= 0) throw new Exception("El sueldo base debe ser mayor a 0.");
                    if(nuevasHoras < 0) throw new Exception("Las horas extra no pueden ser negativas.");

                    // Aplicamos cambios al objeto
                    empEdit.setSueldoBase(nuevoSueldo);
                    empEdit.setHorasExtra(nuevasHoras);
                    empEdit.setPagoPorHoraExtra(5.0); // REQUISITO: SIEMPRE 5
                    
                    // 4. Guardar en Base de Datos
                    controlador.actualizarEmpleado(empEdit); 
                    
                    // 5. Refrescar la tabla visual
                    actualizarTablaTrabajadores(modelo);
                    JOptionPane.showMessageDialog(null, "Trabajador actualizado correctamente.");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El sueldo y las horas deben ser números válidos.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
                }
            }
        });

        // BOTÓN ELIMINAR
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1 && JOptionPane.showConfirmDialog(null, "¿Eliminar trabajador?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                try {
                    controlador.eliminarEmpleado(listaEmpleadosActual.get(fila).getId());
                    actualizarTablaTrabajadores(modelo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    // --- MÉTODO AUXILIAR PARA LLENAR LA TABLA CON LOS CÁLCULOS ---
    private void actualizarTablaTrabajadores(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        listaEmpleadosActual = controlador.getEmpleados(); 
        
        for (EmpleadoVentas emp : listaEmpleadosActual) {
            // Aseguramos que el cálculo visual use el pago fijo de 5
            emp.setPagoPorHoraExtra(5.0); 
            
            modelo.addRow(new Object[]{
                emp.getNombre() + " " + emp.getApellidoPaterno(), // Nombre completo
                emp.getNumeroDocumento(),
                String.format("S/. %.2f", emp.getSueldoBase()),   // Sueldo Base
                emp.getHorasExtra(),                              // Cantidad Horas
                String.format("S/. %.2f", emp.calcularSueldoTotal()) // Total (Base + 5*Horas)
            });
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
        panel.add(new JLabel("Tipo Doc (DNI/CE):"), gbc); gbc.gridx = 1; panel.add(txtTipoDoc, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Nro Doc:"), gbc); gbc.gridx = 1; panel.add(txtNroDoc, gbc); gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Sueldo:"), gbc); gbc.gridx = 1; panel.add(txtSueldo, gbc); gbc.gridx = 0; gbc.gridy++;

        JButton btnRegistrar = new JButton("Guardar Trabajador");
        btnRegistrar.setBackground(new Color(100, 180, 255)); btnRegistrar.setForeground(Color.WHITE);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy++;
        panel.add(btnRegistrar, gbc);

        // LÓGICA DEL BOTÓN
        btnRegistrar.addActionListener(e -> {
            try {
                // 1. Validar campos vacíos
                if (txtNombre.getText().trim().isEmpty() || txtNroDoc.getText().trim().isEmpty() || 
                    txtApP.getText().trim().isEmpty() || txtApM.getText().trim().isEmpty()) {
                    throw new Exception("Todos los campos son obligatorios.");
                }

                // 2. Validar que el documento sean SOLO NÚMEROS (Regex)
                if (!txtNroDoc.getText().trim().matches("[0-9]+")) {
                    throw new Exception("El Documento solo debe contener números.");
                }

                // 3. Obtener sueldo
                double sueldo = Double.parseDouble(txtSueldo.getText().trim());

                // 4. Intentar registrar (La clase Trabajador validará longitud DNI y sueldo positivo)
                // Usamos trim() y toUpperCase() para limpiar entradas
                admin.registrarTrabajador(controlador, 
                        txtNombre.getText().trim(), 
                        txtApP.getText().trim(), 
                        txtApM.getText().trim(), 
                        txtTipoDoc.getText().trim().toUpperCase(), // IgnoreCase manual
                        txtNroDoc.getText().trim(), 
                        sueldo);
                
                JOptionPane.showMessageDialog(null, "¡Trabajador registrado exitosamente!");
                
                // 5. LIMPIEZA DE TODOS LOS CAMPOS
                txtNombre.setText(""); 
                txtApP.setText(""); 
                txtApM.setText("");
                txtTipoDoc.setText("DNI"); // Resetear a valor por defecto
                txtNroDoc.setText(""); 
                txtSueldo.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: El sueldo debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                // Captura errores de lógica de negocio (DNI incorrecto, sueldo negativo)
                JOptionPane.showMessageDialog(null, "Error de Datos: " + ex.getMessage(), "Datos Inválidos", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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

        JButton btnRegistrar = new JButton("Guardar Producto");
        btnRegistrar.setBackground(new Color(100, 255, 180)); btnRegistrar.setForeground(Color.WHITE);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy++;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            try {
                if (txtNombre.getText().trim().isEmpty()) throw new Exception("El nombre del producto es obligatorio.");
                
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());

                if(precio < 0 || stock < 0) throw new Exception("El precio y stock no pueden ser negativos.");

                admin.registrarProducto(controlador, txtNombre.getText().trim(), precio, stock);
                
                JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");
                
                // LIMPIEZA DE TODOS LOS CAMPOS
                txtNombre.setText(""); 
                txtPrecio.setText(""); 
                txtStock.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Precio debe ser decimal y Stock entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panel;
    }

    // --- PANEL VER PRODUCTOS ---
    private JPanel crearPanelVerProductos() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"#", "Nombre", "Precio", "Stock"};
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

        // EDITAR PRODUCTO
        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione producto."); return; }
            
            Producto p = listaProductosActual.get(fila);
            JTextField txtNombre = new JTextField(p.getNombre());
            JTextField txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
            JTextField txtStock = new JTextField(String.valueOf(p.getStock()));
            Object[] msg = {"Nombre:", txtNombre, "Precio:", txtPrecio, "Stock:", txtStock};

            if (JOptionPane.showConfirmDialog(null, msg, "Editar Producto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    if (txtNombre.getText().trim().isEmpty()) throw new Exception("El nombre no puede estar vacío.");
                    
                    p.setNombre(txtNombre.getText().trim());
                    p.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                    p.setStock(Integer.parseInt(txtStock.getText().trim()));
                    
                    if(p.getPrecio() < 0 || p.getStock() < 0) throw new Exception("Valores negativos no permitidos.");

                    controlador.actualizarProducto(p); 
                    actualizarTablaProductos(modelo);
                    JOptionPane.showMessageDialog(null, "Producto actualizado.");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese números válidos para precio y stock.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ELIMINAR PRODUCTO
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                if(JOptionPane.showConfirmDialog(null, "¿Eliminar este producto?", "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    try {
                        controlador.eliminarProducto(listaProductosActual.get(fila).getId());
                        actualizarTablaProductos(modelo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar (posiblemente tiene ventas asociadas).");
                    }
                }
            }
        });
        return panel;
    }

    private void actualizarTablaProductos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        listaProductosActual = controlador.getProductos();
        int contador = 1;
        for (Producto p : listaProductosActual) {
            // Muestra un contador visual (1, 2, 3...) en lugar del ID de la BD
            modelo.addRow(new Object[]{contador++, p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    // ============================================================
    // PANEL VENTAS
    // ============================================================
    private JPanel panelVentas() {
        JPanel panel = new JPanel(new BorderLayout(30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 250));

        // IZQUIERDA
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(230, 235, 240));
        left.setPreferredSize(new Dimension(260, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(10, 10, 10, 10);

        left.add(new JLabel("Producto:"), gbc); gbc.gridy++;
        JComboBox<Producto> comboProductos = new JComboBox<>();
        try {
            for (Producto p : controlador.getProductos()) comboProductos.addItem(p);
        } catch (Exception ex) { System.out.println("Error cargando productos: " + ex.getMessage()); }
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

        // DERECHA
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

        JPanel panelBotonesVenta = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnEditarCant = new JButton("Editar Cantidad");
        btnEditarCant.setBackground(new Color(100, 180, 255)); btnEditarCant.setForeground(Color.WHITE);
        JButton btnEliminarFila = new JButton("Quitar Item");
        btnEliminarFila.setBackground(new Color(255, 100, 100)); btnEliminarFila.setForeground(Color.WHITE);
        panelBotonesVenta.add(btnEditarCant); panelBotonesVenta.add(btnEliminarFila);
        right.add(panelBotonesVenta, BorderLayout.NORTH);

        panel.add(left, BorderLayout.WEST); panel.add(right, BorderLayout.CENTER);

        // --- LOGICA VENTAS (CON TRY-CATCH) ---
        
        // AGREGAR
        btnAgregar.addActionListener(e -> {
            try {
                if (comboProductos.getItemCount() == 0) throw new Exception("No hay productos seleccionables.");
                Producto pSeleccionado = (Producto) comboProductos.getSelectedItem();
                
                int cant = Integer.parseInt(txtCantidad.getText().trim());
                if (cant <= 0) throw new Exception("La cantidad debe ser mayor a 0.");
                if (cant > pSeleccionado.getStock()) throw new Exception("Stock insuficiente (Disponible: " + pSeleccionado.getStock() + ")");

                boolean existe = false;
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    if (modeloTabla.getValueAt(i, 0).toString().equals(pSeleccionado.getNombre())) {
                        int cantActual = (int) modeloTabla.getValueAt(i, 1);
                        if (cantActual + cant > pSeleccionado.getStock()) throw new Exception("La suma excede el stock disponible.");
                        
                        modeloTabla.setValueAt(cantActual + cant, i, 1);
                        modeloTabla.setValueAt((cantActual + cant) * pSeleccionado.getPrecio(), i, 2);
                        existe = true; break;
                    }
                }
                if (!existe) modeloTabla.addRow(new Object[]{pSeleccionado.getNombre(), cant, pSeleccionado.getPrecio() * cant});
                actualizarSubtotal(modeloTabla);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero en la cantidad.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        // EDITAR CANTIDAD
        btnEditarCant.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) { JOptionPane.showMessageDialog(null, "Seleccione un producto."); return; }

            String nombreProd = modeloTabla.getValueAt(fila, 0).toString();
            int cantActual = (int) modeloTabla.getValueAt(fila, 1);
            
            // Buscar prod real para validar stock
            Producto pReal = null;
            for(int k=0; k<comboProductos.getItemCount(); k++){
                if(comboProductos.getItemAt(k).getNombre().equals(nombreProd)) { pReal = comboProductos.getItemAt(k); break; }
            }

            if (pReal != null) {
                String input = JOptionPane.showInputDialog(null, "Nueva cantidad:", cantActual);
                if (input != null) {
                    try {
                        int nuevaCant = Integer.parseInt(input);
                        if (nuevaCant <= 0) throw new Exception("La cantidad debe ser positiva.");
                        if (nuevaCant > pReal.getStock()) throw new Exception("Stock insuficiente (Máx: " + pReal.getStock() + ")");
                        
                        modeloTabla.setValueAt(nuevaCant, fila, 1);
                        modeloTabla.setValueAt(nuevaCant * pReal.getPrecio(), fila, 2);
                        actualizarSubtotal(modeloTabla);
                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        // VENDER
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
                
                // Recargar combo
                comboProductos.removeAllItems();
                for (Producto p : controlador.getProductos()) comboProductos.addItem(p);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al procesar venta: " + ex.getMessage());
            }
        });

        btnLimpiar.addActionListener(e -> { modeloTabla.setRowCount(0); actualizarSubtotal(modeloTabla); });
        btnEliminarFila.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) { modeloTabla.removeRow(fila); actualizarSubtotal(modeloTabla); }
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
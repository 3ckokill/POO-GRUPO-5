package poo.proj;

import java.sql.*;
import java.util.ArrayList;

public class Controlador {

    // --- CARGAR LISTAS DESDE BD ---

    public ArrayList<EmpleadoVentas> getEmpleados() {
        ArrayList<EmpleadoVentas> lista = new ArrayList<>();
        String sql = "SELECT * FROM trabajadores WHERE rol = 'VENDEDOR'";
        
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                EmpleadoVentas emp = new EmpleadoVentas(
                    rs.getString("nombre"), rs.getString("ap_paterno"), rs.getString("ap_materno"),
                    rs.getString("tipo_doc"), rs.getString("num_doc"), rs.getDouble("sueldo"), 0, 0
                );
                emp.setId(rs.getInt("id"));
                lista.add(emp);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("stock")
                );
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // --- LOGIN ---

    public Administrador validarAdministrador(String nombre, String apPaterno, String apMaterno, String nroDoc) {
        String sql = "SELECT * FROM trabajadores WHERE nombre=? AND ap_paterno=? AND ap_materno=? AND num_doc=? AND rol='ADMIN'";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre); ps.setString(2, apPaterno);
            ps.setString(3, apMaterno); ps.setString(4, nroDoc);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrador admin = new Administrador(
                    rs.getString("nombre"), rs.getString("ap_paterno"), rs.getString("ap_materno"),
                    rs.getString("tipo_doc"), rs.getString("num_doc"), rs.getDouble("sueldo"), 0, 0
                );
                admin.setId(rs.getInt("id"));
                return admin;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public EmpleadoVentas validarEmpleado(String nombre, String apPaterno, String apMaterno, String nroDoc) {
        String sql = "SELECT * FROM trabajadores WHERE nombre=? AND ap_paterno=? AND ap_materno=? AND num_doc=? AND rol='VENDEDOR'";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre); ps.setString(2, apPaterno);
            ps.setString(3, apMaterno); ps.setString(4, nroDoc);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EmpleadoVentas emp = new EmpleadoVentas(
                    rs.getString("nombre"), rs.getString("ap_paterno"), rs.getString("ap_materno"),
                    rs.getString("tipo_doc"), rs.getString("num_doc"), rs.getDouble("sueldo"), 0, 0
                );
                emp.setId(rs.getInt("id"));
                return emp;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // --- ESCRITURA EN BD ---

    public void registrarEmpleado(EmpleadoVentas emp) {
        String sql = "INSERT INTO trabajadores (nombre, ap_paterno, ap_materno, tipo_doc, num_doc, sueldo, password, rol) VALUES (?, ?, ?, ?, ?, ?, '123456', 'VENDEDOR')";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre()); ps.setString(2, emp.getApellidoPaterno());
            ps.setString(3, emp.getApellidoMaterno()); ps.setString(4, emp.getTipoDocumento());
            ps.setString(5, emp.getNumeroDocumento()); ps.setDouble(6, emp.getSueldoBase());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void actualizarEmpleado(EmpleadoVentas emp) {
        String sql = "UPDATE trabajadores SET nombre=?, ap_paterno=?, ap_materno=?, num_doc=?, sueldo=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre()); ps.setString(2, emp.getApellidoPaterno());
            ps.setString(3, emp.getApellidoMaterno()); ps.setString(4, emp.getNumeroDocumento());
            ps.setDouble(5, emp.getSueldoBase()); ps.setInt(6, emp.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void eliminarEmpleado(int id) {
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement("DELETE FROM trabajadores WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void registrarProducto(Producto prod) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prod.getNombre()); ps.setDouble(2, prod.getPrecio()); ps.setInt(3, prod.getStock());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void actualizarProducto(Producto prod) {
        String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prod.getNombre()); ps.setDouble(2, prod.getPrecio());
            ps.setInt(3, prod.getStock()); ps.setInt(4, prod.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void eliminarProducto(int id) {
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void registrarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (producto_id, cantidad, total) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, venta.getProducto().getId());
            ps.setInt(2, venta.getCantidad());
            ps.setDouble(3, venta.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public ArrayList<Object[]> getVentasRealizadas() {
        ArrayList<Object[]> lista = new ArrayList<>();
        // Esta consulta une la tabla VENTAS con PRODUCTOS para traer el nombre
        String sql = "SELECT p.nombre, v.cantidad, v.total, v.fecha_registro " +
                     "FROM ventas v " +
                     "JOIN productos p ON v.producto_id = p.id " +
                     "ORDER BY v.fecha_registro DESC"; // Muestra las más recientes primero

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("cantidad"),
                    rs.getDouble("total"),
                    rs.getString("fecha_registro")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}
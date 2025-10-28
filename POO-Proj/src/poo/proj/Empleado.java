package poo.proj;

import java.util.ArrayList;
import java.util.List;

public abstract class  Empleado extends Trabajador {
        
    private List<Pedido> pedidosGestionados;


    public Empleado(List<Pedido> pedidosGestionados, int idEmpleado, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super(idEmpleado, id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.pedidosGestionados = pedidosGestionados;
    }
    
    public void buscarEmpleado() {
    }

    public void listarEmpleados() {
    }

    public void verHistorialPedidos() {
    }

    public List<Pedido> getPedidosGestionados() {
        return pedidosGestionados;
    }

    public void setPedidosGestionados(List<Pedido> pedidosGestionados) {
        this.pedidosGestionados = pedidosGestionados;
    }

    @Override
    public void mostrarDatos() {
        
    }
}

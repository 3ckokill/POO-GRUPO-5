package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Trabajador {

    private List<Pedido> pedidosGestionados;

    public Empleado() {
        super();
        this.pedidosGestionados = new ArrayList<>();
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
}

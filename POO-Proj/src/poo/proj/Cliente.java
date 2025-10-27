package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private String tipoIdentificacion;
    private String numeroDocumento;
    private String tipoCliente;

    private List<Pedido> pedidosAsociados;

    public Cliente() {
        super();
        this.pedidosAsociados = new ArrayList<>();
    }

    public void registrarCliente() {
    }

    public void modificarCliente() {
    }

    public void buscarCliente() {
    }

    public void listarClientes() {
    }

    public void mostrarPedidosAsociados() {
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Pedido> getPedidosAsociados() {
        return pedidosAsociados;
    }

    public void setPedidosAsociados(List<Pedido> pedidosAsociados) {
        this.pedidosAsociados = pedidosAsociados;
    }
}
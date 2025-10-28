package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private String tipoIdentificacion;
    private String tipoCliente;

    private List<Pedido> pedidosAsociados;

    public Cliente(String tipoIdentificacion, String numeroDocumento, String tipoCliente, List<Pedido> pedidosAsociados, 
            int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento,
            String correo, String telefono, String direccion) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroDocumento = numeroDocumento;
        this.tipoCliente = tipoCliente;
        this.pedidosAsociados = pedidosAsociados;
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

    @Override
    public void mostrarDatos() {
        
    }
}
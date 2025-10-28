package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Trabajador {

    private List<Reporte> reportesRevisados;

    public Gerente(List<Reporte> reportesRevisados, int idEmpleado, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super(idEmpleado, id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.reportesRevisados = reportesRevisados;
    }



    public void evaluarDesempeño() {
    }

    public void revisarReportes() {
    }

    public void aprobarPedidosEspeciales() {
    }

    public List<Reporte> getReportesRevisados() {
        
    }

    public void setReportesRevisados(List<Reporte> reportesRevisados) {
        this.reportesRevisados = reportesRevisados;
    }

    @Override
    public void mostrarDatos() {
        
    }
}

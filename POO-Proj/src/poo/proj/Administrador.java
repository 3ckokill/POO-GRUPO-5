package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Trabajador {

    private List<Reporte> reportesGenerados;

    public Administrador() {
        super();
        this.reportesGenerados = new ArrayList<>();
    }

    @Override
    public void registrarEmpleado() {
    }

    @Override
    public void eliminarEmpleado() {
    }

    public void generarReportes() {
    }

    public void consultarHistorialPedidos() {
    }

    public List<Reporte> getReportesGenerados() {
        return reportesGenerados;
    }

    public void setReportesGenerados(List<Reporte> reportesGenerados) {
        this.reportesGenerados = reportesGenerados;
    }

    @Override
    public void mostrarDatos() {
       
    }
}
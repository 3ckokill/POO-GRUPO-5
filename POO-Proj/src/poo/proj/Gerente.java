package poo.proj;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Trabajador {

    private List<Reporte> reportesRevisados;

    public Gerente() {
        super();
        this.reportesRevisados = new ArrayList<>();
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
}

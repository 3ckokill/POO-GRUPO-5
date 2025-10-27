package poo.proj;

import java.util.Date;

public class Reporte {

    private int idReporte;
    private String tipoReporte;
    private Date fechaInicio;
    private Date fechaFin;

    private Administrador administrador;
    private Gerente gerente;

    public Reporte() {
    }

    public void generarReporte() {
    }

    public void generarReporteVentas() {
    }

    public void generarReporteMaterialesBajoStock() {
    }

    public void generarReportePedidosPorCliente() {
    }

    public void generarReportePedidosPorEmpleado() {
    }

    public void mostrarEstado() {
    }

    public void imprimirReporte() {
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}
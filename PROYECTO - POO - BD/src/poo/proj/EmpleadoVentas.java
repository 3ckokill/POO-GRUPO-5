package poo.proj;

public class EmpleadoVentas extends Trabajador {

    public EmpleadoVentas(String nombre, String apellidoPaterno, String apellidoMaterno, 
            String tipoDocumento, String numeroDocumento, double sueldoBase, int horasExtra, double pagoPorHoraExtra) {
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, sueldoBase, horasExtra, pagoPorHoraExtra);
    }

    @Override
    public double calcularSueldoTotal() {
        return sueldoBase + (horasExtra * pagoPorHoraExtra);
    }

    public void mostrarDatos() {
        System.out.println("Vendedor: " + nombreCompleto());
    }
}
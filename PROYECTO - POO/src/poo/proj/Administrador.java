package poo.proj;

public class Administrador extends Trabajador {

    public Administrador(String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, double sueldoBase, int horasExtra, double pagoPorHoraExtra) {
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, sueldoBase, horasExtra, pagoPorHoraExtra);
    }
    
    @Override
    public double calcularSueldoTotal() {
        return getSueldoBase();
    }   
    @Override
    public void mostrarDatos() {
        System.out.println("----Administrador----");
        super.mostrarDatos();
    }

}

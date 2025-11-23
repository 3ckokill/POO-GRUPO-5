package poo.proj;

public class Empleado extends Trabajador {

    public static final double SUELDO_BASE = 1300.0;

    public Empleado(String nombre, int par, String apPat, String apMat, String tipoDoc, String numDoc, String correo, String telefono, String direccion, String direccion1) {

        super("Empleado de Ventas", SUELDO_BASE,
                nombre, apPat, apMat, tipoDoc, numDoc, correo, telefono, direccion);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== EMPLEADO DE VENTAS ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario: S/." + salario);
        System.out.println("==========================");
    }
}

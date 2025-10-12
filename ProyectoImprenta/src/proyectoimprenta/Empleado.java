/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoimprenta;

/**
 *
 * @author 3ckok
 */
public class Empleado extends Persona {

    String sueldo;
    String cargo;

    public Empleado(String sueldo, String cargo, String apellidoPaterno, String apellidoMaterno, String tipoDocumento,
            String NumeroDoumento, String dirrecion, String numeroCelular, String correo) {
        super(apellidoPaterno, apellidoMaterno, tipoDocumento, NumeroDoumento, dirrecion, numeroCelular, correo);
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    @Override
    public void mostrarDatos() {

        System.out.println("Empleado\n");
        System.out.println("Nombres y Apellidos: " + this.nombreCompleto + " " + this.apellidoPaterno + " "
                + this.apellidoMaterno);
        System.out.println("Tipo de Documento: " + this.tipoDocumento);
        System.out.println("Numero de Documento: " + this.NumeroDoumento);
        System.out.println("Dirrecion: " + this.dirrecion);
        System.out.println("Numero de Celular: " + this.numeroCelular);
        System.out.println("Correo: " + this.correo);
        System.out.println("Sueldo: " + this.sueldo);
        System.out.println("Cargo: " + this.cargo);
        System.out.println("\n");
    }

}

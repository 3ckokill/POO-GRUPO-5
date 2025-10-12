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

    public Empleado(String sueldo, String cargo, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String NumeroDoumento, String dirrecion, String numeroCelular, String correo) {
        super(apellidoPaterno, apellidoMaterno, tipoDocumento, NumeroDoumento, dirrecion, numeroCelular, correo);
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    @Override
    public void mostrarDatos() {
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoimprenta;


public abstract class Persona {
    
    protected String nombreCompleto;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipoDocumento;
    protected String NumeroDoumento;
    protected String dirrecion;
    protected String numeroCelular;
    protected String correo;

    public Persona(String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String NumeroDoumento, String dirrecion, String numeroCelular, String correo) {
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.NumeroDoumento = NumeroDoumento;
        this.dirrecion = dirrecion;
        this.numeroCelular = numeroCelular;
        this.correo = correo;
    }
    
    public abstract void mostrarDatos();
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoimprenta;

/**
 *
 * @author 3ckok
 */
public class ProyectoImprenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Vista vista = new Vista(new ControladorPersonas());
        vista.ejecutarMenu();
        System.out.println("Hola");
    }
}

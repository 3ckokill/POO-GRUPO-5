/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poogrupo5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author luisd
 */
public class POOgrupo5 {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Persona> listaPersonas = new ArrayList<>();

        try {
            System.out.println("¿Cuántas personas desea ingresar?");
            int n = sc.nextInt();
            sc.nextLine(); 

            

        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        } finally {
            sc.close();
        }
    }
}

class Persona {
    private String nombre;
    private double sueldo;

    public Persona(String nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Sueldo: " + sueldo;
    }
}
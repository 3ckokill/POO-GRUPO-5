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
            for (int i = 0; i < n; i++) 
            {
                System.out.println("Ingrese el nombre de la persona " + (i + 1) + ":");
                String nombre = sc.nextLine();

                System.out.println("Ingrese (1) sueldo o (2) sueldo con bono:");
                int opcion = sc.nextInt();
                double sueldo, bono;

                if (opcion == 1) {
                    System.out.println("Ingrese el sueldo:");
                    sueldo = sc.nextDouble();
                    listaPersonas.add(new Persona(nombre, sueldo));
                } else if (opcion == 2) {
                    System.out.println("Ingrese el sueldo:");
                    sueldo = sc.nextDouble();
                    System.out.println("Ingrese el bono:");
                    bono = sc.nextDouble();
                    Persona p = new Persona(nombre, 0); 
                    p.asignarSueldo(sueldo, bono);  
                    listaPersonas.add(p);
                } else {
                    System.out.println("Opción inválida, se asignará sueldo 0.");
                    listaPersonas.add(new Persona(nombre, 0));
                }
                sc.nextLine(); 
            }
            System.out.println("\nLista de personas ingresadas:");
            for (Persona p : listaPersonas) {
                System.out.println(p);
            }


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
    
    public void asignarSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public void asignarSueldo(double sueldo, double bono) {
        this.sueldo = sueldo + bono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Sueldo: " + sueldo;
    }
}
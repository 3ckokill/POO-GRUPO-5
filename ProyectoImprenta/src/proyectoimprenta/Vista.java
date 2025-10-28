package proyectoimprenta;

import java.util.Scanner;
public class Vista {
    
    ControladorPersonas control;

    public Vista(ControladorPersonas control) {
        this.control = control;
    }

    public void ejecutarMenu(){
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("Menu:");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Mostrar Personas");
            System.out.println("0. Salir");
            opcion = sc.nextInt();
            sc.nextLine(); 
            switch(opcion){
                case 1:
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    System.out.println("Edad:");
                    int edad = sc.nextInt();
                case 2:
                    control.mostrarPersonas();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
            System.out.println();
        }
        while(opcion != 0);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fileexample;

import java.nio.file.*;
import java.io.*;
import java.util.*;
public class FileExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Path path = Paths.get("prueba_archivo.txt"); // archivo en la carpeta del proyecto
        try {
            // 1) Crear el archivo si no existe
            if (Files.notExists(path)) {
                Files.createFile(path);
                System.out.println("Archivo creado: " + path.toAbsolutePath());
            }

            // 2) Escribir / agregar una línea al archivo
            String linea = "Pedido: Juan Perez; Producto: Tarjeta; Cantidad: 100; Total: 50.0";
            Files.write(path, Arrays.asList(linea), StandardOpenOption.APPEND);

            // 3) Leer y mostrar todo el contenido del archivo
            System.out.println("Contenido del archivo:");
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String lineaLeida;
                while ((lineaLeida = br.readLine()) != null) {
                    System.out.println(lineaLeida);
                }
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}

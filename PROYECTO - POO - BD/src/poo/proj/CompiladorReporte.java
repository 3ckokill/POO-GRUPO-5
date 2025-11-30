package poo.proj;

import net.sf.jasperreports.engine.JasperCompileManager;

public class CompiladorReporte {
    public static void main(String[] args) {
        try {
            // Rutas relativas dentro de 'src'
            String fuente = "src/poo/proj/resources/ReporteVentas.jrxml";
            String destino = "src/poo/proj/resources/ReporteVentas.jasper";
            
            System.out.println("Compilando reporte...");
            
            JasperCompileManager.compileReportToFile(fuente, destino);
            
            System.out.println("¡ÉXITO! Se creó el archivo: " + destino);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al compilar: " + e.getMessage());
        }
    }
}
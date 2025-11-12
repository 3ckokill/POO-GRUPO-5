package poo.proj;

public class Material {
    private static int contador = 1;
    private int idMaterial;
    private String nombre;
    private double cantidadDisponible;
    private String unidadMedida;
    private double costoUnitario;

    public Material(String nombre, double cantidadDisponible, String unidadMedida) {
        this.idMaterial = contador++;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.unidadMedida = unidadMedida;
        this.costoUnitario = 0.0; // valor por defecto
    }

    public Material(String nombre, double cantidadDisponible, String unidadMedida, double costoUnitario) {
        this(nombre, cantidadDisponible, unidadMedida);
        this.costoUnitario = costoUnitario;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        if (cantidadDisponible >= 0) {
            this.cantidadDisponible = cantidadDisponible;
        } else {
            System.out.println("La cantidad no puede ser negativa.");
        }
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        if (costoUnitario >= 0) {
            this.costoUnitario = costoUnitario;
        } else {
            System.out.println("El costo unitario no puede ser negativo.");
        }
    }

    // Método para actualizar la cantidad (sumar o restar)
    public void actualizarCantidad(double cambio) {
        double nuevaCantidad = cantidadDisponible + cambio;
        if (nuevaCantidad >= 0) {
            cantidadDisponible = nuevaCantidad;
        } else {
            System.out.println("Error: no hay suficiente material disponible para realizar la operación.");
        }
    }

    // Mostrar información del material
    public void mostrarDatos() {
        System.out.println("=== Material ===");
        System.out.println("ID: " + idMaterial);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad disponible: " + cantidadDisponible + " " + unidadMedida);
        System.out.println("Costo unitario: S/." + costoUnitario);
        System.out.println("================");
    }
}

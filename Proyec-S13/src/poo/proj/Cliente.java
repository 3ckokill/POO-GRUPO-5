package poo.proj;

public class Cliente extends Persona {

    private double descuento;
    private String tipoCliente;

    public Cliente(double descuento, String tipoCliente, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.descuento = descuento;
        this.tipoCliente = tipoCliente;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
       if (descuento >= 0 && descuento <= 1) {
            this.descuento = descuento;
        } else {
            System.out.println("El descuento debe estar entre 0 y 1 (ejemplo: 0.15 para 15%).");
        }
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
    @Override
    public void mostrarDatos() {
        
        System.out.println("=== Datos del Cliente ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("Documento: " + tipoDocumento + " " + numeroDocumento);
        System.out.println("Tipo de Cliente: " + tipoCliente);
        System.out.println("Descuento: " + (descuento * 100) + "%");
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("=========================");
    }   
    
}
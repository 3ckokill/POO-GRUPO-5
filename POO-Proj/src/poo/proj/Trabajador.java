package poo.proj;

public abstract class Trabajador extends Persona {

    private int idEmpleado;

    public Trabajador(int idEmpleado, int id, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String numeroDocumento, String correo, String telefono, String direccion) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, correo, telefono, direccion);
        this.idEmpleado = idEmpleado;
    }
    
    public void registrarEmpleado() {
    }

    public void modificarEmpleado() {
    }

    public void eliminarEmpleado() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
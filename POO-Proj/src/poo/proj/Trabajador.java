package poo.proj;

public abstract class Trabajador extends Persona {

    private int idEmpleado;
    private String cargo;
    private String rol;
    private String estado;

    public Trabajador() {
        super();
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
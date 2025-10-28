package proyectoimprenta;

import java.util.ArrayList;

public class ControladorPersonas {

    ArrayList<Persona> lista = new ArrayList<>();

    public void agregarPersona(Persona p) {
        lista.add(p);
    }

    public void mostrarPersonas() {

        for (int i = 0; i < lista.size(); i++) {

            lista.get(i).mostrarDatos();
        }
    }
}

package poo.proj;


public class POOProj {
    public static void main(String[] args) {
        Vista vista = new Vista();
        // Cliente 1 (Empresa)
        Cliente c1 = new Cliente(0.15, "Empresa", 1, "Inversiones", "Gráficas", "Globales", "RUC", "20508090100", "contacto@global.com", "987654321", "Av. Principal 123");
        Vista.control.registrarCliente(c1);

        // Cliente 2 (Habitual)
        Cliente c2 = new Cliente(0.05, "Habitual", 2, "Ana", "Gómez", "Torres", "DNI", "45678901", "ana.gomez@correo.com", "912345678", "Calle Falsa 456");
        Vista.control.registrarCliente(c2);

        // Cliente 3 (Nuevo)
        Cliente c3 = new Cliente(0.0, "Nuevo", 3, "Carlos", "Mendoza", "Ruiz", "DNI", "70809010", "carlos.m@correo.com", "933444555", "Jr. Los Pinos 789");
        Vista.control.registrarCliente(c3);

        // Cliente 4 (Habitual - Carné de Extranjería)
        Cliente c4 = new Cliente(0.05, "Habitual", 4, "Sophie", "Dubois", "", "CE", "123456789", "sophie.d@correo.com", "955666777", "Av. El Sol 101");
        Vista.control.registrarCliente(c4);

        // Cliente 5 (Empresa pequeña)
        Cliente c5 = new Cliente(0.10, "Empresa", 5, "Bodega", "San Juan", "(Razón Social)", "RUC", "10405060701", "pedidos@sanjuan.com", "922111333", "Esquina Las Flores 202");
        Vista.control.registrarCliente(c5);
        vista.iniciarSistema();

    }
}
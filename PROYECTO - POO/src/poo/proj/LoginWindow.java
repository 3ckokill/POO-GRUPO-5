/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.proj;

/**
 *
 * @author Rafael
 */
import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private Controlador controlador;

    public LoginWindow(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Login del Sistema");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtNombre = new JTextField();
        JTextField txtApP = new JTextField();
        JTextField txtApM = new JTextField();
        JTextField txtDoc = new JTextField();

        JButton btnLoginAdmin = new JButton("Ingresar como Administrador");
        JButton btnLoginEmpleado = new JButton("Ingresar como Empleado Ventas");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido Paterno:"));
        panel.add(txtApP);
        panel.add(new JLabel("Apellido Materno:"));
        panel.add(txtApM);
        panel.add(new JLabel("Documento:"));
        panel.add(txtDoc);

        panel.add(btnLoginAdmin);
        panel.add(btnLoginEmpleado);

        add(panel);

        btnLoginAdmin.addActionListener(e -> {
            Administrador admin = controlador.validarAdministrador(
                txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText()
            );

            if (admin != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                new VentanaPrincipal(controlador, admin, null).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos para Administrador");
            }
        });

        btnLoginEmpleado.addActionListener(e -> {
            EmpleadoVentas emp = controlador.validarEmpleado(
                txtNombre.getText(), txtApP.getText(), txtApM.getText(), txtDoc.getText()
            );

            if (emp != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido Empleado de Ventas");
                new VentanaPrincipal(controlador, null, emp).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos para Empleado");
            }
        });
    }
}


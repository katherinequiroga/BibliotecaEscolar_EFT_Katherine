package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {
    public MenuPrincipalFrame(String nombre, String rol) {
        setTitle("Biblioteca Escolar - Menu Principal");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 15, 15));

        JLabel lbl = new JLabel("Bienvenido " + nombre + " | Rol: " + rol, SwingConstants.CENTER);
        JButton btnLibros = new JButton("Gestión Libros");
        JButton btnPrestamos = new JButton("Préstamos / Devoluciones");
        JButton btnReportes = new JButton("Reportes");
        JButton btnSalir = new JButton("Salir");

        add(lbl);
        add(btnLibros);
        add(btnPrestamos);
        add(btnReportes);
        add(btnSalir);

        btnLibros.addActionListener(e -> new LibroFrame().setVisible(true));

        btnPrestamos.addActionListener(e -> {
            try {
                new PrestamoFrame().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error abriendo prestamos: " + ex.getMessage());
            }
        });

        btnReportes.addActionListener(e -> new ReporteFrame().setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));
    }
}
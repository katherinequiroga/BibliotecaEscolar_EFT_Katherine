package vista;

import dao.PrestamoDAO;
import db.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class PrestamoFrame extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtLibroId, txtEmail, txtPrestamoId;

    public PrestamoFrame() {
        setTitle("Préstamos y Devoluciones - EFT Katherine");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"ID Préstamo", "ID Libro", "Título", "Email Usuario", "F.Préstamo", "F.Devolución", "Estado"}, 0);
        tabla = new JTable(modelo);

        txtLibroId = new JTextField(5);
        txtEmail = new JTextField(12);
        txtEmail.setText("admin@duoc.cl");
        txtPrestamoId = new JTextField(5);

        JButton btnPrestar = new JButton("Prestar");
        JButton btnDevolver = new JButton("Devolver");
        JButton btnListar = new JButton("Listar");

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(new JLabel("ID Libro:"));
        top.add(txtLibroId);
        top.add(new JLabel("Email:"));
        top.add(txtEmail);
        top.add(btnPrestar);
        top.add(new JLabel(" | ID Préstamo:"));
        top.add(txtPrestamoId);
        top.add(btnDevolver);
        top.add(btnListar);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnPrestar.addActionListener(e -> {
            try {
                int idLibro = Integer.parseInt(txtLibroId.getText().trim());
                String email = txtEmail.getText().trim();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa un email");
                    return;
                }
                if (new PrestamoDAO().prestar(idLibro, email)) {
                    JOptionPane.showMessageDialog(this, "¡Libro Prestado OK!");
                    listar();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Libro debe ser número");
            }
        });

        btnDevolver.addActionListener(e -> {
            try {
                int idPres = Integer.parseInt(txtPrestamoId.getText().trim());
                if (new PrestamoDAO().devolver(idPres)) {
                    JOptionPane.showMessageDialog(this, "¡Libro Devuelto OK!");
                    listar();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Préstamo debe ser número");
            }
        });

        btnListar.addActionListener(e -> listar());

        listar();
    }

    private void listar() {
        modelo.setRowCount(0);
        String sql = "SELECT p.id, p.libro_id, l.titulo, p.email_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado FROM prestamos p JOIN libros l ON p.libro_id=l.id ORDER BY p.id DESC";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }
}
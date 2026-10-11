package vista;

import db.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class ReporteFrame extends JFrame {

    public ReporteFrame() {
        setTitle("Reportes - EFT Katherine");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Consolas", Font.BOLD, 14));
        txtResumen.setBackground(new Color(240, 240, 240));

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Título del Libro", "Veces Prestado"}, 0);
        JTable tabla = new JTable(modelo);

        try {
            Connection con = DatabaseConnection.getInstance().getConnection();

            ResultSet rs1 = con.createStatement().executeQuery("SELECT COUNT(*) FROM libros");
            rs1.next(); int totalLibros = rs1.getInt(1);

            ResultSet rs2 = con.createStatement().executeQuery("SELECT COUNT(*) FROM prestamos WHERE estado='PRESTADO'");
            rs2.next(); int prestadosActivos = rs2.getInt(1);

            ResultSet rs3 = con.createStatement().executeQuery("SELECT COUNT(*) FROM usuarios");
            rs3.next(); int totalUsuarios = rs3.getInt(1);

            ResultSet rs4 = con.createStatement().executeQuery("SELECT COUNT(*) FROM prestamos WHERE estado='DEVUELTO'");
            rs4.next(); int devueltos = rs4.getInt(1);

            txtResumen.setText(
                    "  REPORTE BIBLIOTECA - KATHERINE\n" +
                            " -----------------------------------------\n" +
                            "  Total libros registrados: " + totalLibros + "\n" +
                            "  Préstamos activos: " + prestadosActivos + "\n" +
                            "  Préstamos devueltos: " + devueltos + "\n" +
                            "  Total usuarios: " + totalUsuarios + "\n" +
                            " -----------------------------------------"
            );

            // Libros más prestados
            modelo.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT l.titulo, COUNT(p.id) as total " +
                            "FROM libros l LEFT JOIN prestamos p ON l.id = p.libro_id " +
                            "GROUP BY l.id, l.titulo ORDER BY total DESC"
            );
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(1), rs.getInt(2)});
            }

        } catch (Exception e) {
            e.printStackTrace();
            txtResumen.setText("Error al generar reporte: " + e.getMessage());
        }

        JLabel lblTitulo = new JLabel("Libros más prestados (Top)", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(lblTitulo, BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(txtResumen, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
    }
}
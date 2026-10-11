package dao;

import db.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrestamoDAO {

    // PRESTAR UN LIBRO - baja stock
    public boolean prestar(int libroId, String email) {
        String sqlBajaStock = "UPDATE libros SET stock = stock - 1 WHERE id = ? AND stock > 0";
        String sqlPresta = "INSERT INTO prestamos(libro_id, email_usuario, fecha_prestamo, estado) VALUES(?, ?, CURDATE(), 'PRESTADO')";

        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(sqlBajaStock);
            ps1.setInt(1, libroId);
            int filas = ps1.executeUpdate();

            if (filas == 0) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Sin stock disponible para el libro ID: " + libroId);
                return false;
            }

            PreparedStatement ps2 = con.prepareStatement(sqlPresta);
            ps2.setInt(1, libroId);
            ps2.setString(2, email);
            ps2.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al prestar: " + e.getMessage());
            return false;
        }
    }

    // DEVOLVER UN LIBRO - sube stock
    public boolean devolver(int prestamoId) {
        String sqlDev = "UPDATE prestamos SET estado='DEVUELTO', fecha_devolucion=CURDATE() WHERE id=? AND estado='PRESTADO'";
        String sqlSube = "UPDATE libros SET stock = stock + 1 WHERE id = (SELECT libro_id FROM prestamos WHERE id=?)";

        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(sqlDev);
            ps1.setInt(1, prestamoId);
            int filas = ps1.executeUpdate();

            if (filas == 0) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Préstamo no existe o ya fue devuelto");
                return false;
            }

            PreparedStatement ps2 = con.prepareStatement(sqlSube);
            ps2.setInt(1, prestamoId);
            ps2.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al devolver: " + e.getMessage());
            return false;
        }
    }
}
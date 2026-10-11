package dao;

import db.DatabaseConnection;
import modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements CrudDAO<Libro> {

    @Override
    public boolean crear(Libro libro) {
        String sql = "INSERT INTO libros(titulo, autor, categoria, isbn, stock) VALUES(?,?,?,?,?)";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setString(4, libro.getIsbn());
            ps.setInt(5, libro.getStock());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                lista.add(new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getString("isbn"),
                        rs.getInt("stock")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo=?, autor=?, categoria=?, isbn=?, stock=? WHERE id=?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setString(4, libro.getIsbn());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id=?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // METODO EXTRA PARA EL SYNCHRONIZED Y PARA PRESTAMOS
    public Libro buscarPorId(int id) {
        String sql = "SELECT * FROM libros WHERE id=?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getString("isbn"),
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizarStock(int id, int nuevoStock) {
        String sql = "UPDATE libros SET stock=? WHERE id=?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
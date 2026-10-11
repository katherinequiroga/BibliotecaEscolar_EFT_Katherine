package dao;

import db.DatabaseConnection;
import modelo.Bibliotecario;
import modelo.EstudianteUsuario;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String em = rs.getString("email");
                String pass = rs.getString("password");
                String rol = rs.getString("rol");

                if (rol.equalsIgnoreCase("Bibliotecario")) {
                    return new Bibliotecario(id, nombre, em, pass, rol);
                } else {
                    return new EstudianteUsuario(id, nombre, em, pass, rol);
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
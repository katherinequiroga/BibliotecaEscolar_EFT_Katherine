package vista;

import dao.UsuarioDAO;
import modelo.Usuario;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField txtEmail = new JTextField(20);
    private JPasswordField txtPass = new JPasswordField(20);
    private JButton btnLogin = new JButton("Entrar");

    public LoginFrame() {
        setTitle("Login Biblioteca - Katherine");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Password:"));
        add(txtPass);
        add(new JLabel(""));
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String pass = new String(txtPass.getPassword());
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = dao.login(email, pass);
            if (u != null) {
                new MenuPrincipalFrame(u.getNombre(), u.getRol()).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
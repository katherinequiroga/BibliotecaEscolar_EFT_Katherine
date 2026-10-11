package modelo;

public class Usuario extends Persona {
    private String password;
    private String rol;

    public Usuario(int id, String nombre, String email, String password, String rol) {
        super(id, nombre, email);
        this.password = password;
        this.rol = rol;
    }
    @Override
    public String getPermisos() { return rol; }
    public String getRol() { return rol; }
    public String getPassword() { return password; }
    public String getNombreUsuario() { return nombre; }
}
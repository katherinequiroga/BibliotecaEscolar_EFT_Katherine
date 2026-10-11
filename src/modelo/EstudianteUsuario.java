package modelo;

public class EstudianteUsuario extends Usuario {
    public EstudianteUsuario(int id, String n, String e, String p, String r) {
        super(id, n, e, p, r);
    }
    @Override
    public String getPermisos() {
        return "SOLO_CONSULTA";
    }
}
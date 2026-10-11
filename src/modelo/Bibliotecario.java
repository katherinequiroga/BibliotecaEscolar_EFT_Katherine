package modelo;

public class Bibliotecario extends Usuario {
    public Bibliotecario(int id, String n, String e, String p, String r) {
        super(id, n, e, p, r);
    }
    @Override
    public String getPermisos() {
        return "ADMIN_TOTAL";
    }
}
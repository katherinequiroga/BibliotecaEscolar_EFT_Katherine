package modelo;
import java.sql.Date;

public class Prestamo {
    private int id;
    private int libroId;
    private String emailUsuario;
    private String estado;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(int id, int libroId, String emailUsuario, Date fechaPrestamo, Date fechaDevolucion, String estado) {
        this.id = id;
        this.libroId = libroId;
        this.emailUsuario = emailUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getLibroId() { return libroId; }
    public String getEmailUsuario() { return emailUsuario; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
}
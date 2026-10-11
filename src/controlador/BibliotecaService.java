package controlador;

import dao.LibroDAO;
import dao.PrestamoDAO;
import modelo.Libro;
import modelo.Prestamo;

public class BibliotecaService {

    private LibroDAO libroDAO = new LibroDAO();
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    // SYNCHRONIZED = evita que 2 personas presten el último libro a la vez (lo pide la EFT)
    public synchronized boolean prestarLibro(int idLibro, String emailEstudiante) {
        Libro libro = libroDAO.buscarPorId(idLibro);
        if (libro == null || libro.getStock() <= 0) {
            return false;
        }
        // Ahora usa el DAO nuevo que ya controla stock
        return prestamoDAO.prestar(idLibro, emailEstudiante);
    }

    public synchronized boolean devolverLibro(int idPrestamo) {
        return prestamoDAO.devolver(idPrestamo);
    }
}
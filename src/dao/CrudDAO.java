package dao;
import java.util.List;

public interface CrudDAO<T> {
    boolean crear(T obj);
    List<T> listar();
    T buscarPorId(int id);
    boolean actualizar(T obj);
    boolean eliminar(int id);
}
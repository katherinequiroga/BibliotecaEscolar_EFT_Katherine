package modelo;

// 2. INTERFACE para POO - cumple rúbrica punto 1
public interface Prestamable {
    boolean estaDisponible();
    void prestar();
    void devolver();
}
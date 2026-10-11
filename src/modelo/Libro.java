package modelo;

// Implementa interface Prestamable = cumple POO + Polimorfismo
public class Libro implements Prestamable {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private String isbn;
    private int stock;

    public Libro(int id, String titulo, String autor, String categoria, String isbn, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // === METODOS DE LA INTERFACE Prestamable ===
    @Override
    public boolean estaDisponible() {
        return this.stock > 0;
    }

    @Override
    public void prestar() {
        if (estaDisponible()) {
            this.stock--;
        }
    }

    @Override
    public void devolver() {
        this.stock++;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (Stock: " + stock + ")";
    }
}
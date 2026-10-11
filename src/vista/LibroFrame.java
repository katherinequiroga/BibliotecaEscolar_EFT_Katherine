package vista;

import dao.LibroDAO;
import modelo.Libro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibroFrame extends JFrame {
    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtAutor = new JTextField(20);
    private JTextField txtCategoria = new JTextField(20);
    private JTextField txtIsbn = new JTextField(20);
    private JTextField txtStock = new JTextField(20);
    private JTable tabla;
    private DefaultTableModel modelo;
    private LibroDAO dao = new LibroDAO();
    private int idSeleccionado = -1;

    public LibroFrame() {
        setTitle("Gestión Libros - Katherine");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.add(new JLabel("Titulo:")); panelForm.add(txtTitulo);
        panelForm.add(new JLabel("Autor:")); panelForm.add(txtAutor);
        panelForm.add(new JLabel("Categoria:")); panelForm.add(txtCategoria);
        panelForm.add(new JLabel("ISBN:")); panelForm.add(txtIsbn);
        panelForm.add(new JLabel("Stock:")); panelForm.add(txtStock);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelForm.add(panelBotones);
        add(panelForm, BorderLayout.NORTH);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Titulo", "Autor", "Categoria", "ISBN", "Stock"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarTabla();

        // Eventos
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                txtTitulo.setText(modelo.getValueAt(fila, 1).toString());
                txtAutor.setText(modelo.getValueAt(fila, 2).toString());
                txtCategoria.setText(modelo.getValueAt(fila, 3).toString());
                txtIsbn.setText(modelo.getValueAt(fila, 4).toString());
                txtStock.setText(modelo.getValueAt(fila, 5).toString());
            }
        });

        btnAgregar.addActionListener(e -> {
            if (!validar()) return;
            try {
                Libro libro = new Libro(0, txtTitulo.getText(), txtAutor.getText(), txtCategoria.getText(), txtIsbn.getText(), Integer.parseInt(txtStock.getText().trim()));
                if (dao.crear(libro)) {
                    JOptionPane.showMessageDialog(this, "Libro agregado!");
                    cargarTabla(); limpiar();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stock debe ser un número, ejemplo: 5");
            }
        });

        btnActualizar.addActionListener(e -> {
            if (idSeleccionado == -1) { JOptionPane.showMessageDialog(this, "Seleccione un libro"); return; }
            if (!validar()) return;
            try {
                Libro libro = new Libro(idSeleccionado, txtTitulo.getText(), txtAutor.getText(), txtCategoria.getText(), txtIsbn.getText(), Integer.parseInt(txtStock.getText().trim()));
                if (dao.actualizar(libro)) {
                    JOptionPane.showMessageDialog(this, "Actualizado!");
                    cargarTabla(); limpiar();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stock debe ser un número");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado == -1) { JOptionPane.showMessageDialog(this, "Seleccione un libro"); return; }
            if (dao.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Eliminado!");
                cargarTabla(); limpiar();
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());
    }

    private boolean validar() {
        if (txtTitulo.getText().trim().isEmpty() || txtAutor.getText().trim().isEmpty() || txtCategoria.getText().trim().isEmpty() || txtIsbn.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios, no deje Stock vacío");
            return false;
        }
        return true;
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Libro> lista = dao.listar();
        for (Libro l : lista) {
            modelo.addRow(new Object[]{l.getId(), l.getTitulo(), l.getAutor(), l.getCategoria(), l.getIsbn(), l.getStock()});
        }
    }

    private void limpiar() {
        idSeleccionado = -1;
        txtTitulo.setText(""); txtAutor.setText(""); txtCategoria.setText(""); txtIsbn.setText(""); txtStock.setText("");
        tabla.clearSelection();
    }
}
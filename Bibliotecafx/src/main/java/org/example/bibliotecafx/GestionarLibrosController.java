package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.bibliotecafx.DAO.LibrosImpl;
import org.example.bibliotecafx.DAO.AutoresImpl;
import org.example.bibliotecafx.entities.Libros;
import org.example.bibliotecafx.entities.Autores;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.List;

public class GestionarLibrosController {

    @FXML private TextField tituloField;
    @FXML private TextField isbnField;
    @FXML private ComboBox<Autores> autorComboBox;
    @FXML private TextField editorialField;
    @FXML private TextField anioField;
    @FXML private TextArea descripcionArea;
    @FXML
    private LibrosImpl librosDAO = new LibrosImpl();
    @FXML
    private AutoresImpl autoresDAO = new AutoresImpl();


    // Método para inicializar el ComboBox con autores

    @FXML
    public void initialize() {
        //List<Autores> autores = autoresDAO.listarAutores();
        //autorComboBox.getItems().addAll(autores);
    }

    // Método para agregar un libro
    @FXML
    public void agregarLibro() {
        String isbn = isbnField.getText();
        String titulo = tituloField.getText();
        Autores autor = autorComboBox.getValue();
        String editorial = editorialField.getText();
        int anio = Integer.parseInt(anioField.getText());

        // Aquí no se usa descripcion porque no forma parte de la entidad 'Libro'
        Libros libro = new Libros(isbn, titulo, autor, editorial, anio);
        librosDAO.agregarLibro(libro);
        mostrarMensaje("Agregar Libro", "Se ha agregado un nuevo libro.");
    }

    // Método para modificar un libro
    @FXML
    public void modificarLibro() {
        String isbn = isbnField.getText();
        Libros libro = librosDAO.buscarLibro(isbn);
        if (libro != null) {
            libro.setTitulo(tituloField.getText());
            libro.setAutor(autorComboBox.getValue());
            libro.setEditorial(editorialField.getText());
            libro.setAnioPublicacion(Integer.parseInt(anioField.getText()));
            librosDAO.modificarLibro(libro);
            mostrarMensaje("Modificar Libro", "Se ha modificado el libro.");
        } else {
            mostrarMensaje("Error", "Libro no encontrado.");
        }
    }

    // Método para eliminar un libro
    @FXML
    public void eliminarLibro() {
        String isbn = isbnField.getText();
        librosDAO.eliminarLibro(isbn);
        mostrarMensaje("Eliminar Libro", "Se ha eliminado el libro.");
    }

    // Método para buscar un libro
    @FXML
    public void buscarLibro() {
        String isbn = isbnField.getText();
        Libros libro = librosDAO.buscarLibro(isbn);
        if (libro != null) {
            tituloField.setText(libro.getTitulo());
            autorComboBox.setValue(libro.getAutor());
            editorialField.setText(libro.getEditorial());
            anioField.setText(String.valueOf(libro.getAnioPublicacion()));
            descripcionArea.setText(libro.getTitulo() + " por " + libro.getAutor().getNombre() + "\n" +
                    "Editorial: " + libro.getEditorial() + "\n" +
                    "Año de publicación: " + libro.getAnioPublicacion());
        } else {
            mostrarMensaje("No encontrado", "Libro no encontrado.");
        }
    }

    // Método para listar todos los libros disponibles
    @FXML
    public void listarLibrosDisponibles() {
        List<Libros> libros = librosDAO.listarLibrosDisponibles();
        StringBuilder listaLibros = new StringBuilder();

        // Si no hay libros disponibles
        if (libros.isEmpty()) {
            listaLibros.append("No hay libros disponibles.");
        } else {
            // Agregar los detalles de los libros al StringBuilder
            for (Libros libro : libros) {
                listaLibros.append("Título: ").append(libro.getTitulo()).append("\n")
                        .append("ISBN: ").append(libro.getIsbn()).append("\n")
                        .append("Autor: ").append(libro.getAutor().getNombre()).append("\n")
                        .append("Editorial: ").append(libro.getEditorial()).append("\n")
                        .append("Año: ").append(libro.getAnioPublicacion()).append("\n\n");
            }
        }

        // Mostrar la lista de libros en el TextArea
        descripcionArea.setText(listaLibros.toString());
    }


    // Método para mostrar un mensaje de alerta
    @FXML
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para cerrar la ventana actual
    @FXML
    public void volver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

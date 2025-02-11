package org.example.bibliotecafx;

import javafx.scene.text.Text;
import org.example.bibliotecafx.DAO.AutoresImpl;
import org.example.bibliotecafx.DAO.LibrosImpl;
import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.entities.Libros;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class GestionarLibrosController {

    @FXML
    private TextField txtTitulo, txtISBN, txtAutor, txtEditorial, txtAnioPublicacion, txtTituloMod, txtISBNMod, txtAutorMod, txtBuscar, txtEditorialMod, txtAnioPublicacionMod;
    @FXML
    private Button btnGuardar, btnGuardarMod, btnBuscar, btnEliminar;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Libros> tablaLibros;
    @FXML
    private TableColumn<Libros, String> colTitulo, colISBN, colAutor, colEditorial, colAnioPublicacion;
    @FXML
    private ListView<Libros> listLibrosEliminar, listLibrosModificar, listResultados;

    private Libros libroSeleccionado;

    @FXML
    private void volver() throws Exception {
        HelloApplication.switchScene("/org/example/bibliotecafx/hello-view.fxml");
    }

    // Guardar un nuevo libro
    @FXML
    private void guardarLibro() {
        String nombreAutor = txtAutor.getText();
        Autores autor = new AutoresImpl().buscarPorNombre(nombreAutor);  // Asegúrate de que este método exista

        if (autor != null) {
            Libros libro = new Libros(
                    txtISBN.getText(),
                    txtTitulo.getText(),
                    autor,  // Aquí usamos el objeto Autor
                    txtEditorial.getText(),
                    Integer.parseInt(txtAnioPublicacion.getText())
            );
            // Crear una instancia de LibrosImpl
            LibrosImpl librosImpl = new LibrosImpl();

            if (librosImpl.guardar(libro)) {  // Llamada no estática
                lblMensaje.setText("Libro guardado correctamente.");
                lblMensaje.setStyle("-fx-text-fill: green;");
            } else {
                lblMensaje.setText("Error al guardar el libro.");
                lblMensaje.setStyle("-fx-text-fill: red;");
            }
            actualizarTabla();
            actualizarListaLibros();
        } else {
            lblMensaje.setText("Autor no encontrado.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }



    // Modificar un libro existente
    @FXML
    private void modificarLibro() {
        String nombreAutor = txtAutorMod.getText();
        Autores autor = new AutoresImpl().buscarPorNombre(nombreAutor);  // Asegúrate de que este método exista

        if (autor != null) {
            libroSeleccionado.setTitulo(txtTituloMod.getText());
            libroSeleccionado.setIsbn(txtISBNMod.getText());
            libroSeleccionado.setAutor(autor);  // Aquí actualizas la relación con el autor
            libroSeleccionado.setEditorial(txtEditorialMod.getText());
            libroSeleccionado.setAnioPublicacion(Integer.parseInt(txtAnioPublicacionMod.getText()));

            // Crear una instancia de LibrosImpl
            LibrosImpl librosImpl = new LibrosImpl();

            if (librosImpl.modificar(libroSeleccionado)) {  // Llamada no estática
                lblMensaje.setText("Libro modificado correctamente.");
                lblMensaje.setStyle("-fx-text-fill: green;");
            } else {
                lblMensaje.setText("Error al modificar el libro.");
                lblMensaje.setStyle("-fx-text-fill: red;");
            }
            actualizarTabla();
            actualizarListaLibros();
        } else {
            lblMensaje.setText("Autor no encontrado.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }



    // Eliminar un libro
    @FXML
    private void eliminarLibro() {
        libroSeleccionado = listLibrosEliminar.getSelectionModel().getSelectedItem();
        if (new LibrosImpl().eliminar(libroSeleccionado)) {
            lblMensaje.setText("Libro eliminado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } else {
            lblMensaje.setText("Error al eliminar el libro.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
        actualizarTabla();
        actualizarListaLibros();
    }

    // Buscar libros
    @FXML
    private void buscarLibro() {
        String query = txtBuscar.getText();
        if (!query.isEmpty()) {
            List<Libros> resultados = null;

            // Realizamos la búsqueda directamente, sin validar si es ISBN
            resultados = new LibrosImpl().buscarPorIsbn(query);  // Buscar directamente por ISBN
            if (resultados == null || resultados.isEmpty()) {
                // Si no se encuentra por ISBN, buscamos por título
                resultados = new LibrosImpl().buscarPorTitulo(query);
                if (resultados == null || resultados.isEmpty()) {
                    // Si no se encuentra por título, buscamos por autor
                    resultados = new LibrosImpl().buscarPorAutor(query);
                }
            }

            if (resultados != null && !resultados.isEmpty()) {
                ObservableList<Libros> observableResultados = FXCollections.observableArrayList(resultados);
                listResultados.setItems(observableResultados);
            } else {
                // Manejar el caso cuando no se encuentren resultados
                lblMensaje.setText("No se encontraron resultados.");
                lblMensaje.setStyle("-fx-text-fill: red;");
            }
        } else {
            // Si el campo de búsqueda está vacío, mostramos todos los libros disponibles
            List<Libros> librosDisponibles = new LibrosImpl().listarLibrosDisponibles();
            ObservableList<Libros> observableLibros = FXCollections.observableArrayList(librosDisponibles);
            listResultados.setItems(observableLibros);
        }
    }



    // Método auxiliar para verificar si el query es un ISBN
    private boolean isValidIsbn(String isbn) {
        return isbn.matches("\\d{13}|\\d{10}"); // Acepta ISBN-10 y ISBN-13
    }




    public void actualizarListaLibros() {
        LibrosImpl librosDao = new LibrosImpl();
        List<Libros> autores = librosDao.listarTodosLibros();

        listLibrosEliminar.getItems().clear();
        listLibrosModificar.getItems().clear();
        tablaLibros.getItems().clear();

        listLibrosEliminar.getItems().addAll(autores);
        listLibrosModificar.getItems().addAll(autores);
        tablaLibros.getItems().addAll(autores);
    }


    // Actualizar la tabla de libros
    private void actualizarTabla() {
        List<Libros> libros = new LibrosImpl().listarTodosLibros();
        ObservableList<Libros> listaLibros = FXCollections.observableArrayList(libros);
        tablaLibros.setItems(listaLibros);
    }

    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
        colISBN.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));
        colAutor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutor().getNombre()));  // Accedemos al nombre del autor

        // Añadimos las columnas para Editorial y Año de Publicación
        colEditorial.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEditorial()));
        colAnioPublicacion.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAnioPublicacion())));

        listLibrosModificar.setOnMouseClicked(event -> seleccionarLibroModificar());
        actualizarTabla();
        actualizarListaLibros();
    }

    @FXML
    public void seleccionarLibroModificar() {
        libroSeleccionado = listLibrosModificar.getSelectionModel().getSelectedItem();
        if (libroSeleccionado != null) {
            txtTituloMod.setText(libroSeleccionado.getTitulo());
            txtISBNMod.setText(libroSeleccionado.getIsbn());
            txtAutorMod.setText(libroSeleccionado.getAutor().getNombre());
            txtEditorialMod.setText(libroSeleccionado.getEditorial());
            // Parseo: convertir el año de publicación (int) a String y setearlo en el TextField
            txtAnioPublicacionMod.setText(String.valueOf(libroSeleccionado.getAnioPublicacion()));


        }
    }

}
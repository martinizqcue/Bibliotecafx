package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GestionarLibrosController {

    // Método para agregar un libro
    @FXML
    public void agregarLibro() {
        // Agregar la lógica para agregar un libro
        mostrarMensaje("Agregar Libro", "Se ha agregado un nuevo libro.");
    }

    // Método para modificar un libro
    @FXML
    public void modificarLibro() {
        // Agregar la lógica para modificar un libro
        mostrarMensaje("Modificar Libro", "Se ha modificado el libro.");
    }

    // Método para eliminar un libro
    @FXML
    public void eliminarLibro() {
        // Agregar la lógica para eliminar un libro
        mostrarMensaje("Eliminar Libro", "Se ha eliminado un libro.");
    }

    // Método para volver (cerrar la ventana actual)
    @FXML
    public void volver(ActionEvent event) {
        // Cerrar la ventana de gestión de libros
        cerrarVentana(event);
    }

    // Método para mostrar un mensaje de alerta
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);  // No muestra encabezado
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para cerrar la ventana actual
    private void cerrarVentana(ActionEvent event) {
        // Obtener el Stage actual (ventana) a partir del evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

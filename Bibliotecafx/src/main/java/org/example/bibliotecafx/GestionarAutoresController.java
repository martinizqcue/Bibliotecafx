package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GestionarAutoresController {

    @FXML
    public void agregarAutor() {
        mostrarMensaje("Agregar Autor", "Se agregó un nuevo autor.");
    }

    @FXML
    public void modificarAutor() {
        mostrarMensaje("Modificar Autor", "Se modificó un autor.");
    }

    @FXML
    public void eliminarAutor() {
        mostrarMensaje("Eliminar Autor", "Se eliminó un autor.");
    }

    @FXML
    public void volver(ActionEvent event) {
        // Cerrar la ventana de gestión de autores
        cerrarVentana(event);
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana(ActionEvent event) {
        // Obtener el Stage actual (ventana) a partir del evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

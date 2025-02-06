package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GestionarPrestamosController {

    @FXML
    public void agregarPrestamo() {
        mostrarMensaje("Agregar Préstamo", "Se agregó un nuevo préstamo.");
    }

    @FXML
    public void modificarPrestamo() {
        mostrarMensaje("Modificar Préstamo", "Se modificó un préstamo.");
    }

    @FXML
    public void eliminarPrestamo() {
        mostrarMensaje("Eliminar Préstamo", "Se eliminó un préstamo.");
    }

    @FXML
    public void volver(ActionEvent event) {
        // Cerrar la ventana de gestión de préstamos
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

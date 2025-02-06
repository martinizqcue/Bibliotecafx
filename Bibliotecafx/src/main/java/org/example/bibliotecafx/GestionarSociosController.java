package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GestionarSociosController {

    @FXML
    public void agregarSocio() {
        mostrarMensaje("Agregar Socio", "Se agregó un nuevo socio.");
    }

    @FXML
    public void modificarSocio() {
        mostrarMensaje("Modificar Socio", "Se modificó un socio.");
    }

    @FXML
    public void eliminarSocio() {
        mostrarMensaje("Eliminar Socio", "Se eliminó un socio.");
    }

    @FXML
    public void volver(ActionEvent event) {
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

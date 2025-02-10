package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {





    @FXML
    public void onGestionarLibros() {

        try {
            // Cargar la vista de gestionar libros
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/GestionarLibros.fxml"));
            Stage stage = new Stage();  // Nueva ventana
            stage.setTitle("Gestionar Libros");
            stage.setScene(new Scene(loader.load(), 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGestionarAutores() throws Exception {
        HelloApplication.switchScene("/org/example/bibliotecafx/GestionarAutores.fxml");

    }

    @FXML
    public void onGestionarSocios() {


        try {
            // Cargar la vista de gestionar socios
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/GestionarSocios.fxml"));
            Stage stage = new Stage();  // Nueva ventana
            stage.setTitle("Gestionar Socios");
            stage.setScene(new Scene(loader.load(), 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGestionarPrestamos() {


        try {
            // Cargar la vista de gestionar préstamos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/GestionarPrestamos.fxml"));
            Stage stage = new Stage();  // Nueva ventana
            stage.setTitle("Gestionar Préstamos");
            stage.setScene(new Scene(loader.load(), 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

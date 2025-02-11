package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {





    @FXML
    public void onGestionarLibros() throws Exception {

        HelloApplication.switchScene("/org/example/bibliotecafx/GestionarLibros.fxml");
    }

    @FXML
    public void onGestionarAutores() throws Exception {
        HelloApplication.switchScene("/org/example/bibliotecafx/GestionarAutores.fxml");

    }

    @FXML
    public void onGestionarSocios() throws Exception {

        HelloApplication.switchScene("/org/example/bibliotecafx/GestionarSocios.fxml");
    }

    @FXML
    public void onGestionarPrestamos() throws Exception {

        HelloApplication.switchScene("/org/example/bibliotecafx/GestionarPrestamos.fxml");
    }
}

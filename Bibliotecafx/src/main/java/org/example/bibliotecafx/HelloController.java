package org.example.bibliotecafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;  // Para mostrar el mensaje de bienvenida

    @FXML
    private Label statusLabel;  // Para mostrar el mensaje de estado

    @FXML
    public void onGestionarLibros() {
        // Lógica para gestionar libros
        statusLabel.setText("Acción: Gestionar Libros");
    }

    @FXML
    public void onGestionarAutores() {
        // Lógica para gestionar autores
        statusLabel.setText("Acción: Gestionar Autores");
    }

    @FXML
    public void onGestionarSocios() {
        // Lógica para gestionar socios
        statusLabel.setText("Acción: Gestionar Socios");
    }

    @FXML
    public void onGestionarPrestamos() {
        // Lógica para gestionar préstamos
        statusLabel.setText("Acción: Gestionar Préstamos");
    }
}

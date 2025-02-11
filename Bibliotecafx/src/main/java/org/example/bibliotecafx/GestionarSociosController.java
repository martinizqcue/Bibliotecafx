package org.example.bibliotecafx;

import org.example.bibliotecafx.DAO.LibrosImpl;
import org.example.bibliotecafx.DAO.SociosImpl;
import org.example.bibliotecafx.entities.Libros;
import org.example.bibliotecafx.entities.Socios;

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

public class GestionarSociosController {

    @FXML
    private TextField txtNombre, txtDireccion, txtTelefono, txtNombreMod, txtDireccionMod, txtTelefonoMod, txtBuscar;
    @FXML
    private Button btnGuardar, btnGuardarMod, btnBuscar, btnEliminar;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Socios> tablaSocios;
    @FXML
    private TableColumn<Socios, String> colNombre, colDireccion, colTelefono;
    @FXML
    private ListView<Socios> listSociosEliminar, listSociosModificar, listResultados;

    private Socios socioSeleccionado;

    @FXML
    private void volver() throws Exception {
        HelloApplication.switchScene("/org/example/bibliotecafx/hello-view.fxml");
    }

    // Guardar un nuevo socio
    @FXML
    private void guardarSocio() {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String telefonoTexto = txtTelefono.getText();

        // Verificar que el campo de teléfono no esté vacío y convertirlo a Integer
        Integer telefono = null;
        try {
            telefono = Integer.parseInt(telefonoTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El teléfono debe ser un número válido.", Alert.AlertType.ERROR);
            return;
        }

        Socios socio = new Socios();
        socio.setNombre(nombre);
        socio.setDireccion(direccion);
        socio.setTelefono(telefono);

        SociosImpl socioDao = new SociosImpl();
        socioDao.agregarSocio(socio);

        txtNombre.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        lblMensaje.setText("Socio añadido con éxito");
        lblMensaje.setStyle("-fx-text-fill: green;");
        ocultarMensajeDespuesDeTiempo();
        actualizarListaSocios();
    }

    // Modificar un socio existente
    @FXML
    private void modificarSocio() {
        if (socioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un socio", AlertType.ERROR);
            return;
        }

        Alert confirmacion = new Alert(AlertType.CONFIRMATION, "¿Seguro que quieres modificar este socio?", ButtonType.YES, ButtonType.NO);
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Actualizar los campos del socio seleccionado
                socioSeleccionado.setNombre(txtNombreMod.getText());
                socioSeleccionado.setDireccion(txtDireccionMod.getText());

                // Validar y actualizar el teléfono
                String telefonoTexto = txtTelefonoMod.getText();
                try {
                    Integer telefono = Integer.parseInt(telefonoTexto);
                    socioSeleccionado.setTelefono(telefono);
                } catch (NumberFormatException e) {
                    mostrarAlerta("Error", "El teléfono debe ser un número válido.", Alert.AlertType.ERROR);
                    return;
                }

                // Guardar los cambios en la base de datos
                SociosImpl socioDao = new SociosImpl();
                socioDao.actualizarSocio(socioSeleccionado);

                lblMensaje.setText("Socio modificado con éxito");
                lblMensaje.setStyle("-fx-text-fill: blue;");
                actualizarListaSocios();
            }
        });
    }

    // Eliminar un socio
    @FXML
    private void eliminarSocio() {
        socioSeleccionado = listSociosEliminar.getSelectionModel().getSelectedItem();
        if (socioSeleccionado != null) {
            SociosImpl socioDao = new SociosImpl();
            socioDao.eliminarSocio(socioSeleccionado.getId());
            lblMensaje.setText("Socio eliminado con éxito");
            lblMensaje.setStyle("-fx-text-fill: green;");
            actualizarListaSocios();
        } else {
            mostrarAlerta("Error", "Debe seleccionar un socio", Alert.AlertType.ERROR);
        }
    }

    // Buscar socios
    @FXML
    private void buscarSocio() {
        String query = txtBuscar.getText().trim();
        List<Socios> resultados = null;

        if (!query.isEmpty()) {
            // Comprobamos si el texto ingresado corresponde a un número de teléfono
            if (esTelefono(query)) {
                // Si el texto es un teléfono, buscamos por teléfono
                resultados = new SociosImpl().findByTelefono(Integer.parseInt(query));  // Convertimos el texto a un número entero
            } else {
                // Si no es un teléfono, buscamos por nombre
                resultados = new SociosImpl().findByNombre(query);
            }

            if (resultados != null && !resultados.isEmpty()) {
                ObservableList<Socios> observableResultados = FXCollections.observableArrayList(resultados);
                listResultados.setItems(observableResultados);
            } else {
                lblMensaje.setText("No se encontraron resultados.");
                lblMensaje.setStyle("-fx-text-fill: red;");
            }
        } else {
            // Si el campo de búsqueda está vacío, mostramos todos los socios disponibles
            List<Socios> sociosDisponibles = new SociosImpl().obtenerTodosLosSocios();
            ObservableList<Socios> observableSocios = FXCollections.observableArrayList(sociosDisponibles);
            listResultados.setItems(observableSocios);
        }
    }


    private boolean esTelefono(String texto) {
        try {
            // Intentamos convertir el texto a un número entero
            Integer.parseInt(texto);
            return true;  // Si la conversión tiene éxito, asumimos que es un teléfono
        } catch (NumberFormatException e) {
            return false;  // Si no se puede convertir, no es un teléfono
        }
    }






    // Actualizar las listas de socios
    public void actualizarListaSocios() {
        SociosImpl socioDao = new SociosImpl();
        List<Socios> socios = socioDao.findAll();

        listSociosEliminar.getItems().clear();
        listSociosModificar.getItems().clear();
        tablaSocios.getItems().clear();

        listSociosEliminar.getItems().addAll(socios);
        listSociosModificar.getItems().addAll(socios);
        tablaSocios.getItems().addAll(socios);
    }

    // Mostrar alerta
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Ocultar mensaje después de cierto tiempo
    private void ocultarMensajeDespuesDeTiempo() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), evt -> lblMensaje.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }

    @FXML
    public void seleccionarSocioModificar() {
        socioSeleccionado = listSociosModificar.getSelectionModel().getSelectedItem();
        if (socioSeleccionado != null) {
            txtNombreMod.setText(socioSeleccionado.getNombre());
            txtDireccionMod.setText(socioSeleccionado.getDireccion());
            // Parseo: convertir el año de publicación (int) a String y setearlo en el TextField
            txtTelefonoMod.setText(String.valueOf(socioSeleccionado.getTelefono()));


        }
    }

    // Inicializar la tabla
    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colDireccion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDireccion()));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));

        listSociosModificar.setOnMouseClicked(event -> seleccionarSocioModificar());

        actualizarListaSocios();
    }
}

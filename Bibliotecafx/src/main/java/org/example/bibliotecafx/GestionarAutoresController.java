package org.example.bibliotecafx;

import org.example.bibliotecafx.DAO.AutoresImpl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;
import org.example.bibliotecafx.entities.Autores;

import java.util.List;

public class GestionarAutoresController {

    @FXML
    private TextField txtNombre, txtNacionalidad, txtNombreMod, txtNacionalidadMod, txtBuscar;
    @FXML
    private Button btnGuardar, btnGuardarMod, btnBuscar, btnEliminar;
    @FXML
    private Label lblMensaje;
    @FXML

    private TableView<Autores> tablaAutores;
    @FXML
    private TableColumn<Autores, String> colNombre, colNacionalidad;
    @FXML
    private ListView<Autores> listAutoresEliminar, listAutoresModificar, listResultados;

    private Autores autorSeleccionado;

    @FXML
    private void volver() throws Exception {
        HelloApplication.switchScene("/org/example/bibliotecafx/hello-view.fxml");
    }

    @FXML
    public void guardarAutor() {
        String nombre = txtNombre.getText();
        String nacionalidad = txtNacionalidad.getText();

        Autores autor = new Autores();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        AutoresImpl autorDao = new AutoresImpl();
        autorDao.save(autor);

        txtNombre.clear();
        txtNacionalidad.clear();
        lblMensaje.setText("Autor añadido con éxito");
        lblMensaje.setStyle("-fx-text-fill: green;");
        ocultarMensajeDespuesDeTiempo();
        actualizarListaAutores();
    }

    @FXML
    public void seleccionarAutorModificar() {
        autorSeleccionado = listAutoresModificar.getSelectionModel().getSelectedItem();
        if (autorSeleccionado != null) {
            txtNombreMod.setText(autorSeleccionado.getNombre());
            txtNacionalidadMod.setText(autorSeleccionado.getNacionalidad());
        }
    }

    @FXML
    public void modificarAutor() {
        System.out.println("Botón presionado: modificarAutor() ejecutado");

        if (autorSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un autor", AlertType.ERROR);
            System.out.println("Error: No hay autor seleccionado");
            return;
        }

        Alert confirmacion = new Alert(AlertType.CONFIRMATION, "¿Seguro que quieres modificar este autor?", ButtonType.YES, ButtonType.NO);
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                System.out.println("Confirmación recibida, modificando autor...");

                // Actualizamos los datos del autor seleccionado
                autorSeleccionado.setNombre(txtNombreMod.getText());
                autorSeleccionado.setNacionalidad(txtNacionalidadMod.getText());

                System.out.println("Nuevos valores - Nombre: " + autorSeleccionado.getNombre() + ", Nacionalidad: " + autorSeleccionado.getNacionalidad());

                // Llamamos al método update de IAutorImpl
                AutoresImpl autorDao = new AutoresImpl();
                Autores actualizado = autorDao.update(autorSeleccionado);

                if (actualizado != null) {
                    lblMensaje.setText("Autor modificado con éxito");
                    lblMensaje.setStyle("-fx-text-fill: blue;");
                    System.out.println("Autor actualizado correctamente");
                    actualizarListaAutores(); // Refresca la lista de autores
                } else {
                    mostrarAlerta("Error", "No se pudo modificar el autor.", AlertType.ERROR);
                    System.out.println("Error: La actualización falló");
                }
            }
        });
    }

    @FXML
    public void buscarAutor() {
        String busqueda = txtBuscar.getText();
        AutoresImpl autorDao = new AutoresImpl();
        List<Autores> autoresEncontrados = autorDao.findByNombre(busqueda);
        ObservableList<Autores> autores = FXCollections.observableArrayList(autoresEncontrados);
        listResultados.setItems(autores);
    }


    public void actualizarListaAutores() {
        AutoresImpl autorDao = new AutoresImpl();
        List<Autores> autores = autorDao.findAll();

        listAutoresEliminar.getItems().clear();
        listAutoresModificar.getItems().clear();
        tablaAutores.getItems().clear();

        listAutoresEliminar.getItems().addAll(autores);
        listAutoresModificar.getItems().addAll(autores);
        tablaAutores.getItems().addAll(autores);
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void ocultarMensajeDespuesDeTiempo() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                event -> lblMensaje.setText("")
        ));
        timeline.play();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colNacionalidad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNacionalidad()));
        listAutoresModificar.setOnMouseClicked(event -> seleccionarAutorModificar());
        actualizarListaAutores();
    }


    @FXML
    public void eliminarAutor() {
        Autores autorSeleccionado = listAutoresEliminar.getSelectionModel().getSelectedItem();

        if (autorSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un autor para eliminar", Alert.AlertType.ERROR);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que quieres eliminar este autor?", ButtonType.YES, ButtonType.NO);
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                AutoresImpl autorDao = new AutoresImpl();
                boolean eliminado = autorDao.delete(autorSeleccionado);

                if (eliminado) {
                    lblMensaje.setText("Autor eliminado con éxito");
                    lblMensaje.setStyle("-fx-text-fill: red;");
                    actualizarListaAutores(); // Refresca la lista
                } else {
                    mostrarAlerta("Error", "No se pudo eliminar el autor.", Alert.AlertType.ERROR);
                }
            }
        });
    }



}

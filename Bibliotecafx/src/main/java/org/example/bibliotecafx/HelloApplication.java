package org.example.bibliotecafx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage Stage) throws Exception {
        primaryStage = Stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void switchScene(String fxml) throws Exception {
        FXMLLoader fXMLLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fXMLLoader.load());
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();  // Lanza la aplicación
    }
}

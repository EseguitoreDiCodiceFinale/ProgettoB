package org.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
/**
 * @author Alessio Zangarini
 * Classe per avviare l'interfaccia
 *
 * Estende Application per fornire i servizi di javafx
 */
public class InterfacciaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/client/InterfacciaIniziale.fxml")));
            String css = this.getClass().getResource("/client/cssinterface.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMinWidth(900);
            stage.setMinHeight(580);
            stage.setMaximized(false);
            stage.setMaxHeight(580);
            stage.setMaxWidth(900);
            stage.show();
        //}catch (Exception e){}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
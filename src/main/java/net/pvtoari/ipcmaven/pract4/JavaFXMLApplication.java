package net.pvtoari.ipcmaven.pract4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXMLApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract4/FXMLDocument.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("no tengo enemigos");
        stage.setResizable(true);
        stage.setHeight(450);
        stage.setWidth(800);
        stage.show();

        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

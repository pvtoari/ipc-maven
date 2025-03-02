package net.pvtoari.ipcmaven.pract2.parte2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract2/parte2/FXMLDocument.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("eventos guays parte 2");
        stage.setResizable(false);
        stage.show();
        
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package net.pvtoari.ipcmaven.pract5.exampleIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListViewExampleApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract5/exampleIPC/ListViewExample.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("ejemplico");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

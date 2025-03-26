package net.pvtoari.ipcmaven.pract5.modalsIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalsApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract5/modalsIPC/ListView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        Stage modal = new Stage();
        modal.setScene(scene);
        modal.setResizable(false);
        modal.setWidth(500);
        modal.setHeight(300);
        modal.setTitle("datos de personas");
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

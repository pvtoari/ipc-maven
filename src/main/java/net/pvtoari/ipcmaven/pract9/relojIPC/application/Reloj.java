package net.pvtoari.ipcmaven.pract9.relojIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reloj extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract9/relojIPC/view/FXMLReloj.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setWidth(320);
        stage.setHeight(240);
        stage.setScene(scene);
        stage.setTitle("tic tac tic tac suena el reloj");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

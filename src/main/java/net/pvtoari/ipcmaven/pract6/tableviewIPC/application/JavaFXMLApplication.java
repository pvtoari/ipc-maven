/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.pvtoari.ipcmaven.pract6.tableviewIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXMLApplication extends Application {
    static Scene scene;

    private static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract6/tableviewIPC/view/TableView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setWidth(320);
        stage.setHeight(240);
        stage.setScene(scene);
        stage.setTitle("ipc no me hace sonreir");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

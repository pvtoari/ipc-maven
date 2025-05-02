/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.pvtoari.ipcmaven.pract6.setrootIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;


public class JavaFXMLApplication extends Application {
    private static Scene scene;
    private static final HashMap<String, Parent> roots = new HashMap<>();

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static void setRoot(String key) {
        Parent root = roots.get(key);
        if (root == null) return;

        setRoot(root);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract6/setrootIPC/view/Window1.fxml"));
        root = loader.load();
        roots.put("window1", root);

        loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract6/setrootIPC/view/Main.fxml"));
        root = loader.load();
        roots.put("main", root);

        scene = new Scene(root, 320, 240);
        stage.setScene(scene);
        stage.setTitle("tabla vista varias escenas waooooooooooooooooooo");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

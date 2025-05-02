package net.pvtoari.ipcmaven.pract7.internationalizationIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class JavaFXMLApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("net.pvtoari.ipcmaven.pract7.internationalizationIPC.strings", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract7/internationalizationIPC/view/MainView.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setWidth(600);
        stage.setHeight(400);
        stage.setScene(scene);
        stage.setTitle("dialoguemos internacionalmente");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


package net.pvtoari.ipcmaven.pract8.calculatorIPC.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class IPCCalculator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract8/calculatorIPC/view/FXMLCalculator.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("calculadora muy bien hecha");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(450);
        stage.centerOnScreen();
        stage.show();

        scene.getStylesheets().add(getClass().getResource("/net/pvtoari/ipcmaven/pract8/calculatorIPC/styles/styles.css").toExternalForm());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

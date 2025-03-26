package net.pvtoari.ipcmaven.menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AppLoader menuLoader = new AppLoader();
        Map<String, List<Class<?>>> applications = menuLoader.loadJavaFXMLApplications();

        VBox vBox = new VBox(10);
        for (Map.Entry<String, List<Class<?>>> entry : applications.entrySet()) {
            Label sectionLabel = new Label(entry.getKey());
            sectionLabel.setMaxWidth(Double.MAX_VALUE);
            VBox.setVgrow(sectionLabel, Priority.ALWAYS);
            vBox.getChildren().add(sectionLabel);

            for (Class<?> app : entry.getValue()) {
                if(app.equals(Main.class)) continue;

                Button button = getClassButton(app);

                HBox hBox = new HBox(button);
                hBox.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(button, Priority.ALWAYS);

                vBox.getChildren().add(hBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/net/pvtoari/ipcmaven/menu/styles.css")).toExternalForm());

        stage.setTitle("menu tranquilisimo");
        stage.setResizable(false);
        stage.setHeight(500);
        stage.setWidth(350);
        stage.show();

        stage.setScene(scene);
    }

    private static Button getClassButton(Class<?> app) {
        Button button = new Button(app.getPackageName().substring(app.getPackageName().lastIndexOf('.') + 1));
        button.setMinWidth(Region.USE_PREF_SIZE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> {
            try {
                Application application = (Application) app.getDeclaredConstructor().newInstance();
                application.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
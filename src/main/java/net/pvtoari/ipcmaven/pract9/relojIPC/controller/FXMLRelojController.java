package net.pvtoari.ipcmaven.pract9.relojIPC.controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLRelojController implements Initializable {
    private final static int DELAY = 100;

    @FXML
    private Label clockLabel;

    @FXML
    private Button showButton, stopButton;

    private LocalTime time;
    private ScheduledExecutorService executor;
    private boolean stopped = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.executor = Executors.newSingleThreadScheduledExecutor();

        this.showButton.setOnAction(this::showClock);
        this.stopButton.setOnAction(this::stopClock);

        Platform.runLater(() -> this.clockLabel.getScene().getWindow().setOnCloseRequest(event -> this.executor.shutdown()));

        resumeClock();
    }

    private void showClock(ActionEvent event) {
        this.clockLabel.visibleProperty().set(!this.clockLabel.isVisible());
    }

    private void stopClock(ActionEvent event) {
        this.stopped = !stopped;
    }

    private void resumeClock() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.executor.scheduleAtFixedRate(() -> {
            if (this.stopped) return;

            this.time = LocalTime.now();

            Platform.runLater(() -> this.clockLabel.setText(this.time.format(dtf)));

        }, 0, DELAY, TimeUnit.MILLISECONDS);
    }
}

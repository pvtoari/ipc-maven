package net.pvtoari.ipcmaven.pract9.factorialIPC.controller;

import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField inputTextField, outputTextField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button calcButton, cancelButton, exitButton;

    private Task lastTask;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.lastTask = null;

        this.calcButton.setOnAction(this::calculate);
        this.cancelButton.setOnAction(this::cancel);
        this.exitButton.setOnAction(this::exit);
    }

    private void calculate(ActionEvent event) {
        long toCalc = Long.parseLong(this.inputTextField.getText());

        if (toCalc < 0) {
            this.outputTextField.setText("Undefined");
            return;
        }

        this.lastTask = new IntegerFactorial(toCalc);

        outputTextField.textProperty().bind(Bindings.convert(lastTask.valueProperty()));
        outputTextField.visibleProperty().bind(Bindings.not(lastTask.runningProperty()));

        calcButton.disableProperty().bind(lastTask.runningProperty());

        progressBar.progressProperty().bind(lastTask.progressProperty());

        this.lastTask.setOnSucceeded(workerStateEvent -> {
            outputTextField.textProperty().unbind();
            outputTextField.setText(String.valueOf(this.lastTask.getValue()));
        });

        this.executor.submit(lastTask);
    }

    private void cancel(ActionEvent event) {
        if (this.lastTask == null) return;

        this.lastTask.cancel();
    }

    private void exit(ActionEvent event) {
        if (this.lastTask != null) this.lastTask.cancel();

        this.executor.shutdownNow();

        System.exit(0);
    }

    static class IntegerFactorial extends Task<Long> {
        private final Long toCalc;

        public IntegerFactorial(long toCalc) {
            this.toCalc = toCalc;
        }

        @Override
        protected Long call() {
            if(toCalc == 0 || toCalc == 1) {
                updateProgress(1, 1);
                return 1L;
            }

            long f = 1;
            for (long i = 2; i <= toCalc; i++) {
                if (isCancelled()) break;

                f = f * i;
                updateProgress(i, toCalc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    if (isCancelled()) break;
                }
            }

            return f;
        }
    }
}

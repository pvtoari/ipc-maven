package net.pvtoari.ipcmaven.pract2.parte1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Circle circle;

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleKeyPressed(KeyEvent actionEvent) {
        consumeEvent(actionEvent);
    }

    @FXML
    private void handleMousePressed(MouseEvent mouseEvent) {
        consumeEvent(mouseEvent);
    }

    private void consumeEvent(KeyEvent event) {
        switch(event.getCode()) {
            case KeyCode.UP -> setRow(Utils.rowNorm(gridPane, getCurrentRow() - 1));
            case KeyCode.DOWN -> setRow(Utils.rowNorm(gridPane, getCurrentRow() + 1));
            case KeyCode.LEFT -> setColumn(Utils.rowNorm(gridPane, getCurrentColumn() - 1));
            case KeyCode.RIGHT -> setColumn(Utils.columnNorm(gridPane, getCurrentColumn() + 1));
        }

        event.consume();
    }

    private void consumeEvent(MouseEvent mouseEvent) {
        double clickX = mouseEvent.getSceneX();
        double clickY = mouseEvent.getSceneY();

        int clickedRow = Utils.rowCalc(gridPane, clickY);
        int clickedColumn = Utils.columnCalc(gridPane, clickX);

        setRow(clickedRow);
        setColumn(clickedColumn);

        mouseEvent.consume();
    }

    private void setRow(int index) {
        GridPane.setRowIndex(circle, index);
    }

    private void setColumn(int index) {
        GridPane.setColumnIndex(circle, index);
    }

    private int getCurrentRow() {
        return GridPane.getRowIndex(circle);
    }

    private int getCurrentColumn() {
        return GridPane.getColumnIndex(circle);
    }
}

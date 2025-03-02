package net.pvtoari.ipcmaven.pract2.parte2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Circle circle;

    @FXML
    private GridPane gridPane;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider slider;

    private NodeCircle nodeCircle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nodeCircle = new NodeCircle(circle);
        this.nodeCircle.setRadius(slider.getValue());
        this.colorPicker.setValue((Color) nodeCircle.getFill());
    }

    @FXML
    private void handleCircleKeyPressed(KeyEvent actionEvent) {
        consumeCircleKeyPressedEvent(actionEvent);
    }

    @FXML
    private void handleSceneMousePressed(MouseEvent mouseEvent) {
        consumeSceneMousePressedEvent(mouseEvent);
    }

    @FXML
    private void handleToggleButtonOnAction(ActionEvent actionEvent) {
        consumeToggleButtonActionEvent(actionEvent);
    }

    @FXML
    private void handleSliderOnMouseDragged(MouseEvent mouseEvent) {
        consumeSliderMouseDraggedEvent(mouseEvent);
    }

    @FXML
    private void handleColorPickerOnAction(ActionEvent actionEvent) {
        nodeCircle.setFill(colorPicker.getValue());

        actionEvent.consume();
    }

    private void consumeCircleKeyPressedEvent(KeyEvent event) {
        switch(event.getCode()) {
            case KeyCode.UP -> setRow(Utils.rowNorm(gridPane, getCurrentRow() - 1));
            case KeyCode.DOWN -> setRow(Utils.rowNorm(gridPane, getCurrentRow() + 1));
            case KeyCode.LEFT -> setColumn(Utils.rowNorm(gridPane, getCurrentColumn() - 1));
            case KeyCode.RIGHT -> setColumn(Utils.columnNorm(gridPane, getCurrentColumn() + 1));
        }

        event.consume();
    }

    private void consumeSceneMousePressedEvent(MouseEvent mouseEvent) {
        double clickX = mouseEvent.getSceneX();
        double clickY = mouseEvent.getSceneY();

        int clickedRow = Utils.rowCalc(gridPane, clickY);
        int clickedColumn = Utils.columnCalc(gridPane, clickX);

        setRow(clickedRow);
        setColumn(clickedColumn);

        mouseEvent.consume();
    }

    private void consumeToggleButtonActionEvent(ActionEvent event) {
        if(toggleButton.isSelected()) {
            nodeCircle.setFill(Color.TRANSPARENT);
            nodeCircle.setStroke(colorPicker.getValue());
        }
        else {
            nodeCircle.setFill(colorPicker.getValue());
            nodeCircle.setStroke(Color.BLACK);
        }

        event.consume();
    }

    private void consumeSliderMouseDraggedEvent(MouseEvent event) {
        nodeCircle.setRadius(slider.getValue());

        event.consume();
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

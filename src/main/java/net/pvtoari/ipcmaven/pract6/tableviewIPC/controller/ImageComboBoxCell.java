package net.pvtoari.ipcmaven.pract6.tableviewIPC.controller;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageComboBoxCell extends ListCell<Image> {
    private final ImageView imageView = new ImageView();

    @Override
    protected void updateItem(Image item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) setGraphic(null);
        else {
            imageView.setImage(item);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            setGraphic(imageView);
        }
    }
}

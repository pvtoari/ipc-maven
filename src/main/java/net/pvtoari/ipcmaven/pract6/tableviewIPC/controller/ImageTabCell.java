package net.pvtoari.ipcmaven.pract6.tableviewIPC.controller;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import net.pvtoari.ipcmaven.pract6.tableviewIPC.model.Person;

public class ImageTabCell extends TableCell<Person, String> {
    private final ImageView view = new ImageView();

    @Override
    protected void updateItem(String imagePath, boolean bln) {
        super.updateItem(imagePath, bln);

        if (imagePath == null || bln || imagePath.isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            Image image = new Image(getClass().getResourceAsStream(imagePath), 25, 25, true, true);
            view.setImage(image);
            setGraphic(view);
        }
    }
}
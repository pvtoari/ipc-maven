/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.pvtoari.ipcmaven.pract6.setrootIPC.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import net.pvtoari.ipcmaven.pract6.setrootIPC.application.JavaFXMLApplication;

public class FXMLDocumentController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void goToWindow1(ActionEvent event) {
        JavaFXMLApplication.setRoot("window1");
    }

    @FXML
    private void exitPressed(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}

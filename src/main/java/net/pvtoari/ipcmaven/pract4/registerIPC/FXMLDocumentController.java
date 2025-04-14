package net.pvtoari.ipcmaven.pract4.registerIPC;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField emailField, passwordField, repeatPasswordField;

    @FXML
    DatePicker dateField;

    @FXML
    Label emailErrorLabel, passwordErrorLabel, repeatPasswordErrorLabel, dateErrorLabel;

    @FXML
    private Button acceptButton, cancelButton;

    private BooleanProperty validEmail, validPassword, validRepeatPassword, validDate;

    @SuppressWarnings("unused")
    private ChangeListener<String> listenerEmail, listenerPassword, listenerRepeatPassword;

    @SuppressWarnings("unused")
    private ChangeListener<LocalDate> listenerDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validEmail = new SimpleBooleanProperty(false);
        validPassword = new SimpleBooleanProperty(false);
        validRepeatPassword = new SimpleBooleanProperty(false);
        validDate = new SimpleBooleanProperty(false);

        emailField.focusedProperty().addListener(getFocusListener(emailField.textProperty(), validEmail, listenerEmail, this::checkEmail));
        passwordField.focusedProperty().addListener(getFocusListener(passwordField.textProperty(), validPassword, listenerPassword, this::checkPassword));
        repeatPasswordField.focusedProperty().addListener(getFocusListener(repeatPasswordField.textProperty(), validRepeatPassword, listenerRepeatPassword, this::checkPasswordsMatch));
        dateField.focusedProperty().addListener(getFocusListener(dateField.valueProperty(), validDate, listenerDate, this::checkDate));

        dateField.setConverter(getLocalDateStringConverter());

        BooleanBinding validFields = Bindings.and(validEmail, validPassword).and(validRepeatPassword).and(validDate);
        acceptButton.disableProperty().bind(Bindings.not(validFields));
    }

    @FXML
    private void handleAcceptButtonOnAction() {
        emailField.clear();
        passwordField.clear();
        repeatPasswordField.clear();
        dateField.setValue(null);

        validEmail.setValue(Boolean.FALSE);
        validPassword.setValue(Boolean.FALSE);
        validRepeatPassword.setValue(Boolean.FALSE);
        validDate.setValue(Boolean.FALSE);
    }

    @FXML
    private void handleCancelButtonOnAction() {
        cancelButton.getScene().getWindow().hide();
    }

    private static LocalDateStringConverter getLocalDateStringConverter() {
        return new LocalDateStringConverter() {
            @Override
            public LocalDate fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    System.out.println("Exception in fromString");
                    return LocalDate.now();
                }
            }

            @Override
            public String toString(LocalDate value) {
                return super.toString(value);
            }
        };
    }

    @SuppressWarnings("unused")
    private <T> ChangeListener<Boolean> getFocusListener(Property<T> target, BooleanProperty valid, ChangeListener<T> listener, Runnable checker) {
        final ChangeListener<T> finalListener = listener;
        return (a, b, newVal) -> {
            if (!newVal) {
                checker.run();
                if (!valid.get()) {
                    if (finalListener == null) {
                        ChangeListener<T> newListener = (o0, o1, newValue) -> checker.run();
                        target.addListener(newListener);
                    }
                }
            }
        };
    }

    private void checkEmail() {
        String email = emailField.getText();

        boolean isValid = email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        validEmail.set(isValid);

        showError(isValid, emailField, emailErrorLabel);
    }

    private void checkPassword() {
        String password = passwordField.getText();

        boolean isValid = password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}$");
        validPassword.set(isValid);

        showError(isValid, passwordField, passwordErrorLabel);
    }

    private void checkPasswordsMatch() {
        boolean match = passwordField.getText().equals(repeatPasswordField.getText());
        validRepeatPassword.set(match);

        showError(match, repeatPasswordField, repeatPasswordErrorLabel);
    }

    private void checkDate(){
        LocalDate value = dateField.getValue();

        if(value == null) return;

        boolean isValid = value.isBefore(LocalDate.now().minusYears(16));
        validDate.set(isValid);
        showError(isValid, dateField, dateErrorLabel);
    }

    private void showError(boolean isValid, Node field, Node errorMessage) {
        errorMessage.setVisible(!isValid);

        field.setStyle(((isValid) ? "" : "-fx-background-color: #FCE5E0"));
    }
}
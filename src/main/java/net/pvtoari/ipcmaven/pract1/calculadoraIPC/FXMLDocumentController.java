package net.pvtoari.ipcmaven.pract1.calculadoraIPC;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FXMLDocumentController implements Initializable {
    private final String BACK_SPACE = "\b";
    private final String ENTER = "\r";

    @FXML
    private TextField screen;

    private StringBuilder sb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sb = new StringBuilder();
    }

    @FXML
    private void pressedButton(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        String token = source.getText();

        consumeToken(token);
    }

    @FXML
    private void releasedKey(KeyEvent keyEvent) {
        String token = keyEvent.getText();

        if(isSupportedToken(token)) consumeToken(token);
    }

    private void consumeToken(String token) {
        switch(token) {
            case "C", "c" -> {
                sb = new StringBuilder();
                screen.setText("");
            }
            case "=", ENTER -> computeExpression();
            case BACK_SPACE -> deleteLastToken();
            default -> {
                sb.append(token);
                screen.setText(sb.toString());
            }
        }
    }

    private void computeExpression() {
        try {
            Expression e = new ExpressionBuilder(sb.toString()).build();
            double result = e.evaluate();

            String finalText;
            if(isInteger(result)) finalText = String.format(Locale.US, "%.0f", result);
            else finalText = String.format(Locale.US, "%.5f", result);

            screen.setText(finalText);
        } catch(Exception e) {
            screen.setText("Error");
        } finally {
            sb = new StringBuilder();
        }
    }

    private boolean isInteger(double result) {
        return result%1 == 0;
    }

    private boolean isSupportedToken(String token) {
        return token.matches("[0-9+\\-*^.\\/Cc\b\r]");
    }

    private void deleteLastToken() {
        if(!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
            screen.setText(sb.toString());
        }
    }
}

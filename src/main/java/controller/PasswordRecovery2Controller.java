package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class PasswordRecovery2Controller extends BaseController {

    @FXML
    private TextField codeField;
    
    public void initialize() {       
        codeField.setFocusTraversable(false);
    }

    @FXML
    void ValidBtnEnter(ActionEvent event) throws IOException {
        if (!codeField.getText().equals("937706")) {
            showAlert(AlertType.ERROR, "エラー", "コードが無効です");
        }
        switchToPasswordRecovery3(event);
    }
    
    @FXML
    void codeFieldEnter(Event event) throws IOException {
        codeField.setText("");
    }
    
    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
        switchToPasswordRecovery1(event);
    }

}

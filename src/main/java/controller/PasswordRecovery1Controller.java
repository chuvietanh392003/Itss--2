package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PasswordRecovery1Controller extends BaseController {

    @FXML
    private TextField mailField;

    @FXML
    void codeSendBtnEnter(ActionEvent event) throws IOException {
    	// takeEmailMethod()
    	switchToPasswordRecovery2(event);
    }

    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
    	switchToLogin(event);
    }

}

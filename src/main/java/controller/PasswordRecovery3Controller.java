package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PasswordRecovery3Controller extends BaseController {

    @FXML
    private TextField newPassField;

    @FXML
    private TextField newPassVerifyField;

    @FXML
    void enterBtnEnter(ActionEvent event) throws IOException {
    	//processMethod (check ...)
    	// setting new pw
    	switchToLogin(event);
    }

    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
    	switchToPasswordRecovery2(event);
    }

}

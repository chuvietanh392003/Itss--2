package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PasswordRecovery2Controller extends BaseController {

    @FXML
    private TextField codeField;

    @FXML
    void ValidBtnEnter(ActionEvent event) throws IOException {
    	//verify method
    	switchToPasswordRecovery3(event);
    }

    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
    	switchToPasswordRecovery1(event);
    }

}

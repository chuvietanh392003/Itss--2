package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends BaseController {
	  @FXML
	    private Button loginButton;

	    @FXML
	    private TextField passwordField;

	    @FXML
	    private TextField usernameField;
	    
	    @FXML
	    private Label wrongLogin;

	    @FXML
	    void logInEnter(ActionEvent event) throws IOException {
	    	if(usernameField.getText().equals("admin") && passwordField.getText().equals("12345")) {
	            switchToTemplateManage(event);
	        } else if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
	            wrongLogin.setText("Please enter your data!");
	        } else {
	            wrongLogin.setText("Wrong username or password!");
	        }
	    }
}

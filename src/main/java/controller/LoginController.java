package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.repository.UserRepository;

public class LoginController extends BaseController{

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;
    
    @FXML
    private TextField usernameField;

    @FXML
    private Label wrongLogin;
    
    private UserRepository userRepository = new UserRepository();

    @FXML
    void logInEnter(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            wrongLogin.setText("Username or password cannot be empty!");
        } else if (userRepository.checkCredentials(username, password)) {
            if(userRepository.isAdmin(username))
            	switchToAdminHomePage(event);
            else
            	switchToUserHomepage(event);
        } else {
            wrongLogin.setText("Invalid username or password!");
        }
    }

    @FXML
    void passwordRecoveryClick(MouseEvent event) throws IOException {
    	switchToPasswordRecovery1(event);
    }

    @FXML
    void signupClick(MouseEvent event) throws IOException {
    	switchToSignupPage(event);
    }

}

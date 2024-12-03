package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.repository.UserRepository;

public class SignupPageController extends BaseController {

    @FXML
    private TextField mailField;

    @FXML
    private TextField pwFVerifyField;

    @FXML
    private TextField pwField;

    @FXML
    private TextField userNameField;

    @FXML
    void loginEnter(MouseEvent event) throws IOException {
    	switchToLogin(event);
    }

    @FXML
    void signupBtnEnter(ActionEvent event) {
    	String userName = userNameField.getText();
    	String mail = mailField.getText();
    	String password = pwField.getText();
    	String passwordVerify = pwFVerifyField.getText();
    	
    	UserRepository newUserRepo = new UserRepository();
    	if(password.equals(passwordVerify)) {
    		newUserRepo.addUser(userName, mail, passwordVerify, false);
    		showAlert(AlertType.INFORMATION, "Thành công", "Đã đăng ký thành công!" );
    	}
    	else {
    		showAlert(AlertType.ERROR, "Thất bại", "Mật khẩu xác nhận chưa khớp với mật khẩu");
    	}
    }   
}	

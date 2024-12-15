package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import main.java.controller.PasswordRecovery1Controller;
import main.java.service.UserService;
import main.java.service.implement.UserServiceImp;

public class PasswordRecovery3Controller extends BaseController {

    @FXML
    private TextField newPassField;  

    @FXML
    private TextField newPassVerifyField; 

    @FXML
    void enterBtnEnter(ActionEvent event) throws IOException {
        String newPassword = newPassField.getText();
        String newPasswordVerify = newPassVerifyField.getText();


        if (newPassword.isEmpty() || newPasswordVerify.isEmpty()) {
            showAlert(AlertType.ERROR, "エラー", "すべてのフィールドを入力してください。");  
        } else if (!newPassword.equals(newPasswordVerify)) {
            showAlert(AlertType.ERROR, "エラー", "パスワードが一致しません。"); 
        } else {
            UserService user = new UserServiceImp();
            String gmail = PasswordRecovery1Controller.gmail;
            user.changePassword(gmail, newPassword);
            showAlert(AlertType.INFORMATION, "成功", "パスワードが正常に変更されました。"); 
            switchToLogin(event);
        }
    }

    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
        switchToPasswordRecovery2(event);
    }
}

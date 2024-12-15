package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.service.UserService;
import main.java.service.implement.UserServiceImp;

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

        if (userName.isEmpty() || mail.isEmpty() || password.isEmpty() || passwordVerify.isEmpty()) {
            showAlert(AlertType.ERROR, "エラー", "すべてのフィールドに情報を入力してください！");
            return;
        }

        UserService userService = new UserServiceImp(); 
        if (password.equals(passwordVerify)) {
            userService.addUser(userName, mail, passwordVerify, false);
            showAlert(AlertType.INFORMATION, "成功", "登録が完了しました！");
        } else {
            showAlert(AlertType.ERROR, "失敗", "パスワード確認が一致していません");
        }
    }   
}

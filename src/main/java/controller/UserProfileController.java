package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.java.model.User;
import main.java.service.UserService;
import main.java.service.implement.UserServiceImp;
import main.java.session.SessionManager;

public class UserProfileController extends BaseController {
	
    @FXML
    private TextArea mailField;

    @FXML
    private TextArea pw2Field;

    @FXML
    private TextArea pwField;

    @FXML
    private TextArea usernameField;
    
    User currentUser = SessionManager.getInstance().getCurrentUser();
    UserService currentUserService = SessionManager.getInstance().getCurrentUserService();
	UserService user = new UserServiceImp();

    @FXML
    public void initialize() {
        usernameField.setEditable(false); 
        usernameField.setMouseTransparent(true); // Ngăn không cho chuột nhấp vào
        usernameField.setFocusTraversable(false);
        mailField.setFocusTraversable(false);
        pwField.setFocusTraversable(false);
        pw2Field.setFocusTraversable(false);
        
        // display
        usernameField.setText(currentUser.getUsername());
        mailField.setText(currentUser.getGmail());
        pwField.setText(currentUser.getPassword());
    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        switchToLogin(event);
    }

    @FXML
    void settingClicked(MouseEvent event) throws IOException {
        switchToUserSetting(event);
    }

    @FXML
    void templateSaveClicked(MouseEvent event) throws IOException {
        switchToUserTemplateSave(event);
    }

    @FXML
    void userHomepageClicked(MouseEvent event) throws IOException {
        switchToUserHomepage(event);
    }
    
    @FXML
    void userTemplateClicked(MouseEvent event) throws IOException {
        switchToUserTemplate(event);
    }
    
    @FXML
    void userProfileClicked(MouseEvent event) throws IOException {
        switchToUserProfile(event);
    }
    
    @FXML
    void changeMailCancel(ActionEvent event) {
    	mailField.setText(currentUser.getGmail());
    }

    @FXML
    void changeMailEnter(ActionEvent event) {
    	String newGmail = mailField.getText();
    	currentUserService.changeGmail(currentUser.getUsername(), newGmail);
    	showAlert(AlertType.INFORMATION, newGmail, newGmail);
    }

    @FXML
    void changePasswordCancel(ActionEvent event) {
    	pwField.setText(currentUser.getPassword());
    	pw2Field.setText("");
    }

    @FXML
    void changePasswordEnter(ActionEvent event) {
        String pw = pwField.getText();
        String pw2 = pw2Field.getText();
        if (pw.equals("") || pw2.equals("")) {
            showAlert(AlertType.ERROR, "エラー", "すべてのフィールドに入力してください");
        } else if (!pw.equals(pw2)) {
            showAlert(AlertType.ERROR, "エラー", "パスワードが一致しません");
        } else if (pw.length() < 6) {
            showAlert(AlertType.ERROR, "エラー", "パスワードは6文字以上でなければなりません");
        } else {
            currentUserService.changePassword(currentUser.getGmail(), pw2);
            showAlert(AlertType.INFORMATION, "成功", "パスワードが正常に変更されました");
        }
    }

}

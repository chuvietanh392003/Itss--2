package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.model.User;
import main.java.service.UserService;
import main.java.service.implement.UserServiceImp;
import main.java.session.SessionManager;

public class UserProfileController extends BaseController {
	
    @FXML
    private TextArea mailField;

    @FXML
    private PasswordField pw2Field;

    @FXML
    private PasswordField pwField;

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
    
    @FXML
    void showPassword(Event event) {
        TextField visiblePwField = new TextField();
        visiblePwField.setText(pwField.getText()); // Lấy mật khẩu hiện tại

        // Lấy vị trí và kích thước từ pwField
        visiblePwField.setManaged(false);
        visiblePwField.setLayoutX(pwField.getBoundsInParent().getMinX()); // Lấy vị trí chính xác trong container cha
        visiblePwField.setLayoutY(pwField.getBoundsInParent().getMinY());
        visiblePwField.setPrefWidth(pwField.getWidth());
        visiblePwField.setPrefHeight(pwField.getHeight());

        // Khi người dùng di chuột ra khỏi trường
        visiblePwField.setOnMouseExited(e -> {
            pwField.setText(visiblePwField.getText());
            ((javafx.scene.layout.Pane) pwField.getParent()).getChildren().remove(visiblePwField); // Xóa TextField
            pwField.setVisible(true); // Hiển thị lại PasswordField
        });

        // Thêm TextField vào giao diện
        ((javafx.scene.layout.Pane) pwField.getParent()).getChildren().add(visiblePwField);

        pwField.setVisible(false); // Ẩn PasswordField
        visiblePwField.requestFocus(); // Focus vào TextField mới
    }

    @FXML
    void showPasswordCf(Event event) {
        TextField visiblePw2Field = new TextField();
        visiblePw2Field.setText(pw2Field.getText()); // Lấy mật khẩu xác nhận hiện tại

        // Lấy vị trí và kích thước từ pw2Field
        visiblePw2Field.setManaged(false);
        visiblePw2Field.setLayoutX(pw2Field.getBoundsInParent().getMinX()); // Lấy vị trí chính xác trong container cha
        visiblePw2Field.setLayoutY(pw2Field.getBoundsInParent().getMinY());
        visiblePw2Field.setPrefWidth(pw2Field.getWidth());
        visiblePw2Field.setPrefHeight(pw2Field.getHeight());

        // Khi người dùng di chuột ra khỏi trường
        visiblePw2Field.setOnMouseExited(e -> {
            pw2Field.setText(visiblePw2Field.getText());
            ((javafx.scene.layout.Pane) pw2Field.getParent()).getChildren().remove(visiblePw2Field); // Xóa TextField
            pw2Field.setVisible(true); // Hiển thị lại PasswordField
        });

        // Thêm TextField vào giao diện
        ((javafx.scene.layout.Pane) pw2Field.getParent()).getChildren().add(visiblePw2Field);

        pw2Field.setVisible(false); // Ẩn PasswordField
        visiblePw2Field.requestFocus(); // Focus vào TextField mới
    }


}

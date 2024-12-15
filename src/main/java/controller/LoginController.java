package main.java.controller;

import java.io.IOException;
import java.util.prefs.Preferences;

import com.google.api.client.auth.oauth2.Credential;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import main.java.model.User;
import main.java.service.UserService;
import main.java.service.implement.UserServiceImp;
import main.java.service.GoogleOAuthService;
import main.java.service.implement.GoogleOAuthServiceImp;
import main.java.session.SessionManager;

public class LoginController extends BaseController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private WebView webView;

    @FXML
    private CheckBox rememberMeCheckbox;

    private UserService userService = new UserServiceImp();

    // Preferences API to store user credentials
    private Preferences preferences = Preferences.userNodeForPackage(LoginController.class);

    @FXML
    public void initialize() {
        // Load saved user credentials and preferences
        String savedUsername = preferences.get("username", "");
        String savedPassword = preferences.get("password", "");
        boolean rememberMe = preferences.getBoolean("rememberMe", false);

        // Populate the fields with saved credentials
        usernameField.setText(savedUsername);
        passwordField.setText(savedPassword);
        rememberMeCheckbox.setSelected(rememberMe);
        webView.setVisible(false);
    }

    @FXML
    void logInEnter(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "エラー", "ユーザー名またはパスワードを空にすることはできません！");
        } else if (userService.checkCredentials(username, password)) {
            // Populate the user object with data from the userService
            User user = userService.getUserByUsername(username);
            SessionManager.getInstance().setCurrentUser(user);
            SessionManager.getInstance().setCurrentUserService(userService);

            // Save login credentials if "remember me" is checked
            if (rememberMeCheckbox.isSelected()) {
                preferences.put("username", username);
                preferences.put("password", password);
                preferences.putBoolean("rememberMe", true);
            } else {
                // Remove credentials if "remember me" is unchecked
                preferences.remove("username");
                preferences.remove("password");
                preferences.putBoolean("rememberMe", false);
            }

            if (userService.isAdmin(username)) {
                switchToAdminHomePage(event); // Implement switchToAdminHomePage method in BaseController
            } else {
                switchToUserHomepage(event);  // Implement switchToUserHomepage method in BaseController
            }
        } else {
            showAlert(AlertType.ERROR, "エラー", "パスワードが間違っています.");
        }
    }

    @FXML
    void loginByGoogleEnter(ActionEvent event) throws IOException {
    	webView.setVisible(true);
        try {
            GoogleOAuthService googleOAuthService = new GoogleOAuthServiceImp();
            String googleLoginUrl = googleOAuthService.getOAuthUrl();
            printCurrentURL(event);
            webView.getEngine().load(googleLoginUrl);
            printCurrentURL(event);
            
//            showAlert(AlertType.CONFIRMATION, "da dang ky thanh cong", "Đã đăng ký thành công user :"+ ", mật khẩu mặc định của bạn là 123456");
//            switchToUserHomepage(event);
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "エラー", "Googleログイン中にエラーが発生しました.");
        }
    }
    
    
    @FXML
    void printCurrentURL(ActionEvent event) {
        // Lấy URL hiện tại từ WebView
        String currentURL = webView.getEngine().getLocation();
        
        // In ra URL
        System.out.println("Current URL: " + currentURL);
    }


    @FXML
    void passwordRecoveryClick(MouseEvent event) throws IOException {
        switchToPasswordRecovery1(event); 
    }

    @FXML
    void signupClick(MouseEvent event) throws IOException {
    	System.out.println("1");
        switchToSignupPage(event);
    }

    @FXML
    void userTextFieldEnter(Event event) throws IOException {
        usernameField.setText(""); 
    }

    @FXML
    void passwordTextFieldEnter(Event event) throws IOException {
        passwordField.setText(""); 
    }
}

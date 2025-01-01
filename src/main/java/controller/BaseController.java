package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.java.model.Template;

import java.io.IOException;

public class BaseController {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    
    @FXML
    protected void switchToAdminHomePage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/AdminHomepage.fxml");
    }
    
    protected void switchToLandingPage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/LandingPage.fxml");
    }
    
    @FXML
    protected void switchToLogin(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/LoginPage.fxml");
    }
    
    @FXML
    protected void switchToPasswordRecovery1(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/PasswordRecovery1.fxml");
    }
    
    @FXML
    protected void switchToPasswordRecovery2(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/PasswordRecovery2.fxml");
    }
    
    @FXML
    protected void switchToPasswordRecovery3(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/PasswordRecovery3.fxml");
    }
    
    @FXML
    protected void switchToSignupPage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/SignupPage.fxml");
    }
    
    @FXML
    protected void switchToTemplateManage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/ManageTemplate.fxml");
    }
    
    @FXML
    protected void switchToUserHomepage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/UserHomepage.fxml");
    }
    
    @FXML
    protected void switchToUserManage(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/ManageUser.fxml");
    }
    
    @FXML
    protected void switchToUserProfile(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/UserProfile.fxml");
    }
    
    @FXML
    protected void switchToUserSetting(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/UserSetting.fxml");
    }
    
    @FXML
    protected void switchToUserTemplate(Event event) throws IOException {
        switchScene(event, "/main/resources/fxml/UserTemplate.fxml");
    }
    
    @FXML
    protected void switchToUserTemplateSave(Event event) throws IOException{
    	switchScene(event, "/main/resources/fxml/SavedTemplate.fxml");
    }
    
    @FXML
    protected void switchToTemplateView(Event event) throws IOException {
       switchScene(event, "/main/resources/fxml/AdminTemplateView.fxml");
    }
    
    @FXML
    protected void switchToUserTemplateView(Event event) throws IOException {
       switchScene(event, "/main/resources/fxml/UserTemplateView.fxml");
    }
    
    @FXML
    protected void switchToUserAddTemplate(Event event) throws IOException {
       switchScene(event, "/main/resources/fxml/UserAddTemplate.fxml");
    }
    
    @FXML
    protected void switchUserTemplateViewSaveToView(Event event) throws IOException {
       switchScene(event, "/main/resources/fxml/UserTemplateViewSaveToView.fxml");
    }
    
    // Phương thức này sẽ giữ nguyên kích thước cửa sổ ban đầu 
    private void switchScene(Event event, String fxmlPath) throws IOException {
        Node source = (Node) event.getSource();  
        Stage stage = (Stage) source.getScene().getWindow();  

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));  // Tải file FXML
        Parent root = loader.load();

        double width = stage.getScene().getWidth();  // Lấy chiều rộng của cửa sổ hiện tại
        double height = stage.getScene().getHeight();  // Lấy chiều cao của cửa sổ hiện tại

        Scene scene = new Scene(root, width, height); 
        stage.setScene(scene);  
    }

    protected void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

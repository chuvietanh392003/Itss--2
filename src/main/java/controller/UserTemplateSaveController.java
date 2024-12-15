package main.java.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class UserTemplateSaveController extends BaseController {

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
    void userProfileClicked(MouseEvent event)throws IOException {
    	switchToUserProfile(event);
    }
}

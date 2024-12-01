package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AdminHomepageController extends BaseController {

    @FXML
    void TemplateManageClick(MouseEvent event) throws IOException {
    	switchToTemplateManage(event);
    }

    @FXML
    void adHomepageClick(MouseEvent event) throws IOException {
    	switchToAdminHomePage(event);
    }

    @FXML
    void logOutEnter(ActionEvent event) throws IOException {
    	switchToLogin(event);
    }

    @FXML
    void userManageClick(MouseEvent event) throws IOException {
    	switchToUserManage(event);
    }
}

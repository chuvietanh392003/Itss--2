package main.java.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class TemplateManageController extends BaseController{
    @FXML
    void TemplateManageClick(MouseEvent event) throws IOException {
    	switchToTemplateManage(event);
    }

    @FXML
    void adHomepageClick(MouseEvent event) throws IOException {
    	switchToAdminHomePage(event);
    }

    @FXML
    void userManageClick(MouseEvent event) throws IOException {
    	switchToUserManage(event);
    }
}

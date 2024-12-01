package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LandingPageController extends BaseController {

    @FXML
    void startBtnEnter(ActionEvent event) throws IOException {
    	switchToLogin(event);
    }

}

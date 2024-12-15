package main.java.controller;  
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class PasswordRecovery1Controller extends BaseController {
	
	public static String gmail;
	
    @FXML
    private TextField mailField;
    
    @FXML
    public void initialize() {       
        mailField.setFocusTraversable(false);
    }
    
    @FXML
    void textFieldEnter(Event event) throws IOException {
        mailField.setText("");
    }
    
    @FXML
    void codeSendBtnEnter(ActionEvent event) throws Exception {
    	
        gmail = mailField.getText();
        if (!gmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        	showAlert(AlertType.ERROR,"エラー","無効なGmailアドレスです");
            return;
        }
//
//        // Tạo mã 6 số ngẫu nhiên
//        String verificationCode = generateRandomCode();
//
//        String emailContent = "Mã xác minh của bạn là: " + verificationCode;
//        EmailService emailService = new EmailService();
//        emailService.sendVerificationEmail(gmail, emailContent);
//        System.out.println("Mã xác minh đã được gửi tới email " + gmail);
//
//        // Chuyển sang trang tiếp theo
        else {
        	
        	showAlert(AlertType.INFORMATION, "成功", "OTPが送信されました");
            switchToPasswordRecovery2(event);
        }
    }

//    // Hàm tạo mã ngẫu nhiên
//    private String generateRandomCode() {
//        Random random = new Random();
//        int code = 100000 + random.nextInt(900000);
//        return String.valueOf(code);
//    }
    

    @FXML
    void returnBtnEnter(ActionEvent event) throws IOException {
        switchToLogin(event);
    }
}

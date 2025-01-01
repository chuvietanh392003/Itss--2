package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.java.service.TemplateDetailSerice;
import main.java.service.implement.TemplateDetailServiceImp;
import main.java.session.SessionManager;

public class UserAddTemplateControllor extends BaseController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField notionField;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea textField;

    @FXML
    private TextField titleField;
    
    private int userId = SessionManager.getInstance().getCurrentUser().getId();
    
    @FXML
    void addBtnEnter(ActionEvent event) {
        // 入力フィールドからデータを取得
        String title = titleField.getText();
        String description = descriptionField.getText();
        String templateText = textField.getText();
        String setsumeiText = notionField.getText();
        
        // 必須フィールドが空でないかチェック
        if (title.isEmpty() || description.isEmpty() || templateText.isEmpty() || setsumeiText.isEmpty()) {
            // フィールドが空の場合、エラーメッセージを表示
            showAlert(AlertType.ERROR,"エラー", "すべての情報を入力してください");
            return;
        }

        // テンプレートをデータベースに追加するメソッドを呼び出し
        try {
            // ここでテンプレートサービスのcreateTemplateDetailメソッドを呼び出して、テンプレートを保存
        	TemplateDetailSerice tmp = new TemplateDetailServiceImp();
            tmp.createTemplateDetail(userId, title, description, templateText, setsumeiText);
            
            // 追加が成功した場合、成功メッセージを表示し、次の画面に遷移
            showAlert(AlertType.INFORMATION,"成功", "テンプレートが正常に追加されました！");
            switchToUserTemplateSave(event);  // 次の画面に遷移
        } catch (Exception e) {
            // エラーが発生した場合、エラーメッセージを表示
            showAlert(AlertType.ERROR,"エラー", "テンプレート追加中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    @FXML 
    void cancelBtnEnter(ActionEvent event) throws IOException{
    	switchToUserTemplateSave(event);
    }
}

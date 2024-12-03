package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load file FXML, nó sẽ trả về một AnchorPane nếu trong FXML sử dụng AnchorPane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/fxml/LoginPage.fxml"));
            AnchorPane root = loader.load(); // Load layout từ FXML (AnchorPane)
            
            // Tạo Scene với root là AnchorPane từ FXML
            Scene scene = new Scene(root, 1920, 1080);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

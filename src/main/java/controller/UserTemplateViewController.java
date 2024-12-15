package main.java.controller;

import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import main.java.model.Template;
import main.java.service.TemplateDetailSerice;
import main.java.service.implement.TemplateDetailServiceImp;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.text.Text;
import java.io.IOException;

public class UserTemplateViewController extends BaseController {

    @FXML
    private TextArea setsumeiTextArea;

    @FXML
    private TextArea templateTextArea;

    Template userSelectedTemplate = UserTemplateController.uesrSelectedTemplate;
    TemplateDetailSerice templateDetailSerice = new TemplateDetailServiceImp();

    @FXML
    void initialize() {
        if (userSelectedTemplate != null) {
            templateTextArea.setText(templateDetailSerice.getTemplateTextByTemplate(userSelectedTemplate));
            setsumeiTextArea.setText(templateDetailSerice.getTemplateSetsumeiByTemplate(userSelectedTemplate));
            setsumeiTextArea.setEditable(false); 
        }
    }

    @FXML
    void cancelEnter(Event event) throws IOException {
        // Trở lại TemplateManageController
        switchToUserTemplate(event);
    }

    @FXML
    void exportPDF(Event event) {
        // Lấy nội dung từ TextArea (templateTextArea)
        String templateContent = templateTextArea.getText();

        // Sử dụng PrinterJob để in nội dung ra PDF
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(((Node) event.getSource()).getScene().getWindow())) {
            // Tạo đối tượng Text để in
            Text text = new Text(templateContent);
            
            // In nội dung TextArea ra file PDF (hoặc máy in PDF nếu có)
            boolean success = printerJob.printPage(text);
            if (success) {
                // Hiển thị hộp thoại lưu file PDF
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                java.io.File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

                if (file != null) {
                    // Nếu file được chọn, in ra file PDF
                    printerJob.endJob();
                    System.out.println("PDF đã được lưu thành công!");
                }
            }
        }
    }
}

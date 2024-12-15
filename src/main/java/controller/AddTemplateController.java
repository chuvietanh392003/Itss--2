package main.java.controller;

import main.java.model.Template;
import main.java.service.TemplateService;
import main.java.view.AddTemplateView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTemplateController {
    private AddTemplateView view;
    private TemplateService templateService;

    public AddTemplateController(AddTemplateView view, TemplateService templateService) {
        this.view = view;
        this.templateService = templateService;

        // Gắn sự kiện cho các nút
        this.view.addAddTemplateListener(new AddTemplateListener());
        this.view.addCancelListener(e -> view.close());
    }

    class AddTemplateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String templateTitle = view.getTemplateTitle();
            String templateDescription = view.getTemplateDescription();

            if (templateTitle.isEmpty() || templateDescription.isEmpty()) {
                view.showErrorMessage("All fields are required!");
            } else {
                // Tạo đối tượng Template với dữ liệu từ giao diện
                Template template = new Template(templateTitle, templateDescription);

                // Gọi phương thức createTemplate để thêm template vào cơ sở dữ liệu
                templateService.createTemplate(template);

                // Đóng cửa sổ khi thêm thành công
                view.close();
            }
        }
    }
}

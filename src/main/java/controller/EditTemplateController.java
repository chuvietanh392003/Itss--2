package main.java.controller;

import main.java.model.Template;
import main.java.service.TemplateService;
import main.java.view.EditTemplateView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTemplateController {
    private EditTemplateView view;
    private TemplateService templateService;
    private Template selectedTemplate;

    public EditTemplateController(EditTemplateView view, TemplateService templateService, Template selectedTemplate) {
        this.view = view;
        this.templateService = templateService;
        this.selectedTemplate = selectedTemplate;

        // Điền các giá trị hiện tại vào các trường
        populateFields();

        // Gắn sự kiện cho các nút
        this.view.addSaveTemplateListener(new SaveTemplateListener());
        this.view.addCancelListener(e -> view.close());
    }

    private void populateFields() {
        view.setTemplateTitle(selectedTemplate.getTemplateTitle());
        view.setTemplateDescription(selectedTemplate.getTemplateDes());
    }

    class SaveTemplateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String updatedTitle = view.getTemplateTitle();
            String updatedDescription = view.getTemplateDescription();

            if (updatedTitle.isEmpty() || updatedDescription.isEmpty()) {
                view.showErrorMessage("All fields are required!");
            } else {
                // Cập nhật dữ liệu mới vào đối tượng selectedTemplate
                selectedTemplate.setTemplateTitle(updatedTitle);
                selectedTemplate.setTemplateDes(updatedDescription);

                // Cập nhật đối tượng vào cơ sở dữ liệu thông qua service
                templateService.updateTemplate(selectedTemplate);

                // Đóng giao diện sau khi chỉnh sửa thành công
                view.close();
            }
        }
    }
}

package main.java;

import main.java.model.Template;
import main.java.service.implement.TemplateServiceImpl;

import java.util.List;

public class TestTemplate {

    public static void main(String[] args) {
        TemplateServiceImpl templateService = new TemplateServiceImpl();

        // Test lấy tất cả templates từ cơ sở dữ liệu
        System.out.println("Lấy tất cả các template:");
        List<Template> templates = templateService.getAllTemplates();

        if (templates.isEmpty()) {
            System.out.println("Không có template nào trong cơ sở dữ liệu.");
        } else {
            for (Template template : templates) {
                System.out.println("ID: " + template.getTemplateId());
                System.out.println("Title: " + template.getTemplateTitle());
                System.out.println("Description: " + template.getTemplateDes());
                System.out.println("View Count: " + template.getViewCount());  // Hiển thị viewCount
                System.out.println("Save Count: " + template.getSaveCount());  // Hiển thị saveCount
                System.out.println("----------------------------");
            }
        }

        // Test thêm template mới
        System.out.println("\nThêm một template mới:");
        Template newTemplate = new Template();
        newTemplate.setTemplateTitle("Mẫu Test");
        newTemplate.setTemplateDes("Mô tả cho mẫu test");
        newTemplate.setViewCount(0);  // Gán giá trị mặc định cho viewCount
        newTemplate.setSaveCount(0);  // Gán giá trị mặc định cho saveCount
        templateService.createTemplate(newTemplate);
        System.out.println("Thêm template thành công!");

        // Test tìm kiếm template theo ID
        System.out.println("\nTìm kiếm template với ID 1:");
        Template searchResult = templateService.getTemplateById(1);

        if (searchResult != null) {
            System.out.println("ID: " + searchResult.getTemplateId());
            System.out.println("Title: " + searchResult.getTemplateTitle());
            System.out.println("Description: " + searchResult.getTemplateDes());
            System.out.println("View Count: " + searchResult.getViewCount());  // Hiển thị viewCount
            System.out.println("Save Count: " + searchResult.getSaveCount());  // Hiển thị saveCount
        } else {
            System.out.println("Không tìm thấy template với ID 1.");
        }
    }
}

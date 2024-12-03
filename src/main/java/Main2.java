package main.java;

import main.java.model.Template;
import main.java.repository.TemplateRepository;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        TemplateRepository templateRepository = new TemplateRepository();

        // Test lấy tất cả templates từ cơ sở dữ liệu
        System.out.println("Lấy tất cả các template:");
        List<Template> templates = templateRepository.getAllTemplates();
        
        if (templates.isEmpty()) {
            System.out.println("Không có template nào trong cơ sở dữ liệu.");
        } else {
            for (Template template : templates) {
                System.out.println("ID: " + template.getTemplateId());
                System.out.println("Title: " + template.getTemplateTitle());
                System.out.println("Description: " + template.getTemplateDes());
                System.out.println("----------------------------");
            }
        }

        // Test thêm template mới
        System.out.println("\nThêm một template mới:");
        Template newTemplate = new Template();
        newTemplate.setTemplateTitle("Mẫu Test");
        newTemplate.setTemplateDes("Mô tả cho mẫu test");
        templateRepository.addTemplate(newTemplate);
        System.out.println("Thêm template thành công!");

        // Test tìm kiếm template theo từ khóa
        System.out.println("\nTìm kiếm template với từ khóa 'Test':");
        List<Template> searchResults = templateRepository.searchTemplates("Test");
        
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy template nào với từ khóa 'Test'.");
        } else {
            for (Template template : searchResults) {
                System.out.println("ID: " + template.getTemplateId());
                System.out.println("Title: " + template.getTemplateTitle());
                System.out.println("Description: " + template.getTemplateDes());
                System.out.println("----------------------------");
            }
        }
    }
}

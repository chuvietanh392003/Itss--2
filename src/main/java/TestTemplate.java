package main.java;

import main.java.model.Template;
import main.java.service.TemplateDetailSerice;
import main.java.service.implement.TemplateDetailServiceImp;
import main.java.service.implement.TemplateServiceImpl;

import java.util.List;

public class TestTemplate {

    public static void main(String[] args) {
        TemplateServiceImpl templateService = new TemplateServiceImpl();

        // Test lấy tất cả templates từ cơ sở dữ liệu
        System.out.println("Lấy tất cả các template:");
        List<Template> templates = templateService.getAllTemplatesForUserId(2);

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
        System.out.println("\nThêm một template detail mới:");
        TemplateDetailSerice tplNew = new TemplateDetailServiceImp() ;
        tplNew.createTemplateDetail(21,"test", "mô tả mẫu test","day la mau test", "day la test");
        System.out.println("thanh cong");
    }
}

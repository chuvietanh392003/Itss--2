package main.java.service.implement;

import main.java.model.Template;
import main.java.model.TemplateDetail;
import main.java.repository.implement.TemplateDetailRepositoryImp;
import main.java.service.TemplateDetailSerice;

import java.util.List;

public class TemplateDetailServiceImp implements TemplateDetailSerice {

    private TemplateDetailRepositoryImp repository;

    // Constructor để khởi tạo repository
    public TemplateDetailServiceImp() {
        this.repository = new TemplateDetailRepositoryImp(); // Tạo đối tượng repository
    }

    @Override
    public List<TemplateDetail> getTemplateDetails() {
        // Gọi repository để lấy danh sách TemplateDetail
        return repository.getTemplateDetails();
    }

    @Override
    public String getTemplateTextByTemplate(Template template) {
        // Gọi repository để lấy template_text từ TemplateDetail
        return repository.getTemplateTextByTemplate(template);
    }

    @Override
    public String getTemplateSetsumeiByTemplate(Template template) {
        // Gọi repository để lấy template_setsumei từ TemplateDetail
        return repository.getTemplateSetsumeiByTemplate(template);
    }
    
    @Override
    public void createTemplateDetail(int id, String title, String description, String templateText, String setsumeiText) {
    	repository.createTemplateDetail(id ,title, description, templateText, setsumeiText);
    }
}

package main.java.repository;

import main.java.model.Template;

import java.util.List;

public interface TemplateRepository {
    List<Template> getAllTemplates();
    Template getTemplateById(int id);
    void createTemplate(Template template);
    void updateTemplate(Template template);
    void deleteTemplate(int id);
    void incrementViewCount(int templateId);  
    void incrementSaveCount(int templateId); 
}

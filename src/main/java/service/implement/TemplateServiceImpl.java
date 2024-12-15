package main.java.service.implement;

import main.java.model.Template;
import main.java.repository.TemplateRepository;
import main.java.repository.implement.TemplateRepositoryImp;
import main.java.service.TemplateService;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateServiceImpl() {
        this.templateRepository = new TemplateRepositoryImp();
    }

    @Override
    public List<Template> getAllTemplates() {
        return templateRepository.getAllTemplates();
    }

    @Override
    public Template getTemplateById(int id) {
        return templateRepository.getTemplateById(id);
    }

    @Override
    public void createTemplate(Template template) {
        if (template.getTemplateTitle() == null || template.getTemplateTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        templateRepository.createTemplate(template);
    }

    @Override
    public void updateTemplate(Template template) {
        templateRepository.updateTemplate(template);
    }

    @Override
    public void deleteTemplate(int id) {
        templateRepository.deleteTemplate(id);
    }

    @Override
    public void incrementViewCount(int templateId) {
        templateRepository.incrementViewCount(templateId);
    }

    @Override
    public void incrementSaveCount(int templateId) {
        templateRepository.incrementSaveCount(templateId);
    }
}

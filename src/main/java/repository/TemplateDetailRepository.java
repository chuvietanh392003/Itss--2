package main.java.repository;

import main.java.model.Template; // Lớp Template
import main.java.model.TemplateDetail; // Lớp TemplateDetail

import java.util.List;

public interface TemplateDetailRepository {

    List<TemplateDetail> getTemplateDetails();
    String getTemplateTextByTemplate(Template template);
    String getTemplateSetsumeiByTemplate(Template template);
}

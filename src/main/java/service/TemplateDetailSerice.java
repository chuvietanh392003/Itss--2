package main.java.service;

import java.util.List;

import main.java.model.Template;
import main.java.model.TemplateDetail;

public interface TemplateDetailSerice {
    List<TemplateDetail> getTemplateDetails();
    String getTemplateTextByTemplate(Template template);
    String getTemplateSetsumeiByTemplate(Template template);
    void createTemplateDetail(int id, String title, String description, String templateText, String setsumeiText);
}

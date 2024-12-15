package main.java.model;

public class TemplateDetail {
    private String templateText;
    private String templateSetsumei;

    // Getter và Setter
    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTemplateSetsumei() {
        return templateSetsumei;
    }

    public void setTemplateSetsumei(String templateSetsumei) {
        this.templateSetsumei = templateSetsumei;
    }

    @Override
    public String toString() {
        return "TemplateDetail{" +
                "templateText='" + templateText + '\'' +
                ", templateSetsumei='" + templateSetsumei + '\'' +
                '}';
    }
}

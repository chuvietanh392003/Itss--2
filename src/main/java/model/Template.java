package main.java.model;

public class Template {
    private int templateId;       
    private String templateTitle;
    private String templateDes;
    private int viewCount;  
    private int saveCount;  

    // Constructor mới nhận templateTitle và templateDes
    public Template() {
    	
    }
    
    public Template(String templateTitle, String templateDescription) {
        this.templateTitle = templateTitle;
        this.templateDes = templateDescription;
        this.viewCount = 0;  // Mặc định viewCount là 0 khi tạo mới
        this.saveCount = 0;  // Mặc định saveCount là 0 khi tạo mới
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public String getTemplateDes() {
        return templateDes;
    }

    public void setTemplateDes(String templateDes) {
        this.templateDes = templateDes;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }
}

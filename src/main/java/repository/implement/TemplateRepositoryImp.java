package main.java.repository.implement;

import main.java.model.Template;
import main.java.repository.TemplateRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemplateRepositoryImp implements TemplateRepository {
    private final String url = "jdbc:mysql://localhost:3306/itss";
    private final String username = "root";
    private final String password = "chuvietanh";

    private static final Logger logger = Logger.getLogger(TemplateRepositoryImp.class.getName());

    @Override
    public List<Template> getAllTemplates() {
        List<Template> templates = new ArrayList<>();
        String query = "SELECT * FROM Template WHERE UserID IN (1, 2)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Template template = new Template();
                template.setTemplateId(rs.getInt("TemplateID"));
                template.setTemplateTitle(rs.getString("title"));
                template.setTemplateDes(rs.getString("description"));
                template.setViewCount(rs.getInt("view_count"));
                template.setSaveCount(rs.getInt("save_count"));
                templates.add(template);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching templates", e);
        }
        return templates;
    }

    @Override
    public List<Template> getAllTemplatesForUserId(int userId) {
        List<Template> templates = new ArrayList<>();
        String query = "SELECT * FROM Template WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);  // Set the userId parameter
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Template template = new Template();
                    template.setTemplateId(rs.getInt("TemplateID"));
                    template.setTemplateTitle(rs.getString("title"));
                    template.setTemplateDes(rs.getString("description"));
                    template.setViewCount(rs.getInt("view_count"));
                    template.setSaveCount(rs.getInt("save_count"));
                    templates.add(template);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching templates for user", e);
        }
        return templates;
    }

    @Override
    public Template getTemplateById(int id) {
        String query = "SELECT * FROM Template WHERE TemplateID = ?";
        Template template = null;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    template = new Template();
                    template.setTemplateId(rs.getInt("TemplateID"));
                    template.setTemplateTitle(rs.getString("title"));
                    template.setTemplateDes(rs.getString("description"));
                    template.setViewCount(rs.getInt("view_count"));
                    template.setSaveCount(rs.getInt("save_count"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching template by ID", e);
        }
        return template;
    }

    @Override
    public void createTemplate(Template template) {
        String query = "INSERT INTO Template (title, description, view_count, save_count, UserID) VALUES (?, ?, ?, ?, 1)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, template.getTemplateTitle());
            pstmt.setString(2, template.getTemplateDes());
            pstmt.setInt(3, template.getViewCount());
            pstmt.setInt(4, template.getSaveCount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating template", e);
        }
    }

    @Override
    public void updateTemplate(Template template) {
        String query = "UPDATE Template SET title = ?, description = ?, view_count = ?, save_count = ? WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, template.getTemplateTitle());
            pstmt.setString(2, template.getTemplateDes());
            pstmt.setInt(3, template.getViewCount());
            pstmt.setInt(4, template.getSaveCount());
            pstmt.setInt(5, template.getTemplateId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating template", e);
        }
    }

    @Override
    public void deleteTemplate(int id) {
        String query = "DELETE FROM Template WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting template", e);
        }
    }

    public void incrementViewCount(int templateId) {
        String query = "UPDATE Template SET view_count = view_count + 1 WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, templateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error incrementing view count", e);
        }
    }

    public void incrementSaveCount(int templateId) {
        String query = "UPDATE Template SET save_count = save_count + 1 WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, templateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error incrementing save count", e);
        }
    }
}

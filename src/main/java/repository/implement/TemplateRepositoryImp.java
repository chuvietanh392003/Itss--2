package main.java.repository.implement;

import main.java.model.Template;
import main.java.repository.TemplateRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemplateRepositoryImp implements TemplateRepository {
    private final String url = "jdbc:mysql://localhost:3306/itss";
    private final String username = "root";
    private final String password = "chuvietanh";

    @Override
    public List<Template> getAllTemplates() {
        List<Template> templates = new ArrayList<>();
        String query = "SELECT * FROM Template";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Template template = new Template();
                template.setTemplateId(rs.getInt("TemplateID"));
                template.setTemplateTitle(rs.getString("title"));
                template.setTemplateDes(rs.getString("description"));
                template.setViewCount(rs.getInt("view_count"));  // Sửa lại tên cột
                template.setSaveCount(rs.getInt("save_count"));  // Sửa lại tên cột
                templates.add(template);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                    template.setViewCount(rs.getInt("view_count"));  // Sửa lại tên cột
                    template.setSaveCount(rs.getInt("save_count"));  // Sửa lại tên cột
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return template;
    }

    @Override
    public void createTemplate(Template template) {
        String query = "INSERT INTO Template (title, description, view_count, save_count) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, template.getTemplateTitle());
            pstmt.setString(2, template.getTemplateDes());
            pstmt.setInt(3, template.getViewCount());  // Sửa lại tên cột
            pstmt.setInt(4, template.getSaveCount());  // Sửa lại tên cột
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTemplate(Template template) {
        String query = "UPDATE Template SET title = ?, description = ?, view_count = ?, save_count = ? WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set các tham số vào câu lệnh UPDATE
            pstmt.setString(1, template.getTemplateTitle());
            pstmt.setString(2, template.getTemplateDes());
            pstmt.setInt(3, template.getViewCount());
            pstmt.setInt(4, template.getSaveCount());
            pstmt.setInt(5, template.getTemplateId());

            // Thực hiện cập nhật
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    // Các phương thức mới thêm vào để tăng giá trị viewCount và saveCount:
    public void incrementViewCount(int templateId) {
        String query = "UPDATE Template SET view_count = view_count + 1 WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, templateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementSaveCount(int templateId) {
        String query = "UPDATE Template SET save_count = save_count + 1 WHERE TemplateID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, templateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

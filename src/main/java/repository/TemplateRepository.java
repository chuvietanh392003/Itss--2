package main.java.repository;

import main.java.model.Template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemplateRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/itss";
    private static final String USER = "root"; 
    private static final String PASSWORD = "chuvietanh"; 

    // 1. Kết nối đến cơ sở dữ liệu
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 2. Thêm một template mới vào cơ sở dữ liệu với giá trị mặc định cho favorite và tag
    public void addTemplate(Template template) {
        String query = "INSERT INTO Template (title, description, favorite, tag) VALUES (?, ?, FALSE, '')";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, template.getTemplateTitle());
            statement.setString(2, template.getTemplateDes());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Lấy tất cả các template từ cơ sở dữ liệu
    public List<Template> getAllTemplates() {
        List<Template> templates = new ArrayList<>();
        String query = "SELECT * FROM Template";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Template template = new Template();
                template.setTemplateId(resultSet.getInt("TemplateID")); 
                template.setTemplateTitle(resultSet.getString("title"));
                template.setTemplateDes(resultSet.getString("description"));
                templates.add(template);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templates;
    }

    // 4. Tìm kiếm template theo title hoặc tag
    public List<Template> searchTemplates(String keyword) {
        List<Template> templates = new ArrayList<>();
        String query = "SELECT * FROM Template WHERE title LIKE ? OR tag LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Template template = new Template();
                    template.setTemplateId(resultSet.getInt("TemplateID"));
                    template.setTemplateTitle(resultSet.getString("title"));
                    template.setTemplateDes(resultSet.getString("description"));
                    templates.add(template);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templates;
    }
}

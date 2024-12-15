package main.java.repository.implement;

import main.java.model.Template; // Lớp chứa thông tin của Template
import main.java.model.TemplateDetail; // Lớp chứa thông tin của TemplateDetail
import main.java.repository.TemplateDetailRepository; // Interface

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemplateDetailRepositoryImp implements TemplateDetailRepository {
    private final String url = "jdbc:mysql://localhost:3306/itss";
    private final String username = "root";
    private final String password = "chuvietanh";

    @Override
    public List<TemplateDetail> getTemplateDetails() {
        List<TemplateDetail> templateDetails = new ArrayList<>();
        String query = "SELECT template_text, template_setsumei FROM TemplateDetail";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TemplateDetail templateDetail = new TemplateDetail();
                templateDetail.setTemplateText(rs.getString("template_text"));
                templateDetail.setTemplateSetsumei(rs.getString("template_setsumei"));
                templateDetails.add(templateDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templateDetails;
    }

    // Phương thức lấy template_text theo Template (sử dụng TemplateID từ đối tượng Template)
    public String getTemplateTextByTemplate(Template template) {
        String query = "SELECT template_text FROM TemplateDetail WHERE TemplateID = ?";
        String templateText = null;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, template.getTemplateId()); // Lấy TemplateID từ đối tượng Template
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    templateText = rs.getString("template_text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templateText;
    }

    // Phương thức lấy template_setsumei theo Template (sử dụng TemplateID từ đối tượng Template)
    public String getTemplateSetsumeiByTemplate(Template template) {
        String query = "SELECT template_setsumei FROM TemplateDetail WHERE TemplateID = ?";
        String templateSetsumei = null;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, template.getTemplateId()); // Lấy TemplateID từ đối tượng Template
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    templateSetsumei = rs.getString("template_setsumei");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templateSetsumei;
    }
}

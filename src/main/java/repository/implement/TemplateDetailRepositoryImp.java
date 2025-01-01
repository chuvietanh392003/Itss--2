package main.java.repository.implement;

import main.java.model.Template; // Lớp chứa thông tin của Template
import main.java.model.TemplateDetail; // Lớp chứa thông tin của TemplateDetail
import main.java.repository.TemplateDetailRepository; // Interface

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
    
    public void createTemplateDetail(int id, String title, String description, String templateText, String setsumeiText) {
        // Câu lệnh INSERT vào bảng Template và TemplateDetail
        String insertTemplateQuery = "INSERT INTO Template (title, description, view_count, save_count, UserID) VALUES (?, ?, ?, ?, ?)";
        String insertTemplateDetailQuery = "INSERT INTO TemplateDetail (TemplateID, template_text, template_setsumei) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Bắt đầu transaction
            conn.setAutoCommit(false);

            // Chèn vào bảng Template để lấy TemplateID
            try (PreparedStatement psTemplate = conn.prepareStatement(insertTemplateQuery, Statement.RETURN_GENERATED_KEYS)) {
                psTemplate.setString(1, title);
                psTemplate.setString(2, description);
                psTemplate.setInt(3, 0);  // view_count
                psTemplate.setInt(4, 0);  // save_count
                psTemplate.setInt(5, id);
                psTemplate.executeUpdate();

                // Lấy TemplateID từ bảng Template
                int templateId = 0;
                try (ResultSet rs = psTemplate.getGeneratedKeys()) {
                    if (rs.next()) {
                        templateId = rs.getInt(1);  // Lấy TemplateID
                    }
                }

                // Chèn vào bảng TemplateDetail với TemplateID vừa lấy
                try (PreparedStatement psDetail = conn.prepareStatement(insertTemplateDetailQuery)) {
                    psDetail.setInt(1, templateId);  // Gán TemplateID
                    psDetail.setString(2, templateText);  // Gán templateText
                    psDetail.setString(3, setsumeiText);  // Gán templateSetsumei
                    psDetail.executeUpdate();  // Thực thi câu lệnh INSERT vào TemplateDetail
                }

                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();  // Nếu có lỗi, rollback lại giao dịch
                throw e;  // Ném lại lỗi để xử lý ở nơi khác
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

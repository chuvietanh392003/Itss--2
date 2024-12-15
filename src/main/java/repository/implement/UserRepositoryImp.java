package main.java.repository.implement;

import main.java.model.User;
import main.java.repository.UserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/itss";
    private final String dbUser = "root";
    private final String dbPassword = "chuvietanh";

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("is_admin");
                String createAt = resultSet.getString("created_at");
                String gmail = resultSet.getString("email");

                users.add(new User(id, username, password, isAdmin, createAt, gmail));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void deleteUser(String username) {
        String query = "DELETE FROM user WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isAdmin(String username) {
        String query = "SELECT is_admin FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(String username, String email, String password, boolean isAdmin) {
        String sql = "INSERT INTO user (username, email, password, is_admin) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBoolean(4, isAdmin);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public void changePassword(String gmail, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Gán giá trị vào câu truy vấn
            statement.setString(1, newPassword); // Mật khẩu mới
            statement.setString(2, gmail);       // Email để xác định người dùng

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("No user found with the specified email.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getUserByGmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Gán giá trị email vào câu truy vấn
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Nếu tìm thấy kết quả, tạo đối tượng User
                if (resultSet.next()) {
                    int id = resultSet.getInt("UserID");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    boolean isAdmin = resultSet.getBoolean("is_admin");
                    String createdAt = resultSet.getString("created_at");

                    user = new User(id, username, password, isAdmin, createdAt, email);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Gán giá trị username vào câu truy vấn
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("UserID");
                    String fetchedUsername = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    boolean isAdmin = resultSet.getBoolean("is_admin");
                    String createdAt = resultSet.getString("created_at");
                    String email = resultSet.getString("email");

                    user = new User(id, fetchedUsername, password, isAdmin, createdAt, email);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    @Override
    public void changeGmail(String username, String newGmail) {
        String sql = "UPDATE user SET email = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            
            statement.setString(1, newGmail);  
            statement.setString(2, username);  

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Email updated successfully.");
            } else {
                System.out.println("No user found with the specified username.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

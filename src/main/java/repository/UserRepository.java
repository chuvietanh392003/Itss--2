package main.java.repository;

import main.java.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/itss";
    private final String dbUser = "root";
    private final String dbPassword = "chuvietanh";

    // Get all users
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

    // Delete user by username
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
    
    public boolean isAdmin(String username) {
        String query = "SELECT is_admin FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query)) {

        	statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_admin"); // Nếu là admin, trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
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
}

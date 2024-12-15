package main.java;

import main.java.service.implement.UserServiceImp; 
import main.java.service.UserService;
import main.java.model.User;

import java.util.List;

public class TestUserRepo {

    public static void main(String[] args) {
        UserService userService = new UserServiceImp();

        // Lấy danh sách người dùng từ Service
        List<User> users = userService.getAllUsers();

        // In danh sách người dùng ra console
        if (users.isEmpty()) {
            System.out.println("Không có người dùng nào trong cơ sở dữ liệu.");
        } else {
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getGmail());
                System.out.println("Created At: " + user.getCreateAt());
                System.out.println("Admin: " + (user.isAdmin() ? "Yes" : "No"));
                System.out.println("----------------------------");
            }
        }
    }
}

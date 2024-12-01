package main.java;

import main.java.repository.UserRepository;
import main.java.model.User;

import java.util.List;

public class TestUserRepo {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getAllUsers();

        // In danh sách người dùng ra console
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

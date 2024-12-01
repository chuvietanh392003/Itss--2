package main.java;

import java.util.List;
import main.java.model.User; // Đảm bảo bạn đã import đúng lớp User
import main.java.repository.UserRepository; 
public class Test {
    public static void main(String[] args) {
        // Tạo đối tượng UserRepo (giả sử có constructor mặc định)
        UserRepository userRepo = new UserRepository();

        // Lấy danh sách tất cả người dùng từ repository
        List<User> userList = userRepo.getAllUsers(); 


        if (userList != null && !userList.isEmpty()) {
            for (User user : userList) {
                System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }
        } else {
            System.out.println("No users found in the repository.");
        }
    }
}

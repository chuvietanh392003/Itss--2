package main.java.repository;

import main.java.model.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    void deleteUser(String username);
    void changePassword(String gmail, String newPassword);
    void changeGmail(String username, String newGmail);
    boolean checkCredentials(String username, String password);
    boolean isAdmin(String username);
    boolean addUser(String username, String email, String password, boolean isAdmin);
    User getUserByGmail(String email);
	User getUserByUsername(String username);
}

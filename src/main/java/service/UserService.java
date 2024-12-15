package main.java.service;

import main.java.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUser(String username);
    boolean checkCredentials(String username, String password);
    boolean isAdmin(String username);
    boolean addUser(String username, String email, String password, boolean isAdmin);
    void changePassword(String gmail, String newPassword);
    void changeGmail(String username, String newGmail);
    User getUserByGmail(String gmail);
    User getUserByUsername(String username);
}

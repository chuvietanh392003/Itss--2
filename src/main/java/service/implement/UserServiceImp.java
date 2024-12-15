package main.java.service.implement;

import main.java.model.User;
import main.java.repository.UserRepository;
import main.java.repository.implement.UserRepositoryImp;
import main.java.service.UserService;

import java.util.List;

public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp() {
        this.userRepository = new UserRepositoryImp();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return userRepository.checkCredentials(username, password);
    }

    @Override
    public boolean isAdmin(String username) {
        return userRepository.isAdmin(username);
    }

    @Override
    public boolean addUser(String username, String email, String password, boolean isAdmin) {
        return userRepository.addUser(username, email, password, isAdmin);
    }
    
    @Override
    public void changePassword(String gmail, String newPassword) {
    	userRepository.changePassword(gmail, newPassword);
    }
    
    @Override
    public void changeGmail(String username, String newGmail) {
    	userRepository.changeGmail(username, newGmail); 
    }
    
    @Override
    public User getUserByGmail(String gmail) {
    	return userRepository.getUserByGmail(gmail);
    }
    
    @Override
    public User getUserByUsername(String username) {
    	return userRepository.getUserByUsername(username);
    }
}

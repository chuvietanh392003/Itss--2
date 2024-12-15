package main.java.session;

import main.java.model.User;
import main.java.service.UserService;

public class SessionManager {

    private static SessionManager instance;
    private User currentUser;
    private UserService currentUserService;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUserService(UserService userService) {
        this.currentUserService = userService;
    }

    public UserService getCurrentUserService() {
        return this.currentUserService;
    }
}

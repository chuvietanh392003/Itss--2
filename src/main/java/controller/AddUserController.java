package main.java.controller;

import main.java.service.UserService;
import main.java.view.AddUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserController {
    private AddUserView view;
    private UserService userService;

    public AddUserController(AddUserView view, UserService userService) {
        this.view = view;
        this.userService = userService;

        // Gắn sự kiện cho các nút
        this.view.addAddUserListener(new AddUserListener());
        this.view.addCancelListener(e -> view.close());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String email = view.getEmail();
            String password = view.getPassword();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                view.showErrorMessage("All fields are required!");
            } else {
                userService.addUser(username,email, password, false );
                view.close();
            }
        }
    }
}

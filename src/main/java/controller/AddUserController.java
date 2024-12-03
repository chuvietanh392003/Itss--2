package main.java.controller;

import main.java.model.User;
import main.java.repository.UserRepository;
import main.java.view.AddUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserController {
    private AddUserView view;
    private UserRepository userRepository;

    public AddUserController(AddUserView view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;

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
                User newUser = new User(username, email, password, false);
                userRepository.addUser(username, email, password, false);
                view.close();
            }
        }
    }
}

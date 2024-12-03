package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddUserView extends JFrame {
	
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton addButton;
    private JButton cancelButton;

    public AddUserView() {
        setTitle("Add New User");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo các thành phần giao diện
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        // Thêm các thành phần vào giao diện
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(addButton);
        add(cancelButton);

        setLocationRelativeTo(null); // Center window
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public void addAddUserListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void close() {
        this.dispose();
    }
}

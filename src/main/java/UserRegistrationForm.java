package main.java;


import javax.swing.*;

public class UserRegistrationForm {
    public static void main(String[] args) {
        // Gọi hàm đăng ký
        registerUser();
    }

    public static void registerUser() {
        // Tạo một JPanel để chứa các trường nhập liệu
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 


        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);    // Ô nhập cho email
        JPasswordField passwordField = new JPasswordField(20); // Ô nhập cho password

        // Thêm các label và trường nhập vào panel
        panel.add(new JLabel("Enter Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Enter Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Enter Password:"));
        panel.add(passwordField);

        // Hiển thị hộp thoại đăng ký
        int option = JOptionPane.showConfirmDialog(null, panel, "User Registration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Nếu người dùng nhấn OK
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim(); // Lấy dữ liệu từ trường username
            String email = emailField.getText().trim();       // Lấy dữ liệu từ trường email
            String password = new String(passwordField.getPassword()).trim(); // Lấy dữ liệu từ trường password

            // Kiểm tra nếu tất cả các trường không bị bỏ trống
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Đăng ký thành công
                JOptionPane.showMessageDialog(null, "User Registered Successfully!\nUsername: " + username + "\nEmail: " + email, "Success", JOptionPane.INFORMATION_MESSAGE);
                // Có thể lưu thông tin người dùng vào cơ sở dữ liệu hoặc danh sách nếu cần
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registration Cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

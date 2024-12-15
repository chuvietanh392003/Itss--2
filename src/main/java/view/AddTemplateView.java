package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddTemplateView extends JFrame {

    private JTextField titleField;
    private JTextField descriptionField;
    private JButton addButton;
    private JButton cancelButton;

    public AddTemplateView() {
        setTitle("Add New Template");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo các thành phần giao diện
        titleField = new JTextField(20);
        descriptionField = new JTextField(20);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        // Thêm các thành phần vào giao diện
        add(new JLabel("Template Title:"));
        add(titleField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(addButton);
        add(cancelButton);

        setLocationRelativeTo(null); // Center window
    }

    // Lấy thông tin từ các trường nhập liệu
    public String getTemplateTitle() {
        return titleField.getText().trim();
    }

    public String getTemplateDescription() {
        return descriptionField.getText().trim();
    }

    // Thêm listener cho các nút
    public void addAddTemplateListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    // Hiển thị thông báo lỗi
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Đóng cửa sổ
    public void close() {
        this.dispose();
    }
}

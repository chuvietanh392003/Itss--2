package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditTemplateView extends JFrame {

    private JTextField templateTitleField;
    private JTextField templateDescriptionField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditTemplateView(String currentTitle, String currentDescription) {
        setTitle("Edit Template");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo các thành phần giao diện
        templateTitleField = new JTextField(currentTitle, 20);
        templateDescriptionField = new JTextField(currentDescription, 20);
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        // Thêm các thành phần vào giao diện
        add(new JLabel("Template Title:"));
        add(templateTitleField);
        add(new JLabel("Template Description:"));
        add(templateDescriptionField);
        add(saveButton);
        add(cancelButton);

        setLocationRelativeTo(null); // Center window
    }

    public String getTemplateTitle() {
        return templateTitleField.getText().trim();
    }

    public String getTemplateDescription() {
        return templateDescriptionField.getText().trim();
    }
    
    public void setTemplateTitle(String title) {
        templateTitleField.setText(title);
    }

    public void setTemplateDescription(String description) {
        templateDescriptionField.setText(description);
    }

    
    public void addSaveTemplateListener(ActionListener listener) {
        saveButton.addActionListener(listener);
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

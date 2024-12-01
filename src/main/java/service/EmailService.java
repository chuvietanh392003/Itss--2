package main.java.service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private final String username = "your_email@gmail.com"; // Địa chỉ email của bạn
    private final String password = "your_email_password"; // Mật khẩu của bạn

    // Phương thức gửi email
    public void sendEmail(String toEmail, String subject, String body) {
        // Cấu hình thuộc tính của email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Bật STARTTLS

        // Tạo phiên làm việc với email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Địa chỉ email người gửi
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Địa chỉ email người nhận
            message.setSubject(subject); // Tiêu đề email
            message.setText(body); // Nội dung email

            // Gửi email
            Transport.send(message);

            System.out.println("Email đã được gửi thành công.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

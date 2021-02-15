package com.vvicee.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.vvicee.constant.servlet.MailConstant.*;

public class MailService {

    public void sendMessage(String emailTo, String subject, String text) {
        Session session = initSession();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Session initSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", EMAIL_PORT);
        properties.put("mail.from", EMAIL_USERNAME);
        properties.put("mail.smtp.password", EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                    }
                });
    }
}

package ua.com.alevel.service.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String DEFAULT_FROM_EMAIL = "company@gobus.com";

    private final JavaMailSender javaMailSender;

    @Override
    public void sendNotification(EmailParameters emailParameters) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (emailParameters.getFrom() != null) {
            message.setFrom(emailParameters.getFrom());
        } else {
            message.setFrom(DEFAULT_FROM_EMAIL);
        }
        message.setTo(emailParameters.getTo());
        message.setSubject(emailParameters.getSubject());
        message.setText(emailParameters.getMessage());

        javaMailSender.send(message);
    }
}

package work.szczepanskimichal.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements Messenger {

    private final JavaMailSender emailSender;

    @Override
    public void sendMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("presentday@work.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
            log.info(String.format("Successfully sent email with subject: %s, to: %s", subject, to));
        } catch (MessagingException e) {
            //todo create custom exception
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
package work.szczepanskimichal.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
@DependsOn("smtpUtil")
public class EmailConfiguration {

    private final SmtpUtil smtpUtil;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpUtil.getHost());
        mailSender.setPort(smtpUtil.getPort());
        mailSender.setUsername(smtpUtil.getUsername());
        mailSender.setPassword(System.getenv("SPRING_MAIL_PASSWORD"));
        return mailSender;
    }

}

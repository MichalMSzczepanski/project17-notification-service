package work.szczepanskimichal.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import work.szczepanskimichal.util.CredentialsUtil;
import work.szczepanskimichal.util.SmtpUtil;

@Configuration
@RequiredArgsConstructor
@DependsOn("smtpUtil")
public class EmailConfiguration {

    private final SmtpUtil smtpUtil;
    private final CredentialsUtil credentialsUtil;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpUtil.getHost());
        mailSender.setPort(smtpUtil.getPort());
        mailSender.setUsername(smtpUtil.getUsername());
        mailSender.setPassword(credentialsUtil.getPassword());
        return mailSender;
    }

}

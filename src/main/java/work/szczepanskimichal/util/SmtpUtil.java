package work.szczepanskimichal.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("spring.mail")
@Component
@Getter
@Setter
public class SmtpUtil {
    private String host;
    private int port;
    private String username;
}

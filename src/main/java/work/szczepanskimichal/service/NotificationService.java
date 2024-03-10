package work.szczepanskimichal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.szczepanskimichal.exception.MissingMessageParameterException;
import work.szczepanskimichal.model.Notification;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MessageBuilder messageBuilder;
    private final EmailService emailService;

    public void processNotification(Notification notification) {
        var to = notification.getAddressee();
        var subject = notification.getSubject();
        var messageParameters = notification.getMessageParameters();
        if (to == null || subject == null) {
            throw new MissingMessageParameterException(String.format("subject: %s, to: %s", subject, to));
        }

        var message = messageBuilder.buildMessage(subject, messageParameters);

        switch (notification.getType()) {
            case EMAIL -> emailService.sendMessage(to, subject.getMessage(), message);
            default -> emailService.sendMessage(to, subject.toString(), message);
        }
    }

}

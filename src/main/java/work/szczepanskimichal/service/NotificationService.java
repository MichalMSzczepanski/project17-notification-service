package work.szczepanskimichal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        //todo message params can be null, fix
        if (to == null || subject == null || messageParameters == null) {
            //todo create custom exception
            throw new IllegalArgumentException("Notification properties cannot be null");
        }

        var message = messageBuilder.buildMessage(subject, messageParameters);

        switch (notification.getType()) {
            case EMAIL -> emailService.sendMessage(to, subject.getMessage(), message);
            default -> emailService.sendMessage(to, subject.toString(), message);
        }
    }

}

package work.szczepanskimichal.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.exception.KafkaProcessingException;
import work.szczepanskimichal.exception.MessageException;
import work.szczepanskimichal.exception.NotificationProcessingException;
import work.szczepanskimichal.model.Notification;
import work.szczepanskimichal.service.NotificationService;

@Component
@RequiredArgsConstructor
public class UserTopicListener {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "USER_TOPIC", groupId = "notification-consumers")
    public void receiveMessage(String message) {
        if (message == null) {
            throw new MessageException("USER_TOPIC");
        }
        try {
            var notification = objectMapper.readValue(message, Notification.class);
            notificationService.processNotification(notification);
        } catch (JsonProcessingException e) {
            throw new NotificationProcessingException(message);
        } catch (RuntimeException e) {
            throw new KafkaProcessingException(message);
        }
    }
}

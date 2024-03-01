package work.szczepanskimichal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.model.Notification;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "NOTIFICATION_TOPIC", groupId = "notification-consumers")
    public void receiveMessage(String message) {
        try {
            var notification = objectMapper.readValue(message, Notification.class);
            notificationService.processNotification(notification);
        } catch (JsonProcessingException e) {
            //todo create custom exception
            System.err.println("Error deserializing JSON message: " + e.getMessage());
        } catch (RuntimeException e) {
            //todo create custom exception
            System.err.println("Error processing Kafka message: " + e.getMessage());
        }
    }
}

package work.szczepanskimichal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.model.Notification;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "NOTIFICATION_TOPIC", groupId = "my-group")
    public void receiveMessage(String message) {
        try {
            // Deserialize the received message to Email object
            Notification notification = objectMapper.readValue(message, Notification.class);

            // Process the received Email object
            // You can implement your logic here to handle the received email notification
            System.out.println("Received email notification: " + notification);
        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + e.getMessage());
        }
    }
}

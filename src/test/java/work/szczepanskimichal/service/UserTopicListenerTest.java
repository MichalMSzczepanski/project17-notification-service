package work.szczepanskimichal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import work.szczepanskimichal.exception.NotificationProcessingException;
import work.szczepanskimichal.listener.UserTopicListener;
import work.szczepanskimichal.model.Notification;
import work.szczepanskimichal.model.NotificationSubject;
import work.szczepanskimichal.model.NotificationType;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserTopicListenerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private UserTopicListener notificationListener;

    @Test
    void testReceiveMessageSuccess() {
        String messageContent = "{\"addressee\":\"test@example.com\",\"type\":\"EMAIL\"," +
                "\"subject\":\"USER_ACTIVATION\",\"messageParameters\":null}";

        Notification expectedNotification = Notification.builder()
                .addressee("test@example.com")
                .type(NotificationType.EMAIL)
                .subject(NotificationSubject.USER_ACTIVATION)
                .messageParameters(null)
                .build();

        notificationListener.receiveMessage(messageContent);

        verify(notificationService).processNotification(expectedNotification);
    }

    @Test
    void testReceiveMessageJsonProcessingException() {
        String message = "invalidJson";

        assertThrows(NotificationProcessingException.class, () -> notificationListener.receiveMessage(message));
    }

}
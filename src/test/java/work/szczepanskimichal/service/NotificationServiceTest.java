package work.szczepanskimichal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import work.szczepanskimichal.exception.MissingMessageParameterException;
import work.szczepanskimichal.model.Notification;
import work.szczepanskimichal.model.NotificationSubject;
import work.szczepanskimichal.model.NotificationType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private MessageBuilder messageBuilder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void testProcessNotificationWithEmailType() {
        var notification = Notification.builder()
                .type(NotificationType.EMAIL)
                .addressee("test@test.com")
                .subject(NotificationSubject.USER_ACTIVATION)
                .messageParameters(null)
                .build();

        when(messageBuilder.buildMessage(any(), any())).thenReturn("Test Message");

        notificationService.processNotification(notification);

        verify(emailService).sendMessage(anyString(), anyString(), anyString());
    }

    @Test
    void testProcessNotificationWithMissingAddressee() {
        var notification = Notification.builder()
                .type(NotificationType.EMAIL)
                .addressee(null)
                .subject(NotificationSubject.USER_ACTIVATION)
                .build();

        assertThrows(MissingMessageParameterException.class,
                () -> notificationService.processNotification(notification));
    }

    @Test
    void testProcessNotificationWithMissingSubject() {
        var notification = Notification.builder()
                .type(NotificationType.EMAIL)
                .addressee("test@test.com")
                .subject(null)
                .build();

        assertThrows(MissingMessageParameterException.class,
                () -> notificationService.processNotification(notification));
    }

}
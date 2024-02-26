package work.szczepanskimichal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Notification implements Serializable {

    private String addressee;
    private NotificationType type;
    private NotificationSubject subject;
    private Map<String, String> messageParameters;
}

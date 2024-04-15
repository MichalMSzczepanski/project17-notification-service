package work.szczepanskimichal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Notification implements Serializable {

    private String addressee;
    private NotificationType type;
    private NotificationSubject subject;
    private Map<String, String> messageParameters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(addressee, that.addressee) &&
                type == that.type &&
                subject == that.subject &&
                Objects.equals(messageParameters, that.messageParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressee, type, subject, messageParameters);
    }

}

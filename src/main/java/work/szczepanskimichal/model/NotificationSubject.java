package work.szczepanskimichal.model;

import lombok.Getter;

@Getter
public enum NotificationSubject {

    USER_ACTIVATION("User activation required"),
    USER_DEACTIVATION("User deactivated"),
    USER_ACTIVATION_CONFIRMATION("User successfully activated"),
    USER_DATA_UPDATE("User data updated"),
    USER_PASSWORD_UPDATE("User password update requested"),
    USER_PASSWORD_UPDATED("User password updated"),
    REMINDER_TRIGGERING("This is a reminder.");

    private final String message;

    NotificationSubject(String message) {
        this.message = message;
    }

}

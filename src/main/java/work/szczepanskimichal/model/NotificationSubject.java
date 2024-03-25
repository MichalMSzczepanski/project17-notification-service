package work.szczepanskimichal.model;

import lombok.Getter;

@Getter
public enum NotificationSubject {

    USER_ACTIVATION("User activation required"),
    USER_ACTIVATION_CONFIRMATION("User successfully activated "),
    USER_EMAIL_UPDATE("User email updated"),
    USER_PASSWORD_UPDATE("User password update requested"),
    USER_PASSWORD_UPDATED("User password updated");

    private final String message;

    NotificationSubject(String message) {
        this.message = message;
    }

}

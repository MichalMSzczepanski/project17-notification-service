package work.szczepanskimichal.model;

public enum NotificationSubject {

    USER_ACTIVATION("User activation required"),
    USER_ACTIVATION_CONFIRMATION("User successfully activated "),
    USER_EMAIL_UPDATE("User email updated"),
    USER_PASSWORD_UPDATE("User password updated");

    private final String message;

    NotificationSubject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

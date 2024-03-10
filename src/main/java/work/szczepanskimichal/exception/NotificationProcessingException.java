package work.szczepanskimichal.exception;

public class NotificationProcessingException extends RuntimeException {

    public NotificationProcessingException(String message) {
        super(String.format("Encountered issue with json while processing notification: %s", message));
    }
}
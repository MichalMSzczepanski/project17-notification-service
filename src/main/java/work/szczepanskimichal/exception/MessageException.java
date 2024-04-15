package work.szczepanskimichal.exception;

public class MessageException extends RuntimeException {

    public MessageException(String topic) {
        super(String.format("Message missing in notification in topic '%s'", topic));
    }
}
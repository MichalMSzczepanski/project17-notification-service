package work.szczepanskimichal.exception;

public class MessageParameterMapException extends RuntimeException {

    public MessageParameterMapException(String subject) {
        super(String.format("Message parameters for message: %s cannot be null", subject));
    }
}
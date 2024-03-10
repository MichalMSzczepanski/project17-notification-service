package work.szczepanskimichal.exception;

public class MessageSubjectException extends RuntimeException {

    public MessageSubjectException(String subject) {
        super(String.format("Message subject not anticipated: %s", subject));
    }
}
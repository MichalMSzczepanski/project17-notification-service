package work.szczepanskimichal.exception;

public class MissingMessageParameterException extends RuntimeException {

    public MissingMessageParameterException(String details) {
        super(String.format("Missing parameter for message. Details: %s", details));
    }
}
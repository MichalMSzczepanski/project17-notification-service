package work.szczepanskimichal.exception;

public class EmailException extends RuntimeException {

    public EmailException(String email, String subject) {
        super(String.format("Encountered issue when sending email to: %s, on topic: %s", email, subject));
    }
}
package work.szczepanskimichal.exception;

public class KafkaProcessingException extends RuntimeException {

    public KafkaProcessingException(String message) {
        super(String.format("Encountered issue with kafka while processing notification: %s", message));
    }
}
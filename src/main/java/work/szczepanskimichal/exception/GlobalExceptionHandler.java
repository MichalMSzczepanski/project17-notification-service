package work.szczepanskimichal.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<String> handleEmailException(EmailException e) {
        log.warn(String.format("Email exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KafkaProcessingException.class)
    public ResponseEntity<String> handleKafkaProcessingException(EmailException e) {
        log.warn(String.format("Kafka exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageParameterMapException.class)
    public ResponseEntity<String> handleMessageParameterMapException(MessageParameterMapException e) {
        log.warn(String.format("Message parameter map exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<String> handleMessageException(MessageException e) {
        log.warn(String.format("Message exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageSubjectException.class)
    public ResponseEntity<String> handleMessageSubjectException(MessageSubjectException e) {
        log.warn(String.format("Message subject exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingMessageParameterException.class)
    public ResponseEntity<String> handleMissingMessageParameterException(MissingMessageParameterException e) {
        log.warn(String.format("Message parameters missing exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotificationProcessingException.class)
    public ResponseEntity<String> handleNotificationProcessingException(NotificationProcessingException e) {
        log.warn(String.format("Notification processing exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
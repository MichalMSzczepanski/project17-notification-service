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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEmailException(EmailException e) {
        log.warn(String.format("Email exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KafkaProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleKafkaProcessingException(EmailException e) {
        log.warn(String.format("Kafka exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageParameterMapException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMessageParameterMapException(MessageParameterMapException e) {
        log.warn(String.format("Message parameter map exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageSubjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMessageSubjectException(MessageSubjectException e) {
        log.warn(String.format("Message subject exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingMessageParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleMissingMessageParameterException(MissingMessageParameterException e) {
        log.warn(String.format("Message parameters missing exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotificationProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNotificationProcessingException(NotificationProcessingException e) {
        log.warn(String.format("Notification processing exception encountered: %s", e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
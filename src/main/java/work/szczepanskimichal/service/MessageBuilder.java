package work.szczepanskimichal.service;

import org.springframework.stereotype.Component;
import work.szczepanskimichal.model.NotificationSubject;

import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class MessageBuilder {

    public String buildMessage(NotificationSubject subject, Map<String, String> parameters) {
        switch (subject) {
            case USER_ACTIVATION:
                return buildActivationMessage(parameters);
            case USER_ACTIVATION_CONFIRMATION:
                return buildActivationConfirmationMessage();
            case USER_EMAIL_UPDATE:
                return buildEmailUpdateMessage();
            case USER_PASSWORD_UPDATE:
                return buildPasswordUpdateMessage(parameters);
            default:
                //todo create custom exception
                throw new IllegalArgumentException("Unknown subject: " + subject);
        }
    }

    private String buildActivationMessage(Map<String, String> parameters) {
        String userId;
        String secretKey;
        try {
            userId = parameters.get("userId");
            secretKey = parameters.get("secretKey");
        } catch (NoSuchElementException e) {
            //todo create custom exception
            throw new RuntimeException(e.getMessage());
        }
        return String.format("Activate your account at: http://localhost:8081/activate/%s/%s", userId, secretKey);
    }

    private String buildActivationConfirmationMessage() {
        return "Your account has been activated successfully.";
    }

    private String buildEmailUpdateMessage(
    ) {
        return "Your email has been updated successfully.";
    }

    private String buildPasswordUpdateMessage(Map<String, String> parameters) {
        String secretKey;
        try {
            secretKey = parameters.get("secretKey");
        } catch (NoSuchElementException e) {
            //todo create custom exception
            throw new RuntimeException(e.getMessage());
        }
        return String.format("[doesn't work without body] Set new password at: " +
                "http://localhost:8081/set-new-password/%s", secretKey);
    }
}
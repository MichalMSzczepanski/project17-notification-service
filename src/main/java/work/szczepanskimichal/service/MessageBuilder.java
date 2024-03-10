package work.szczepanskimichal.service;

import org.springframework.stereotype.Component;
import work.szczepanskimichal.exception.MessageParameterMapException;
import work.szczepanskimichal.exception.MissingMessageParameterException;
import work.szczepanskimichal.model.NotificationSubject;

import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class MessageBuilder {

    public String buildMessage(NotificationSubject subject, Map<String, String> parameters) {
        return switch (subject) {
            case USER_ACTIVATION -> buildActivationMessage(parameters);
            case USER_ACTIVATION_CONFIRMATION -> buildActivationConfirmationMessage();
            case USER_EMAIL_UPDATE -> buildEmailUpdateMessage();
            case USER_PASSWORD_UPDATE -> buildPasswordUpdateMessage(parameters);
        };
    }

    private String buildActivationMessage(Map<String, String> parameters) {
        if (parameters == null) {
            throw new MessageParameterMapException("activation message");
        }
        String userId;
        String secretKey;
        try {
            userId = parameters.get("userId");
            secretKey = parameters.get("secretKey");
        } catch (NoSuchElementException e) {
            throw new MissingMessageParameterException("secret key or userId in activation message");
        }
        return String.format("Activate your account at: http://localhost:8081/public/user/activate/%s/%s", userId,
                secretKey);
    }

    private String buildActivationConfirmationMessage() {
        return "Your account has been activated successfully.";
    }

    private String buildEmailUpdateMessage(
    ) {
        return "Your email has been updated successfully.";
    }

    private String buildPasswordUpdateMessage(Map<String, String> parameters) {
        if (parameters == null) {
            throw new MessageParameterMapException("build password update message");
        }
        String secretKey;
        try {
            secretKey = parameters.get("secretKey");
        } catch (NoSuchElementException e) {
            throw new MissingMessageParameterException("build password update message");
        }
        return String.format("[doesn't work without body] Set new password at: " +
                "http://localhost:8081/public/user/set-new-password/%s", secretKey);
    }
}
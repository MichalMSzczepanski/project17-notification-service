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
            case USER_PASSWORD_UPDATE -> buildPasswordUpdateRequestedMessage(parameters);
            case USER_PASSWORD_UPDATED -> buildPasswordUpdatedMessage();
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
        return String.format("Activate your account at: http://localhost:8080/public/user/activate/%s/%s", userId,
                secretKey);
    }

    private String buildActivationConfirmationMessage() {
        return "Your account has been activated successfully.";
    }

    private String buildEmailUpdateMessage(
    ) {
        return "Your email has been updated successfully.";
    }

    private String buildPasswordUpdateRequestedMessage(Map<String, String> parameters) {
        if (parameters == null) {
            throw new MessageParameterMapException("build password update requested message");
        }
        String secretKey;
        try {
            secretKey = parameters.get("secretKey");
        } catch (NoSuchElementException e) {
            throw new MissingMessageParameterException("build password update requested message");
        }
        return String.format("[doesn't work without body] Password update was requested - set new password at: " +
                "http://localhost:8080/public/user/set-new-password/%s", secretKey);
    }

    private String buildPasswordUpdatedMessage(
    ) {
        return "Your password has been updated successfully.";
    }
}
package fr.polytech.recognition.exception;

public class RegistrationError extends Error {
    public RegistrationError(String message) {
        super(message);
    }

    public RegistrationError(String message, Throwable cause) {
        super(message, cause);
    }
}

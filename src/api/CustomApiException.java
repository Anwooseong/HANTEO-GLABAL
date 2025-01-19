package api;

public class CustomApiException extends RuntimeException {
    private final String message;

    public CustomApiException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

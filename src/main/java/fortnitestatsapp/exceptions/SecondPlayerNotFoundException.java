package fortnitestatsapp.exceptions;

public class SecondPlayerNotFoundException extends PlayerNotFoundException {
    public SecondPlayerNotFoundException() {
    }

    public SecondPlayerNotFoundException(String message) {
        super(message);
    }

    public SecondPlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

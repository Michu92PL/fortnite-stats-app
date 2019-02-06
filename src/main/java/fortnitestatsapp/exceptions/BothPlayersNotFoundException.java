package fortnitestatsapp.exceptions;

public class BothPlayersNotFoundException extends PlayerNotFoundException {

    public BothPlayersNotFoundException() {
    }

    public BothPlayersNotFoundException(String message) {
        super(message);
    }

    public BothPlayersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

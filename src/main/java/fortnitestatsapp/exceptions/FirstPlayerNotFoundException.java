package fortnitestatsapp.exceptions;

public class FirstPlayerNotFoundException extends PlayerNotFoundException{

    public FirstPlayerNotFoundException() {
    }

    public FirstPlayerNotFoundException(String message) {
        super(message);
    }

    public FirstPlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

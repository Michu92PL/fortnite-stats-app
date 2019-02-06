package fortnitestatsapp.exceptions;

public class PlayerNotFoundException extends PlayerException{

    public PlayerNotFoundException(){
    }

    public PlayerNotFoundException(String message){
        super(message);
    }
    public PlayerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}

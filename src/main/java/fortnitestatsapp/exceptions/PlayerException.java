package fortnitestatsapp.exceptions;

public class PlayerException extends RuntimeException{

    public PlayerException(){
    }

    public PlayerException(String message){
        super(message);
    }

    public PlayerException(String message, Throwable cause){
        super(cause);
    }


}

package customexception;

public class InvalidStartWordException extends CustomException{
    public InvalidStartWordException(){
        super("start word doesn't exist");
    }

    
}

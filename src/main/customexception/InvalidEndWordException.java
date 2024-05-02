package customexception;

public class InvalidEndWordException extends CustomException{
    public InvalidEndWordException(){
        super("end word doesn't exist");
    }
    
}

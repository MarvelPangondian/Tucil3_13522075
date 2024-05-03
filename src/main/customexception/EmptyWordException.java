package customexception;

public class EmptyWordException extends CustomException{
    public EmptyWordException(){
        super("Start or end word cannot be empty.");
    }

}

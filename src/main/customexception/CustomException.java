package customexception;

public class CustomException extends Exception {
    public CustomException (){
        super("something went wrong");
    }
    public CustomException (String message) {
        super(message);
    }
}
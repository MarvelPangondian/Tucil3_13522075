package customexception;

public class InvalidLengthWord extends CustomException{
    public InvalidLengthWord(){
        super("Unequal lengths of words");
    }
}

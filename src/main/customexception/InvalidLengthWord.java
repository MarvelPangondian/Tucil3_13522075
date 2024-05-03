package customexception;

public class InvalidLengthWord extends CustomException{
    public InvalidLengthWord(String word1, String word2){
        super(word1 + " and " + word2 + " have Unequal lengths");
    }
}

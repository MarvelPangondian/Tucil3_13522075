import java.io.IOException;

import dictionary.*;
import customexception.*;
import algorithm.*;

public class Main {
    public static void main(String args[]){
        try {
            Dictionary dictionary = new Dictionary("src/main/dictionary/data.txt");
            UCS algorithm = new UCS();
            String[] hasil = algorithm.getUniformCostSearch("marvel", "marver", dictionary);
            System.out.println(hasil.length);
            for (String what : hasil){
                System.out.println(what);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CustomException e){
            System.out.println(e.getMessage());
        }
    }
    
}

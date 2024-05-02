import java.io.IOException;

import dictionary.*;
import customexception.*;
import algorithm.*;
import datastructure.*;

public class Main {
    public static void main(String args[]){
        try {
            Dictionary dictionary = new Dictionary("src/main/dictionary/data.txt");
            AStar algorithm = new AStar();
            System.out.println("Processing");
            Node hasil = algorithm.getAStar("cold", "warm", dictionary);
            System.out.println("Done !");
            System.out.println("Path : ");
            for (String what : hasil.getPath()){
                System.out.println(what);
            }
            System.out.println("Value : "+ hasil.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CustomException e){
            System.out.println(e.getMessage());
        }
    }
    
}

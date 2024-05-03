import java.io.IOException;

import java.util.Scanner;

import dictionary.*;
import util.StringUtil;
import cli.*;
import gui.*;
import prompt.*;

public class Main {
    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        Prompt prompt =  new Prompt(scanner);

        System.out.println("      _.--._  _.--._\r\n" + //
                        ",-=.-\":;:;:;\\':;:;:;\"-._\r\n" + //
                        "\\\\\\:;:;:;:;:;\\:;:;:;:;:;\\\r\n" + //
                        " \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\\r\n" + //
                        "  \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\\r\n" + //
                        "   \\\\\\:;:;:;:;:;\\:;::;:;:;:\\\r\n" + //
                        "    \\\\\\;:;::;:;:;\\:;:;:;::;:\\\r\n" + //
                        "     \\\\\\;;:;:_:--:\\:_:--:_;:;\\ \r\n" + //
                        "      \\\\\\_.-\"      :      \"-._\\\r\n" + //
                        "       \\`_..--\"\"--.;.--\"\"--.._=>\r\n" + //
                        "        \"");
        
        System.out.println("Word Ladder Program");
        System.out.println("By Marvel Pangondian - 13522075");
        System.out.println("=================================================================");
        System.out.println("Picking dictionary");
        System.out.println("make sure the dictionary is in the test folder !");

        Dictionary dictionary;
        try {
            String dictionaryFileName = prompt.promptString("Enter Dictionary name (with .txt): ");
            dictionary = new Dictionary("test/" + dictionaryFileName);
            
        } catch (IOException e){
            System.out.println(StringUtil.getWordInRed("Something Went Wrong"));
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }
        System.out.println("=================================================================");
        System.out.println("Please select a display mode:");
        System.out.println("1. GUI (Graphical User Interface)");
        System.out.println("2. CLI (Command Line Interface)");
        int displayChoice = prompt.promptInt("Input: ");
        while (displayChoice <= 0 || displayChoice > 2) {
            System.out.println("Invalid Input! ");
            displayChoice = prompt.promptInt("Input: ");
            
        }
        switch (displayChoice) {
            case 1:
                AppGUI gui =  new AppGUI();
                gui.showGUI(dictionary);
                break;
            case 2:
                CLI cli = new CLI(dictionary,prompt);
                cli.showCLI();
                break;

            default:
                break;
        }


        
        
        scanner.close();

    }

}

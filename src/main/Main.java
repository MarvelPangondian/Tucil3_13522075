import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dictionary.*;
import util.StringUtil;
import customexception.*;
import algorithm.*;
import datastructure.*;


public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;


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
            System.out.print("Enter Dictionary name (with .txt): ");
            String dictionaryFileName = scanner.nextLine();
            dictionary = new Dictionary("test/" + dictionaryFileName);
            
        } catch (IOException e){
            System.out.println(StringUtil.getWordInRed("Something Went Wrong"));
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        while (choice != -1) {
            try {
                System.out.println("=================================================================");
                System.out.println("Main Menu");
                System.out.print("Enter Start Word: ");
                String startWord = scanner.nextLine();
                startWord = startWord.toLowerCase();
                System.out.print("Enter End Word: ");
                String endWord = scanner.nextLine();
                endWord = endWord.toLowerCase();
                System.out.println("\nPick algorithm to use ");
                System.out.println("1.Uniform Cost Search Algorithm ");
                System.out.println("2.Greedy Best-First Search Algorithm ");
                System.out.println("3.A-Star Search Algorithm ");
                System.out.print("Input: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                Node result;
                long startTime;
                long endTime;

                if (choice == 1){
                    UCS algorithm = new UCS();
                    startTime = System.nanoTime();
                    result = algorithm.getUniformCostSearch(startWord, endWord, dictionary);
                    endTime = System.nanoTime();
                    float executionTime = (endTime - startTime) / 1_000_000.0f;
                    result.printPath(executionTime, choice,endWord,dictionary);

                }else if (choice == 2){ 
                    GreedyBestFirst algorithm = new GreedyBestFirst();
                    startTime = System.nanoTime();
                    result = algorithm.getGreedyBestFirst(startWord, endWord, dictionary);
                    endTime = System.nanoTime();
                    float executionTime = (endTime - startTime) / 1_000_000.0f;
                    result.printPath(executionTime, choice,endWord,dictionary);

                }else if (choice == 3){
                    AStar algorithm = new AStar();
                    startTime = System.nanoTime();
                    result = algorithm.getAStar(startWord, endWord, dictionary);
                    endTime = System.nanoTime();
                    float executionTime = (endTime - startTime) / 1_000_000.0f;
                    result.printPath(executionTime, choice,endWord,dictionary);

                } else {
                    System.out.println("Input Choice is invalid !");
                }


            } catch (CustomException e){
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Error: Input was not an integer");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            } finally {
                System.out.print("Do you want to continue ? (-1 for no, any number for yes): ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            
        }
        scanner.close();

    }

}

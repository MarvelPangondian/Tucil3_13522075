import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dictionary.*;
import customexception.*;
import algorithm.*;
import datastructure.*;


public class Main {
    public static void main(String args[]){
        Dictionary dictionary;
        try {
            dictionary = new Dictionary("src/main/dictionary/data.txt");
        } catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

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
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != -1) {
            try {
                System.out.println("=================================================================");
                System.out.println("Main Menu");
                System.out.print("Enter Start Word: ");
                String startWord = scanner.nextLine();
                System.out.print("Enter End Word: ");
                String endWord = scanner.nextLine();
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

package cli;
import dictionary.*;
import customexception.*;
import algorithm.*;
import datastructure.*;
import prompt.*;

public class CLI {
    private Dictionary dictionary;
    private Prompt prompt;

    public CLI(Dictionary dictionary, Prompt prompt) {
        this.dictionary = dictionary;
        this.prompt = prompt;
    }




    public void showCLI(){
        int choice = 0;
        while (choice != -1) {
            try {
                System.out.println("=================================================================");
                System.out.println("Main Menu");
                String startWord = this.prompt.promptString("Enter Start Word: ");
                String endWord = this.prompt.promptString("Enter End Word: ");
                System.out.println("\nPick algorithm to use ");
                System.out.println("1. Uniform Cost Search Algorithm ");
                System.out.println("2. Greedy Best-First Search Algorithm ");
                System.out.println("3. A-Star Search Algorithm ");
                choice = this.prompt.promptInt("Input: ");
                System.out.println();

                SearchAlgorithm algorithm = null;
                switch (choice) {
                    case 1: algorithm = new UCS(); break;
                    case 2: algorithm = new GreedyBestFirst(); break;
                    case 3: algorithm = new AStar(); break;
                    default: System.out.println("Input Choice is invalid !"); continue;
                }

                long startTime = System.nanoTime();
                Node result = algorithm.search(startWord, endWord, dictionary);
                long endTime = System.nanoTime();
                float executionTime = (endTime - startTime) / 1_000_000.0f;
                result.printPath(executionTime, choice, endWord, dictionary);

            } catch (CustomException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                choice = this.prompt.promptInt("Do you want to continue ? (-1 for no, any number for yes): ");

            }
        }

    }


}

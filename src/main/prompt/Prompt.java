package prompt;

import java.util.Scanner;

public class Prompt {
    private Scanner scanner;

    public Prompt(Scanner scanner){
        this.scanner = scanner;
    }

    public String promptString(String message) {
        System.out.print(message);
        return scanner.nextLine().toLowerCase();
    }

    public int promptInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next(); // Consume the invalid input
            System.out.println("Error: Input was not an integer. Please try again.");
            System.out.print(message);
        }
        int temp = scanner.nextInt();
        scanner.nextLine();
        return temp ;
    }
}

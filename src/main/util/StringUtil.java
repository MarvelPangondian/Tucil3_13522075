package util;



public class StringUtil {

    public static String printNodeInColor(String startWord, String endWord){
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        StringBuilder coloredOutput = new StringBuilder();

        for (int i = 0; i < startWord.length(); i++) {
            if (i < endWord.length() && startWord.charAt(i) == endWord.charAt(i)) {
                coloredOutput.append(ANSI_GREEN).append(startWord.charAt(i)).append(ANSI_RESET);
            } else {
                coloredOutput.append(startWord.charAt(i));
            }
        }

    return coloredOutput.toString();

    }

    public static String getWordInRed(String word){
        final String ANSI_RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        return RED + word + ANSI_RESET;
    }

    public static String getWordInGreen(String word){
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        return ANSI_GREEN + word + ANSI_RESET;
    }

    public static void printArrNodeInColor(String[] nodes, String endWord){
        System.out.print("[");
        for (int i = 0 ; i < nodes.length ; i++){
            System.out.print(StringUtil.printNodeInColor(nodes[i], endWord));
            if (i != nodes.length - 1){
                System.out.print(", ");
            }
        }

        System.out.print("]");
    }
    
}

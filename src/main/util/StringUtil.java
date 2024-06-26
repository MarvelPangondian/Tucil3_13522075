package util;

public class StringUtil {

    // Static method to print a word in color, green letters for letters that are the same as endWord
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

    // Static method to get string in red color
    public static String getWordInRed(String word){
        final String ANSI_RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        return RED + word + ANSI_RESET;
    }

    // Static method to get string in green color
    public static String getWordInGreen(String word){
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        return ANSI_GREEN + word + ANSI_RESET;
    }

    // Static method to print array of words/nodes in color
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
    
    // Static method to highlight characters, will be used in GUI
    public static String highlightCertainCharacters(String word, String target) {
        StringBuilder highlighted = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i < target.length() && word.charAt(i) == target.charAt(i)) {
                highlighted.append("<span style='color:green'>").append(word.charAt(i)).append("</span>");
            } else {
                highlighted.append(word.charAt(i));
            }
        }
        return highlighted.toString();
    }
}

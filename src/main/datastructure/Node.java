package datastructure;
import customexception.CustomException;
import customexception.InvalidLengthWord;
import dictionary.Dictionary;
import util.*;
public class Node {
    private String word;
    private int value; // value can be g(n), h(n), or f(n) = g(n) + f(n)
    private String path[];
    private static int nodesTraverse;
    private static int nodesGenerated;
    private static String startWord;
    private static String endWord;

    // Concstructor
    public Node(){
        this.word = "";
        this.value = -1;
        this.path = new String[]{};
    }
    // Concstructor
    // with node word and value
    public Node(String word, int value){
        this.word = word;
        this.value = value;
        this.path = new String[]{this.word};
    }

    // Constructor
    // constructor with parent path, extend parent path with current word
    public Node(String word, int value, String[] parentPath){
        this.word = word;
        this.value = value;
        int tempSize = parentPath.length;
        this.path = new String[tempSize + 1];
        System.arraycopy(parentPath, 0, this.path, 0, tempSize);
        this.path[tempSize] = word;
    }

    // Constructor
    // constructor for greedy best first search
    public Node(String word, String target ,String[] parentPath) throws CustomException{ 
        this.word = word;
        this.value = this.getWordDifference(target); // heuristic function
        int tempSize = parentPath.length;
        this.path = new String[tempSize + 1];
        System.arraycopy(parentPath, 0, this.path, 0, tempSize);
        this.path[tempSize] = word;
    }

    // getters
    public String getWord(){
        return this.word;
    }

    public int getValue(){
        return this.value;
    }

    public String[] getPath(){
        return this.path;
    }

    public static String getStartWord(){
        return Node.startWord;
    }

    public static String getEndWord(){
        return Node.endWord;
    }

    public static int getNodesTraverse(){
        return Node.nodesTraverse;
    }

    public static int getNodesGenerated(){
        return Node.nodesGenerated;
    }
    

    // setters
    public void setPath(String[] path){
        this.path = new String[path.length];
        System.arraycopy(path, 0, this.path, 0, path.length);
    }

    public static void setStartWord(String startWord){
        Node.startWord = startWord;
    }

    public static void setEndWord(String endWord){
        Node.endWord = endWord;
    }

    public void setWord(String word){
        this.word = word;
    }

    public void setValue(int value){
        this.value = value;
    }


    // reset static variables
    public static void resetNodesTraverse(){
        Node.nodesTraverse = 0;
    }

    public static void resetNodesGenerated(){
        Node.nodesGenerated = 0;
    }

    public static void resetNodeClass(){
        Node.resetNodesGenerated();;
        Node.resetNodesTraverse();
        Node.startWord = "";
        Node.endWord = "";
    }


    // increment static variables
    public static void incrementNodesTraverse(){
        Node.nodesTraverse++;
    }

    public static void incrementNodesGenerated(){
        Node.nodesGenerated++;
    }

    // toString for debugging
    public String toString(){
        String temp = "[";
        for (int i = 0; i < this.path.length; i++) {
            temp = temp + StringUtil.printNodeInColor(this.path[i], Node.getEndWord());
            
            if (i != this.path.length - 1) {
                temp += ", ";
            } else {

            }
        }
        temp += "]";
        return temp;
    }

    // printPath to print result of a node
    public void printPath(float timeInMs, int algorithmType, String endWord,Dictionary dictionary){
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        // boolean found = true;
        if (this.getWord().compareTo(endWord) != 0){
            // found = false;
            System.out.println(RED + "No path can be found !" + RESET);
        }
        System.out.println("Word path : ");
        int count = 1;
        for (String word : this.path){
            System.out.print(String.format("-> %d. ", count));
            count++;
            System.out.print(StringUtil.printNodeInColor(word, endWord));
            // if (!found){
            //     System.out.print(", child nodes : ");StringUtil.printArrNodeInColor(dictionary.getNeighbors(word).toArray(new String[0]), endWord);
            // }
            System.out.println();
        }
        if (this.getWord().compareTo(endWord) != 0){
            System.out.println(RED + "STUCK/DEAD END" + RESET);
        }
        if (algorithmType == 1){
            System.out.print("g(n) = ");
        } else if (algorithmType == 2){
            System.out.print("h(n) = ");
        } else if (algorithmType == 3){
            System.out.print("g(n) + h(n) = ");
        } else {
            return;
        }
        System.out.println(this.value);
        System.out.printf("Time taken : %f ms\n",timeInMs);
        System.out.println("Nodes traversed : " + Node.getNodesTraverse());
        System.out.println("Nodes generated : " + Node.getNodesGenerated());
        if (this.getWord().compareTo(endWord) == 0){
            System.out.println("Length of Path : " + this.getPath().length + "\n");
        }


    }

    // Heuristic function
    // is used by Node Heuristic Constructor
    public int getWordDifference(String endWord) throws CustomException{
        if (this.getWord().length() != endWord.length()){
            throw new InvalidLengthWord(this.getWord(), endWord);
        }
        int count = 0;
        char[] arr1 = this.getWord().toCharArray();
        char[] arr2 = endWord.toCharArray();
        // asumsikan kedua word memiliki leng
        for (int i = 0 ; i < arr1.length ; i++){
            if (arr1[i] != arr2[i]){
                count++;
            }
        }
        return count;
    }
    
    public static int getWordDifference(String startWord, String endWord) throws CustomException {


        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord(startWord, endWord);
        }
        int count = 0;
        char[] arr1 = startWord.toCharArray();
        char[] arr2 = endWord.toCharArray();
        // asumsikan kedua word memiliki leng
        for (int i = 0 ; i < arr1.length ; i++){
            if (arr1[i] != arr2[i]){
                count++;
            }
        }
        return count;

    }

}

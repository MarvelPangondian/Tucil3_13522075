package datastructure;

public class Node {
    private String word;
    private int value; // value can be g(n), h(n), or f(n) = g(n) + f(n)

    public Node(){
        this.word = "";
        this.value = 0;
    }
    public Node(String word, int value){
        this.word = word;
        this.value = value;
    }

    public String getWord(){
        return this.word;
    }

    public int getValue(){
        return this.value;
    }
    public void setWord(String word){
        this.word = word;
    }

    public void setValue(int value){
        this.value = value;
    }
    
}

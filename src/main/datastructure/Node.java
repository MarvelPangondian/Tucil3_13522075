package datastructure;

public class Node {
    private String word;
    private int value; // value can be g(n), h(n), or f(n) = g(n) + f(n)
    private String path[];

    public Node(){
        this.word = "";
        this.value = 0;
    }
    public Node(String word, int value){
        this.word = word;
        this.value = value;
        this.path = new String[]{this.word};
    }

    public Node(String word, int value, String[] parentPath){
        this.word = word;
        this.value = value;
        int tempSize = parentPath.length;
        this.path = new String[tempSize + 1];
        System.arraycopy(parentPath, 0, this.path, 0, tempSize);
        this.path[tempSize] = word;
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

    public void setPath(String[] path){
        this.path = new String[path.length];
        System.arraycopy(path, 0, this.path, 0, path.length);
    }

    public String[] getPath(){
        return this.path;
    }

    public String toString(){
        String temp = "[";
        for (int i = 0; i < this.path.length; i++) {
            temp = temp + this.path[i];
            
            if (i != this.path.length - 1) {
                temp += ", ";
            } else {

            }
        }
        temp += "]";
        return temp;
    }

    public int getWordDifference(Node endWord){
        int count = 0;
        char[] arr1 = this.getWord().toCharArray();
        char[] arr2 = endWord.getWord().toCharArray();
        // asumsikan kedua word memiliki leng
        for (int i = 0 ; i < arr1.length ; i++){
            if (arr1[i] != arr2[i]){
                count++;
            }
        }
        return count;
    }
    
}

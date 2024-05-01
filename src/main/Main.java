import java.io.IOException;

import datastructure.*;
import dictionary.*;
public class Main {
    public static void main(String args[]){
        Node n1 = new Node("love",100);
        Node n2 = new Node("Right",10,n1.getPath());
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(n1);
        queue.addNode(n2);

        // System.out.println(n2);
        while (!queue.isEmpty()){
            System.out.println(queue.dequeueNode().getWord());
        }

        try {
            Dictionary dictionary = new Dictionary("src/main/dictionary/data.txt");
            System.out.println("Neighbors of 'word': " + dictionary.getNeighbors("marvelpangondian"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

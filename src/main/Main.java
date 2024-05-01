import datastructure.*;
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
    }
    
}

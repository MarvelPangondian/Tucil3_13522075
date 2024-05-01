package datastructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueNode {
    private PriorityQueue<Node> priorityQueue;

    public PriorityQueueNode(){
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getValue));
    }

    public void addNode(Node n){
        this.priorityQueue.add(n);
    }
    
    public Node dequeueNode(){
        return this.priorityQueue.remove();
    }

    public boolean isEmpty(){
        return this.priorityQueue.isEmpty();
    }
}

package datastructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueNode {
    private PriorityQueue<Node> priorityQueue; // Priority Queue Object

    // Constructor
    public PriorityQueueNode(){
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getValue)); // initialize priority queue
    }
    
    // addNode to insert node
    public void addNode(Node n){
        this.priorityQueue.add(n);
    }
    
    // dequeueNode to dequeue the first element of queue
    public Node dequeueNode(){
        return this.priorityQueue.remove();
    }
    
    // isEmpty to determine if queue is empty or not
    public boolean isEmpty(){
        return this.priorityQueue.isEmpty();
    }
}

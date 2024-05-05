package algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import datastructure.*;
import customexception.*;
import dictionary.Dictionary;

public class AStar implements SearchAlgorithm{
    private Map<String, Integer> visited;

    public AStar(){
        visited = new HashMap<>(); // initialize set object

    }

    public Node search(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        visited.clear(); // clear visited set
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();
        Node.resetNodeClass(); // reset Node static variables
        
        // Validation
        if (!dictionary.isWord(startWord)){
            throw new InvalidStartWordException();
        }
        if (!dictionary.isWord(endWord)){
            throw new InvalidEndWordException();
        }

        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord(startWord, endWord);
        }

        // Set start word and end word static variables
        Node.setStartWord(startWord);
        Node.setEndWord(endWord);

        // Initialize essential nodes
        Node start = new Node(startWord,endWord,new String[]{}); // distance of start node with root (itself) is 0
        Node lastNode = new Node(); // will hold final answer
        Node record = new Node(); // will hold the last processed node

        // Initialize queue
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start); // first element of queue
        boolean done = false; 
        visited.put(startWord,start.getValue());
        
        while (!queue.isEmpty() && !done){

            // dequeue first element in queue
            Node currNode = queue.dequeueNode();
            Node.incrementNodesTraverse(); // increment nodes traversed
            record = currNode; // store last node processed

            // Check if the process should be stopped or not
            if (lastNode.getValue() != -1){
                if (currNode.getValue() > lastNode.getValue()){
                    done = true;
                    continue;
                }
            }

            //check if currNode is the end word
            if (currNode.getWord().equals(endWord) ){
                lastNode = currNode;
            }

            // Get all neighbour nodes/words
            List<String> newNodes = dictionary.getNeighbors(currNode.getWord());

            int tempValue = currNode.getPath().length;
            int heuristic; 
            // iterate every neighbour
            for(String nodeString : newNodes){
                heuristic = tempValue + Node.getWordDifference(nodeString, endWord);  // update value to use heuristic h(n), final value is f(n) = g(n) + h(n) 
                if (visited.containsKey(nodeString)){
                    // calculating temporary heuristic value
                    if (heuristic > visited.get(nodeString)){
                        continue;
                    }
 
                    
                }
                Node.incrementNodesGenerated(); // increment nodesGenerated static variable
                visited.put(nodeString,heuristic); // update visited
                Node node = new Node(nodeString, heuristic , currNode.getPath()); // create node, with f(n) = distance of node with the start node (g(n))
                queue.addNode(node); 

            }
        }

        // situation where there is no solution
        if (lastNode.getValue() == -1){
            lastNode = record;
        }
        return lastNode;   
    }
}





package algorithm;

import java.util.*;
import datastructure.*;
import dictionary.Dictionary;
import customexception.*;


public class UCS implements SearchAlgorithm {
    private Set<String> visited; 
    public UCS(){
        visited = new HashSet<>(); // initialize set object
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
        Node start = new Node(startWord,0); // distance of start node with root (itself) is 0
        Node record = new Node(); // will hold the last processed node

        // Initialize queue
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);// first element of queue
        visited.add(startWord);

        while (!queue.isEmpty()){

            // dequeue first element in queue
            Node currNode = queue.dequeueNode();
            record = currNode;
            Node.incrementNodesTraverse(); // increment nodes traversed

            //check if currNode is the end word
            if (currNode.getWord().equals(endWord) ){
                return currNode;
            }

            // Get all neighbour nodes/words
            List<String> newNodes = dictionary.getNeighbors(currNode.getWord());

            // iterate every neighbour
            for(String nodeString : newNodes){
                if (!visited.contains(nodeString)){
                    visited.add(nodeString); // update visited
                    Node.incrementNodesGenerated(); // increment nodesGenerated static variable
                    Node node = new Node(nodeString,currNode.getValue() + 1, currNode.getPath()); // create node, with g(n) = distance of node with the start node

                    if (node.getWord().equals(endWord) ){ // if curr node is the end word, no need to process anymore
                        return node;
                    }

                    // add node to queue
                    queue.addNode(node);
                }
                else {
                    continue;
                }
            }
        }
        // situation where there is no solution
        return record;
        
    }
    
}

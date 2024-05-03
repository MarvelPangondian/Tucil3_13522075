package algorithm;

import java.util.*;
import datastructure.*;
import dictionary.Dictionary;
import customexception.*;


public class UCS implements SearchAlgorithm {
    private Map<String, Boolean> visited;
    public UCS(){
        visited = new HashMap<>();
    }

    public Node search(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        visited.clear();
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();
        Node.resetNodesTraverse();

        if (!dictionary.isWord(startWord)){
            throw new InvalidStartWordException();
        }
        if (!dictionary.isWord(endWord)){
            throw new InvalidEndWordException();
        }

        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord(startWord, endWord);
        }
        Node.setStartWord(startWord);
        Node.setEndWord(endWord);
        Node start = new Node(startWord,0); // distance of start node with root (itself) is 0
        // Node lastNode = new Node();
        Node record = new Node();
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);
        visited.put(startWord, true);
        while (!queue.isEmpty()){
            // dequeue
            Node currNode = queue.dequeueNode();
            record = currNode;
            Node.incrementNodesTraverse();

            //check
            if (currNode.getWord().equals(endWord) ){
                return currNode;
            }
            List<String> newNodes = dictionary.getNeighbors(currNode.getWord());
            for(String nodeString : newNodes){
                if (!visited.containsKey(nodeString)){
                    visited.put(nodeString,true);
                    Node node = new Node(nodeString,currNode.getValue() + 1, currNode.getPath());
                    if (node.getWord().equals(endWord) ){
                        return node;
                    }
                    queue.addNode(node);
                }
                else {
                    continue;
                }
            }
        }
        return record;


        
    }
    
}

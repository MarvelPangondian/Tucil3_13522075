package algorithm;

import java.util.*;
import datastructure.*;
import dictionary.Dictionary;
import customexception.*;


public class UCS {
    private Map<String, Boolean> visited;
    public UCS(){
        visited = new HashMap<>();
    }

    public Node getUniformCostSearch(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        visited.clear();
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();

        if (!dictionary.isWord(startWord)){
            throw new CustomException("start word doesn't exist");
        }
        if (!dictionary.isWord(endWord)){
            throw new CustomException("end word doesn't exist");
        }

        if (startWord.length() != endWord.length()){
            throw new CustomException("Unequal lengths of words");
        }

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

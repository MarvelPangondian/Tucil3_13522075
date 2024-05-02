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

    public String[] getUniformCostSearch(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        visited.clear();

        if (!dictionary.isWord(startWord)){
            throw new CustomException("start word doesn't exist");
        }
        if (!dictionary.isWord(endWord)){
            throw new CustomException("end word doesn't exist");
        }

        Node start = new Node(startWord,0); // distance of start node with root (itself) is 0
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);
        Node lastNode = new Node("Placeholder",-1);
        while (!queue.isEmpty() && lastNode.getValue() == -1){
            // dequeue
            Node currNode = queue.dequeueNode();

            //check
            if (currNode.getWord().equals(endWord) ){
                return currNode.getPath();
            }

            List<String> newNodes = dictionary.getNeighbors(currNode.getWord());

            
            for(String nodeString : newNodes){
                if (!visited.containsKey(nodeString)){
                    visited.put(nodeString,true);
                    Node node = new Node(nodeString,currNode.getValue() + 1, currNode.getPath());
                    if (node.getWord().equals(endWord) ){
                        return node.getPath();
                    }
                    queue.addNode(node);
                }
                else {
                    continue;
                }
            }
        }


        return new String[]{};


        
    }
    
}

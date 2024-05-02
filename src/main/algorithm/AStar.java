package algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import datastructure.*;

import customexception.CustomException;
import datastructure.PriorityQueueNode;
import dictionary.Dictionary;

public class AStar {
       private Map<String, Boolean> visited;
    public AStar(){
        visited = new HashMap<>();
    }

    public Node getAStar(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
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
        Node end = new Node(endWord, -1);
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);
        Node lastNode = new Node("Placeholder",-1);
        boolean done = false;
        while (!queue.isEmpty() && !done){
            Node currNode = queue.dequeueNode();
            // dequeue
            if (lastNode.getValue() != -1){
                if (currNode.getValue() > lastNode.getValue()){
                    done = true;
                    continue;
                }
            }

            //check
            if (currNode.getWord().equals(endWord) ){
                lastNode = currNode;
            }

            List<String> newNodes = dictionary.getNeighbors(currNode.getWord());

            
            for(String nodeString : newNodes){
                if (!visited.containsKey(nodeString)){
                    visited.put(nodeString,true);
                    Node node = new Node(nodeString, currNode.getPath().length , currNode.getPath());
                    node.setValue(node.getValue() + node.getWordDifference(end));
                    queue.addNode(node);
                }
                else {
                    continue;
                }
            }
        }


        return lastNode;


        
    }

}





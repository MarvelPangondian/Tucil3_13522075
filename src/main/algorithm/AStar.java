package algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import datastructure.*;
import customexception.*;
import datastructure.PriorityQueueNode;
import dictionary.Dictionary;

public class AStar {
    private Map<String, Boolean> visited;
    public AStar(){
        visited = new HashMap<>();
    }

    public Node getAStar(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        Node.resetNodesTraverse();
        visited.clear();
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();

        if (!dictionary.isWord(startWord)){
            throw new InvalidStartWordException();
        }
        if (!dictionary.isWord(endWord)){
            throw new InvalidEndWordException();
        }

        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord(startWord, endWord);
        }

        Node start = new Node(startWord,0); // distance of start node with root (itself) is 0
        Node lastNode = new Node();
        Node record = new Node();
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);
        boolean done = false;
        visited.put(startWord, true);
  
        
        while (!queue.isEmpty() && !done){
            Node currNode = queue.dequeueNode();
            Node.incrementNodesTraverse();
            record = currNode;
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
                    node.setValue(node.getValue() + node.getWordDifference(endWord));
                    queue.addNode(node);
                }
                else {
                    continue;
                }
            }
        }
        if (lastNode.getValue() == -1){
            lastNode = record;
        }
        return lastNode;
        
    }

}





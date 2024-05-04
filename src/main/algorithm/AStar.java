package algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datastructure.*;
import customexception.*;
import dictionary.Dictionary;

public class AStar implements SearchAlgorithm{
    private Set<String> visited;

    public AStar(){
        visited = new HashSet<>();
    }

    public Node search(String startWord, String endWord, Dictionary dictionary ) throws CustomException{
        Node.resetNodeClass();
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
        Node.setStartWord(startWord);
        Node.setEndWord(endWord);
        Node start = new Node(startWord,0); // distance of start node with root (itself) is 0
        Node lastNode = new Node();
        Node record = new Node();
        PriorityQueueNode queue = new PriorityQueueNode();
        queue.addNode(start);
        boolean done = false;
        visited.add(startWord);
  
        
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
                if (!visited.contains(nodeString)){
                    Node.incrementNodesGenerated();
                    visited.add(nodeString);
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





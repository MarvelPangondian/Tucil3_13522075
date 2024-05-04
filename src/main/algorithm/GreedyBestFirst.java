package algorithm;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import customexception.*;
import datastructure.*;
import dictionary.Dictionary;

public class GreedyBestFirst implements SearchAlgorithm{
    private Set<String> visited; 

    public GreedyBestFirst(){
        visited = new HashSet<>(); // initialize set object
    }

    public Node search(String startWord, String endWord, Dictionary dictionary) throws CustomException{
        visited.clear(); // clear visited set 
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();
        Node.resetNodeClass(); // reset Node static variabless

        // Validation
        if (!dictionary.isWord(startWord)){
            throw new InvalidStartWordException();
        }
        if (!dictionary.isWord(endWord)){
            throw new InvalidEndWordException();
        }
        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord(startWord,endWord);
        }

        // Set start word and end word static variables
        Node.setStartWord(startWord);
        Node.setEndWord(endWord);

        // Initialize essential nodes
        Node lastNode = new Node(); // will hold final answer
        Node targetNode = new Node(endWord, endWord, new String[]{}); // hold end word, for heuristic comparison
        Node currNode = new Node(startWord,endWord,new String[]{}); // will hold current node
        Node record = new Node(); // will hold the last node processed
        visited.add(startWord);

        while (currNode != null){
            Node.incrementNodesTraverse(); // increment nodes traversed
            record = currNode; // store last node processed

            //check if currNode is the end word
            if (currNode.getWord().equals(endWord)){
                lastNode = currNode;
                currNode = null;
                continue;
            }
            
            // Get all neighbour nodes/words
            List<String> allNode = dictionary.getNeighbors(currNode.getWord());
            
            String[] path = currNode.getPath();
            currNode = null;
            int count = 0;

            // iterate every neighbour
            for (String temp : allNode){
                if (visited.contains(temp)){ // skip visited nodes
                    continue;
                }
                Node.incrementNodesGenerated(); // increment nodesGenerated static variable

                // picking best node for currNode
                if (count == 0){
                    currNode = new Node(temp,endWord,path);
                    count++; // count is for the first word only
                }
                else {
                    
                    // heuristic comparison
                    // compare which node is better for currNode
                    if (targetNode.getWordDifference(temp) < currNode.getValue()){
                        currNode = new Node(temp,endWord,path);
                    }
                }
            }

            // update visited
            if (currNode != null){
                visited.add(currNode.getWord());
            }

        }

        // situation where there is no path
        if (lastNode.getValue() == -1){
            lastNode = record;
            
        }
        return lastNode;
    }
    
}

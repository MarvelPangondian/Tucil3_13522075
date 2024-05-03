package algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import customexception.*;
import datastructure.*;
import dictionary.Dictionary;

public class GreedyBestFirst implements SearchAlgorithm{
    private Map<String, Boolean> visited;
    public GreedyBestFirst(){
        visited = new HashMap<>();
    }

    public Node search(String startWord, String endWord, Dictionary dictionary) throws CustomException{
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
            throw new InvalidLengthWord(startWord,endWord);
        }

        Node lastNode = new Node();
        Node targetNode = new Node(endWord, endWord, new String[]{});
        Node currNode = new Node(startWord,endWord,new String[]{});
        Node record = new Node();
        visited.put(startWord, true);

        while (currNode != null){
            Node.incrementNodesTraverse();
            record = currNode;

            if (currNode.getWord().equals(endWord)){
                lastNode = currNode;
                currNode = null;
                continue;
            }
                
            List<String> allNode = dictionary.getNeighbors(currNode.getWord());
            String[] path = currNode.getPath();
            currNode = null;
            int count = 0;
            for (String temp : allNode){
                if (visited.containsKey(temp)){
                    continue;
                }
                if (count == 0){
                    currNode = new Node(temp,endWord,path);
                    count++; // count is for the first word only
                }
                else {
                    
                    if (targetNode.getWordDifference(temp) < currNode.getValue()){
                        currNode = new Node(temp,endWord,path);
                    }
                }
            }
            if (currNode != null){
                visited.put(currNode.getWord(), true);
            }

        }

        if (lastNode.getValue() == -1){
            lastNode = record;
            
        }
        return lastNode;
    }
    
}

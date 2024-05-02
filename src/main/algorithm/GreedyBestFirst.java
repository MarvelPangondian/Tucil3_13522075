package algorithm;

import java.util.List;

import customexception.*;
import datastructure.*;
import dictionary.Dictionary;

public class GreedyBestFirst {
    public GreedyBestFirst(){}

    public Node getGreedyBestFirst(String startWord, String endWord, Dictionary dictionary) throws CustomException{
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();

        if (!dictionary.isWord(startWord)){
            throw new InvalidStartWordException();
        }
        if (!dictionary.isWord(endWord)){
            throw new InvalidEndWordException();
        }

        if (startWord.length() != endWord.length()){
            throw new InvalidLengthWord();
        }

        Node lastNode = new Node();
        Node targetNode = new Node(endWord, endWord, new String[]{});
        Node currNode = new Node(startWord,endWord,new String[]{});

        while (currNode != null){
            System.out.println(currNode.getWord());
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



   

        }

        return lastNode;
    }
    
}

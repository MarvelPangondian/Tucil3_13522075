package dictionary;

import java.util.*;
import java.io.*;

public class Dictionary {
    private Set<String> words = new HashSet<>();
    private Map<String, List<String>> neighbors = new HashMap<>();

    public Dictionary(String fileName) throws IOException {
        this.loadWords(fileName);
        this.generateNeighbors();
    }

    private void loadWords(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            words.add(line.trim().toLowerCase());
        }
        reader.close();
    }

    private void generateNeighbors() {
        for (String word : words) {
            List<String> adjWords = new ArrayList<String>();
            char[] wordArray = word.toCharArray();
            for (int i = 0; i < wordArray.length; i++) {
                char originalChar = wordArray[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) {
                        continue;
                    }
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if (words.contains(newWord)) {
                        adjWords.add(newWord);
                    }
                }
                wordArray[i] = originalChar; 
            }
            neighbors.put(word, adjWords);
        }
    }

    public boolean isWord(String word) {
        return this.words.contains(word);
    }

    public List<String> getNeighbors(String word) {
        return this.neighbors.getOrDefault(word, new ArrayList<>());
    }
}
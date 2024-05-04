package dictionary;
import java.util.*;
import util.StringUtil;
import java.io.*;

public class Dictionary {
    private Set<String> words = new HashSet<>();
    private Map<String, List<String>> neighbors = new HashMap<>();

    // Constructor
    public Dictionary(String fileName) throws IOException {
        System.out.println("Loading Dictionary...");
        this.loadWords(fileName);
        this.generateNeighbors();
        System.out.println(StringUtil.getWordInGreen("DONE"));
    }


    // Load words in txt file
    private void loadWords(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            words.add(line.trim().toLowerCase());
        }
        reader.close();
    }

    // Generate neighbouring words for every word in the dictionary
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

    // Determine if a word is in dictionary or not
    public boolean isWord(String word) {
        return this.words.contains(word);
    }

    // get all neighbouring words
    public List<String> getNeighbors(String word) {
        return this.neighbors.getOrDefault(word, new ArrayList<>());
    }
}
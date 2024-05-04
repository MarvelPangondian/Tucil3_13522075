package gui;
import javax.swing.*;
import algorithm.AStar;
import algorithm.GreedyBestFirst;
import algorithm.SearchAlgorithm;
import algorithm.UCS;
import customexception.CustomException;
import customexception.EmptyWordException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dictionary.Dictionary;
import datastructure.*;

public class AppGUI extends JFrame {
        private JTextField startWordField;
        private JTextField endWordField;
        private JComboBox<String> algorithmDropdown;
        private JButton searchButton;

        // Constructor
        public AppGUI() {
            super("Word Ladder");  
        }

        // Method to initialize GUI
        private void initializeGUI(Dictionary dictionary) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLayout(new GridLayout(5, 2, 10, 10));

            startWordField = new JTextField();
            endWordField = new JTextField();

            String[] algorithms = {"Uniform Cost Search", "Best First Search", "A Star"};
            algorithmDropdown = new JComboBox<>(algorithms);

            searchButton = new JButton("Search");

            add(new JLabel("Start Word:"));
            add(startWordField);
            add(new JLabel("End Word:"));
            add(endWordField);
            add(new JLabel("Algorithm:"));
            add(algorithmDropdown);
            add(searchButton);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    performSearch(dictionary);
                }
            });

            setVisible(true);
        }

        // method to peform search
        private void performSearch(Dictionary dictionary) {
            try {
                String startWord = startWordField.getText().toLowerCase();
                String endWord = endWordField.getText().toLowerCase();
                String selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();
        
                // Simulate search process and throw an exception to demonstrate handling
                if (startWord.isEmpty() || endWord.isEmpty()) {
                    throw new EmptyWordException();
                }
                SearchAlgorithm algorithm = null;
                switch (selectedAlgorithm) {
                    case "Uniform Cost Search" : algorithm = new UCS(); break;
                    case "Best First Search": algorithm = new GreedyBestFirst(); break;
                    case "A Star": algorithm = new AStar(); break;
                    default: throw new CustomException("Invalid Algorithm");
                }
                long startTime = System.nanoTime();
                Node result = algorithm.search(startWord, endWord, dictionary);
                long endTime = System.nanoTime();
                float executionTime = (endTime - startTime) / 1_000_000.0f;
                if (result.getWord().compareTo(endWord) != 0){
                    throw new CustomException(String.format("No path can be found !\n Nodes Traversed: %d\n Time: %f ms",Node.getNodesTraverse(),executionTime));
                }
                SearchResultWindow resultWindow = new SearchResultWindow(result,executionTime,selectedAlgorithm);
                resultWindow.setVisible(true);
        
                System.out.println("Starting search from: " + startWord + " to " + endWord + " using " + selectedAlgorithm);
                
            }catch (CustomException e){
                JOptionPane.showMessageDialog(this, "Error during search: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error during search: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            } 
        }

        public void showGUI(Dictionary dictionary) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    initializeGUI(dictionary);
                }
            });
        }
}
// class for search result
class SearchResultWindow extends JFrame {
        private JList<String> resultList;
        private DefaultListModel<String> listModel;
    
        public SearchResultWindow(Node result, float executionTime, String algorithmName) {
            super("Search Results");
            setLocationRelativeTo(null);
    
            String[] searchResults = result.getPath();
    
            listModel = new DefaultListModel<>();
            resultList = new JList<>(listModel);
            resultList.setFont(new Font("Arial", Font.PLAIN, 14));
    
            JScrollPane listScrollPane = new JScrollPane(resultList);
            listScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
            JPanel headerPanel = new JPanel(new GridLayout(6, 1));
            JLabel resultHeader = new JLabel("Result", JLabel.CENTER);
            resultHeader.setFont(new Font("Arial", Font.BOLD, 24)); 
            headerPanel.add(resultHeader);
            headerPanel.add(new JLabel("Using " + algorithmName, JLabel.CENTER));
            headerPanel.add(new JLabel("Time taken: " + executionTime + " ms", JLabel.CENTER));
            headerPanel.add(new JLabel("Nodes Traversed: " + Node.getNodesTraverse(), JLabel.CENTER));
            headerPanel.add(new JLabel("Nodes Generated: " + Node.getNodesGenerated(), JLabel.CENTER));
            headerPanel.add(new JLabel("Length of Path: " + result.getPath().length, JLabel.CENTER));
    
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
            JPanel gridPanel = new JPanel(new GridLayout(searchResults.length, 1, 0, 10));  
            JScrollPane gridScrollPane = new JScrollPane(gridPanel); 
            gridScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            gridScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    
            String endWord = Node.getEndWord();  
            for (String word : searchResults) {
                JPanel wordPanel = new JPanel(new GridLayout(1, word.length(), 10, 0));  
                wordPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
                for (int i = 0; i < word.length(); i++) {
                    JLabel letterLabel = new JLabel(String.valueOf(word.charAt(i)), SwingConstants.CENTER);
                    letterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    letterLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
                    if (endWord != null && word.charAt(i) == endWord.charAt(i)) {
                        letterLabel.setBackground(Color.GREEN);
                        letterLabel.setOpaque(true);
                    }
                    wordPanel.add(letterLabel);
                }
                gridPanel.add(wordPanel);
            }
    
            getContentPane().add(headerPanel, BorderLayout.NORTH);
            getContentPane().add(gridScrollPane, BorderLayout.CENTER);  
    

            if (searchResults.length > 10) {
                setSize(600, 800);  
            } else {
                setSize(600, 400);  
            }
            
            setVisible(true);
        }
}
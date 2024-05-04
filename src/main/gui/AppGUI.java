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
import util.StringUtil;
import datastructure.*;

public class AppGUI extends JFrame {
        private JTextField startWordField;
        private JTextField endWordField;
        private JComboBox<String> algorithmDropdown;
        private JButton searchButton;

        // Constructor
        public AppGUI() {
            super("Search GUI");  
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

// Class for the search results window
class SearchResultWindow extends JFrame {
        private JList<String> resultList;
        private DefaultListModel<String> listModel;

        public SearchResultWindow(Node result, float executionTime, String algorithmName) {
            super("Search Results");
            setSize(600, 400);
            setLocationRelativeTo(null);
    
            String[] searchResults = result.getPath();
            
            listModel = new DefaultListModel<>();
            resultList = new JList<>(listModel);
            resultList.setFont(new Font("Arial", Font.PLAIN, 14));
    
            JScrollPane scrollPane = new JScrollPane(resultList);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JPanel headerPanel = new JPanel(new GridLayout(5, 1));
            headerPanel.add(new JLabel("Result", JLabel.CENTER));
            headerPanel.add(new JLabel("Using " + algorithmName, JLabel.CENTER));
            headerPanel.add(new JLabel("Time taken: " + executionTime + " ms", JLabel.CENTER));
            headerPanel.add(new JLabel("Nodes Traversed: " + Node.getNodesTraverse() , JLabel.CENTER));
            headerPanel.add(new JLabel("Nodes Generated: " + Node.getNodesGenerated() , JLabel.CENTER));
    
            getContentPane().add(headerPanel, BorderLayout.NORTH);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
    
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
            int count = 1;
            for (String word : searchResults) {
                String highlightedWord = StringUtil.highlightCertainCharacters(word,Node.getEndWord());
                listModel.addElement(String.format("<html>%d. %s </html>",count,highlightedWord ));
                count++;
            }
            
            setVisible(true);
        }
}

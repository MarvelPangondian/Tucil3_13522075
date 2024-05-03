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

        public AppGUI() {
            super("Search GUI");
            
        }

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
                Node result = algorithm.search(startWord, endWord, dictionary);
                if (result.getWord().compareTo(endWord) != 0){
                    throw new CustomException("No path can be found !");
                }
                String[] searchResults = result.getPath();
                SearchResultWindow resultWindow = new SearchResultWindow(searchResults);
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

        public SearchResultWindow(String[] searchResults) {
            super("Search Results");
            setSize(300, 200);
            listModel = new DefaultListModel<>();
            resultList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(resultList);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            for (String result : searchResults) {
                listModel.addElement(result);
            }
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
}

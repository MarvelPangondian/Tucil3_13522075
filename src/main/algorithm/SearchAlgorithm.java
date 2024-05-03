package algorithm;
import datastructure.*;
import dictionary.Dictionary;
import customexception.*;

public interface SearchAlgorithm {
    Node search(String start, String end, Dictionary dict) throws CustomException;
}


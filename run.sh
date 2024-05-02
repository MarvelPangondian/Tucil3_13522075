set +x

# Find and delete all files in bin/ directory except those with the .txt extension
find bin/ -type f ! -name '*.txt' -delete

# Compile Java classes
javac -d bin src/main/Main.java src/main/datastructure/Node.java src/main/datastructure/PriorityQueueNode.java src/main/dictionary/Dictionary.java src/main/algorithm/UCS.java src/main/customexception/CustomException.java src/main/algorithm/AStar.java

# Run the Main class
java -cp bin Main

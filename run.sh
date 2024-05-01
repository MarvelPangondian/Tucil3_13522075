
set +x

rm -rf bin/*

javac -d bin src/main/Main.java src/main/datastructure/Node.java src/main/datastructure/PriorityQueueNode.java src/main/dictionary/Dictionary.java

java -cp bin Main
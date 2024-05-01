@echo off
del /s /q bin\*
javac -d bin src\main\Main.java src\main\datastructure\Node.java
java -cp bin Main

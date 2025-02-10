@echo off
REM Compile the Java files
javac -d bin src/chess/*.java

REM Run the compiled code
java -cp bin chess.PlayChess
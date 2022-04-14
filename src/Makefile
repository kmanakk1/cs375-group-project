JDK = javac
JVM = java
JAR = jar
SRC = Sudoku.java Solver.java

all: ${SRC}
	$(JDK) ${SRC}
	$(JAR) cfe Sudoku.jar Sudoku *.class

tests: all
	$(JVM) Sudoku ../puzzles/demo.txt 1
	$(JVM) Sudoku ../puzzles/hard0.txt 1
	$(JVM) Sudoku ../puzzles/empty.txt 1

clean:
	rm *.class Sudoku.jar
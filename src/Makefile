JDK = javac
JVM = java
JAR = jar
SRC = Sudoku.java Solver.java

all: ${SRC}
	$(JDK) ${SRC}
	$(JAR) cfe Sudoku.jar Sudoku *.class

tests: all
	$(JVM) Sudoku ../puzzles/demo.txt
	$(JVM) Sudoku ../puzzles/hard0.txt

clean:
	rm *.class Sudoku.jar
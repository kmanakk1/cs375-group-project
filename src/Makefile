JDK = javac
JVM = java
JAR = jar
SRC = Sudoku.java BacktrackSolver.java

all: ${SRC}
	$(JDK) ${SRC}
	$(JAR) cfe Sudoku.jar Sudoku *.class

tests: all
	$(JVM) Sudoku ../puzzles/demo.txt

clean:
	rm *.class Sudoku.jar
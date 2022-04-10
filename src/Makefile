JDK = javac
JVM = java
SRC = Sudoku.java BacktrackSolver.java

all: ${SRC}
	$(JDK) ${SRC}

tests: all
	$(JVM) Sudoku ../puzzles/demo.txt

clean:
	rm *.class
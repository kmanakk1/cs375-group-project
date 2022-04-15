JDK = javac
JVM = java
JAR = jar
SRC = *.java

all: ${SRC}
	$(JDK) -d classes ${SRC}
	$(JAR) cfe Program.jar Program *.class

tests: all
	$(JVM) -cp classes Program ../puzzles/demo.txt 1
	$(JVM) -cp classes Program ../puzzles/hard0.txt 1
	$(JVM) -cp classes Program ../puzzles/empty.txt 1

clean:
	rm *.class Program.jar
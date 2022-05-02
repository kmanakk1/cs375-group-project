JDK = javac
JVM = java
JAR = jar
SRC = *.java

all: ${SRC}
	$(JDK) -d classes ${SRC}
	$(JAR) cfe Program.jar Program classes/*.class

tests: all
	$(JVM) -cp classes Program ../puzzles/hard0.txt 1
	$(JVM) -cp classes Program ../puzzles/easy1.txt 0
	$(JVM) -cp classes Program ../puzzles/empty.txt 1

clean:
	rm classes/*.class Program.jar
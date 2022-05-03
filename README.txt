Java
cd src/
make                                            # compile program
java -jar Program.jar ../puzzles/easy1.txt 1    # run program on easy1.txt puzzle

usage of program:
java -jar Program.jar <puzzle file> <algorithm to use>

Where options for algorithm are:
0: naive bruteforce solver
1: basic backtracking solver
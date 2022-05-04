Java
cd src/
./compile.sh                                    # compile program
./run.sh puzzles/easy1.txt 1                    # run program on easy1.txt puzzle

usage of program:
./run.sh <puzzle file> <algorithm to use>

Where options for algorithm are:
0: naive solver
1: basic backtracking solver
2: bitmask backtracking solver

to run tests:
./tests.sh
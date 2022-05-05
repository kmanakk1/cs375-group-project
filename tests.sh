#!/bin/sh
PUZZLES="$(ls puzzles/|grep .txt)"

testSudoku() {
    FILE=$1
    for i in 0 1 2; do
        ./run.sh ${FILE} ${i} noprint
    done
}

for i in $PUZZLES ; do testSudoku puzzles/$i ; echo; done
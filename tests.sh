#!/bin/sh
PUZZLES="$(ls puzzles/|grep .txt)"

testSudoku() {
    FILE=$1
    for i in 0 1 2; do
        #echo "Running solver [${i}] against puzzle: ${FILE}"
        ./run.sh ${FILE} ${i} noprint
        #echo -n 'Press enter'
        #read dummy
    done
}

for i in $PUZZLES ; do testSudoku puzzles/$i ; done
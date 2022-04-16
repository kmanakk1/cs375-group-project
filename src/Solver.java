import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {
    Sudoku board;

    Solver() {
        board = new Sudoku();
    }

    // read in puzzle
    void loadPuzzle(String filename) {
        board.loadPuzzle(filename);
    }

    void printBoard() {
        for(int i=0; i<board.boardSize; i++) {
            for(int j=0; j<board.boardSize; j++) {
                System.out.print(board.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean naiveSolver(int row, int col) {
        // check if we have reached the end
        if((row == board.boardSize-1) && (col == board.boardSize))
            return true;
        
        // if we reach end of a col, move to next row
        if(col == board.boardSize) {
            row++;
            col = 0;
        }

        // current pos already has an item
        if(board.board[row][col] > 0)
            return naiveSolver(row, col+1);
        
        for(int number = 1; number <= board.boardSize; number++) {
            if(PromisingFunctions.isPromising(board,row, col, number)) {
                // use number in current row
                board.board[row][col] = number;

                // continue solving
                if(naiveSolver(row, col+1))
                    return true;
            }

            // the number did not work out, try again
            board.board[row][col] = 0;
        }

        // none of the numbers worked, no solution possible
        return false;
    }

    /*boolean findEmpty(int row, int col) {
        for(row = 0; row < boardSize; row++)
            for(col = 0; col < boardSize; col++)
                if(board[row][col] == 0)
                    return true;
        return false;
    }*/

    boolean backtrackSolver() {
        int row = 0, col = 0;
        boolean done = true;
        // check if we have reached the end
        for(int i = 0; i < board.boardSize; i++) {
            for(int j = 0; j < board.boardSize; j++) {
                if(board.board[i][j] == 0) {
                    row=i; col=j;
                    done = false;
                    break;
                }
            }
            if(!done)
                break;
        }

        if(done)
            return true;
        
        for(int number=1; number<=9; number++) {
            if(PromisingFunctions.isPromising(board, row, col, number)) {
                board.board[row][col] = number;

                // recursive call
                if(backtrackSolver())
                    return true;
                
                board.board[row][col] = 0;
            }
        }

        return false;
    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {
    int boardSize = 9;               // default sudoku size: 9
    int board[][];                   // our 2d matrix "sudoku board"

    Solver() {
        board = new int[boardSize][boardSize];
    }

    // read in puzzle
    void loadPuzzle(String filename) {
        int currentRow=0;
        try {
            File puzzleFile = new File(filename);
            Scanner puzzleReader = new Scanner(puzzleFile);

            // get board size for problem
            /*String boardSizeStr = puzzleReader.nextLine();
            boardSize = Integer.parseInt(boardSizeStr);
            board = new int[boardSize][boardSize]; */

            // read in board[][]
            while(puzzleReader.hasNextLine()) {
                String data = puzzleReader.nextLine();
                String[] tokenizedRow = data.split(" ");
                for(int j=0; j<boardSize; j++) {
                    board[currentRow][j] = Integer.parseInt(tokenizedRow[j]);
                }
                currentRow++;
            }
        } catch (Exception e) {
            System.out.println("Error reading puzzle file '" + filename + "'");
            System.exit(1);
        }
    }

    void printBoard() {
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean usedInBox(int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board[row + startRow][col + startCol] == number)
                    return true;
        return false;
    }

    boolean isLegal(int row, int col, int number) {
        // check row
        for(int i=0; i<boardSize; i++)
            if(board[row][i] == number)
                return false;
    
        // check col
        for(int i=0; i<boardSize; i++)
            if(board[i][col] == number)
                return false;

        // check if we used this number in this box already
        if(usedInBox(row - row % 3, col - col % 3, number))
            return false;

        // all the checks passed, result is legal
        return true;
    }

    boolean naiveSolver(int row, int col) {
        // check if we have reached the end
        if((row == boardSize-1) && (col == boardSize))
            return true;
        
        // if we reach end of a col, move to next row
        if(col == boardSize) {
            row++;
            col = 0;
        }

        // current pos already has an item
        if(board[row][col] > 0)
            return naiveSolver(row, col+1);
        
        for(int number = 1; number <= boardSize; number++) {
            if(isLegal(row, col, number)) {
                // use number in current row
                board[row][col] = number;

                // continue solving
                if(naiveSolver(row, col+1))
                    return true;
            }

            // the number did not work out, try again
            board[row][col] = 0;
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
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(board[i][j] == 0) {
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
            if(isLegal(row, col, number)) {
                board[row][col] = number;

                // recursive call
                if(backtrackSolver())
                    return true;
                
                board[row][col] = 0;
            }
        }

        return false;
    }

}
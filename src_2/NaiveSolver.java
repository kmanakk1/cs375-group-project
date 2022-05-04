public class NaiveSolver implements ISolver {
    Sudoku board;

    public boolean solve(Sudoku s) {
        this.board = s;
        return naiveSolver(0, 0);
    }

    boolean naiveSolver(int row, int col) {
        // check if we have reached the end
        if((row == Sudoku.boardSize-1) && (col == Sudoku.boardSize))
            return true;
        
        // if we reach end of a col, move to next row
        if(col == Sudoku.boardSize) {
            row++;
            col = 0;
        }

        // current pos already has an item
        if(board.board[row][col] > 0)
            return naiveSolver(row, col+1);
        
        for(int number = 1; number <= Sudoku.boardSize; number++) {
            if(isPromising(board,row, col, number)) {
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

    private static boolean isPromising(Sudoku board, int row, int col, int number) {
        // check row
        for(int i=0; i<Sudoku.boardSize; i++)
            if(board.board[row][i] == number)
                return false;
    
        // check col
        for(int i=0; i<Sudoku.boardSize; i++)
            if(board.board[i][col] == number)
                return false;

        // check if we used this number in this box already
        if(board.usedInBox(row - row % 3, col - col % 3, number))
            return false;

        // all the checks passed, result is legal
        return true;
    }
}

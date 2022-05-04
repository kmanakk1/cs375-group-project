public class NaiveSolver implements ISolver {
    Sudoku board;

    public boolean solve(Sudoku s) {
        this.board = s;
        return naiveSolver();
    }

    boolean naiveSolver() {
        int row = 0, col = 0;
        boolean done = true;
        // check if we have reached the end
        for(int i = 0; i < Sudoku.boardSize; i++) {
            for(int j = 0; j < Sudoku.boardSize; j++) {
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
        
        for(int number = 1; number <= Sudoku.boardSize; number++) {
            if(isLegal(board,row, col, number)) {
                // use number in current row
                board.board[row][col] = number;

                // continue solving
                if(naiveSolver())
                    return true;
            }

            // the number did not work out, try again
            board.board[row][col] = 0;
        }

        // none of the numbers worked, no solution possible
        return false;
    }

    private static boolean isLegal(Sudoku board, int row, int col, int number) {
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

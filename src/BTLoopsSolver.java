public class BTLoopsSolver implements ISolver {

    Sudoku board;

    public boolean solve(Sudoku s) {
        this.board = s;
        return backtrackSolver(0,0);
    }
    
    boolean backtrackSolver(int row, int col) {
        
        // check if we have reached the end of a row
        if (col == Sudoku.boardSize) {
            col = 0;
            row++;
            if(row == Sudoku.boardSize)
                return true;
        }

        // skip filled spots
        if (board.board[row][col] != 0)
            return backtrackSolver(row, col + 1);
        
        // main logic
        for(int number=1; number<=9; number++) {
            if(isPromising(board, row, col, number)) {
                board.board[row][col] = number;

                // recursive call
                if(backtrackSolver(row, col+1))
                    return true;
                
                board.board[row][col] = 0;
            }
        }

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

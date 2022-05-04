public class BTLoopsSolver implements ISolver {

    Sudoku board;

    public boolean solve(Sudoku s) {
        this.board = s;
        return backtrackSolver();
    }
    
    boolean backtrackSolver() {
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
        
        for(int number=1; number<=9; number++) {
            if(isPromising(board, row, col, number)) {
                board.board[row][col] = number;

                // recursive call
                if(backtrackSolver())
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

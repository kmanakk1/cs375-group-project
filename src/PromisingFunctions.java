public class PromisingFunctions {

    static boolean isLegal(Sudoku board, int row, int col, int number) {
        // check row
        for(int i=0; i<board.boardSize; i++)
            if(board.board[row][i] == number)
                return false;
    
        // check col
        for(int i=0; i<board.boardSize; i++)
            if(board.board[i][col] == number)
                return false;

        // check if we used this number in this box already
        if(board.usedInBox(row - row % 3, col - col % 3, number))
            return false;

        // all the checks passed, result is legal
        return true;
    }
    
}

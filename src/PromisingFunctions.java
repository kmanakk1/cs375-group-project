public class PromisingFunctions {
    // we can setup the state integer ?
    // and then a wrapper function to choose the current legal function.
    public static int state = 0;

    public static boolean isPromising(Sudoku board, int row, int col, int number) {
        switch (state) {
            case 1: return isLegal(board, row, col, number);
            // ...
            default: throw new IllegalStateException("Invalid state " + state);
        }
    }

    // Or maybe find a way to store a function pointer and at the start of the program choose a 
    // function we wanna use. Hence use that variable only.

    private static boolean isLegal(Sudoku board, int row, int col, int number) {
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

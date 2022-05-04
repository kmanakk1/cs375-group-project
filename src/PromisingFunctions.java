public class PromisingFunctions {
    // we can setup the state integer ?
    // and then a wrapper function to choose the current legal function.
    public static int state = 0;

    public static boolean isPromising(Sudoku board, int row, int col, int number) {
        switch (state) {
            case 1: return isLegalLoops(board, row, col, number);
            // ...
            default: throw new IllegalStateException("Invalid state " + state);
        }
    }

    // Or maybe find a way to store a function pointer and at the start of the program choose a 
    // function we wanna use. Hence use that variable only.

    // basic promising function, check entire row, column, and neighboring
    // 3x3 box for number.  if we don't encounter it, then it is promising.
    private static boolean isLegalLoops(Sudoku board, int row, int col, int number) {
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
    
    int[] rows = new int[Sudoku.boardSize];
    int[] cols = new int[Sudoku.boardSize];
    int[] boxes = new int[Sudoku.boardSize];
    boolean isSet = false;

    static int getBox(int i, int j) {
        return i / 3 * 3 + j / 3;
    }

}

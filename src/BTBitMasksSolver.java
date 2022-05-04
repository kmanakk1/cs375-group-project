public class BTBitMasksSolver implements ISolver {
    
    Sudoku board;

    int[] rows = new int[Sudoku.boardSize];
    int[] cols = new int[Sudoku.boardSize];
    int[] boxes = new int[Sudoku.boardSize];

    public boolean solve(Sudoku s) {
        this.board = s;
        setInitialMasks();
        return backtrack(0, 0);
    }

    boolean backtrack(int i, int j) {
        if (i == Sudoku.boardSize - 1 && j == Sudoku.boardSize)
            return true;
        
        if (j == Sudoku.boardSize) {
            j = 0;
            i++;
        }

        if (board.board[i][j] != 0) 
            return backtrack(i, j + 1);

        for (int num = 1; num <= Sudoku.boardSize; num++) {
            if (isPromising(i, j, num)) {
                board.board[i][j] = num;
                rows[i] |= 1 << num;
                cols[j] |= 1 << num;
                boxes[getBox(i, j)] |= 1 << num;

                if (backtrack(i, j + 1))
                    return true;

                rows[i] &= ~(1 << num);
                cols[j] &= ~(1 << num);
                boxes[getBox(i, j)] &= ~(1 << num);
            }

            board.board[i][j] = 0;
        }
        
        return false;
    }

    static int getBox(int i, int j) {
        return i / 3 * 3 + j / 3;
    }

    void setInitialMasks() {
        for (int i = 0; i < Sudoku.boardSize; i++) {
            for (int j = 0; j < Sudoku.boardSize; j++) {
                rows[i] |= 1 << board.board[i][j];
                cols[j] |= 1 << board.board[i][j];
                boxes[getBox(i, j)] |= 1 << board.board[i][j];
            }
        }
    }

    boolean isPromising(int i, int j, int num) {
        return ((rows[i] >> num) & 1) == 0
            && ((cols[j] >> num) & 1) == 0
            && ((boxes[getBox(i, j)] >> num) & 1) == 0;
    }
}

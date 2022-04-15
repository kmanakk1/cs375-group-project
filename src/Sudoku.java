import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
    int boardSize = 9;               // default sudoku size: 9
    int board[][];                   // our 2d matrix "sudoku board"

    Sudoku() {
        board = new int[boardSize][boardSize];
    }

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
            puzzleReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading puzzle file '" + filename + "'");
            System.exit(1);
        }
    }

    boolean usedInBox(int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board[row + startRow][col + startCol] == number)
                    return true;
        return false;
    }
}

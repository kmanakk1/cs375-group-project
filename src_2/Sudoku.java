import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    
    static int boardSize = 9;
    
    int board[][];

    Sudoku() {
        board = new int[boardSize][boardSize];
    }

    void readSudokyFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        for (int i = 0; i < boardSize; i++) {
            String[] numbers = lines.get(i).split(" ");
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(numbers[j]);
            }
        }
    }

    void printBoard() {
        for (int[] row : board) {
            for (int number : row) {
                System.out.print(number + " ");
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
}

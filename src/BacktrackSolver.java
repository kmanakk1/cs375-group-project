import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BacktrackSolver {
    int gridSize = 9;               // default sudoku size: 9
    int grid[][];                   // our 2d matrix "sudoku board"

    BacktrackSolver() {
        //grid = new int[gridSize][gridSize];
    }

    // read in puzzle
    void loadPuzzle(String filename) {
        int currentRow=0;
        try {
            File puzzleFile = new File(filename);
            Scanner puzzleReader = new Scanner(puzzleFile);

            // get grid size for problem
            String gridSizeStr = puzzleReader.nextLine();
            gridSize = Integer.parseInt(gridSizeStr);
            grid = new int[gridSize][gridSize]; 

            // read in grid[][]
            while(puzzleReader.hasNextLine()) {
                String data = puzzleReader.nextLine();
                String[] tokenizedRow = data.split(" ");
                for(int j=0; j<gridSize; j++) {
                    grid[currentRow][j] = Integer.parseInt(tokenizedRow[j]);
                }
                currentRow++;
            }
        } catch (Exception e) {
            System.out.println("Error reading puzzle file '" + filename + "'");
            System.exit(1);
        }
    }

    void printGrid() {
        for(int i=0; i<gridSize; i++) {
            for(int j=0; j<gridSize; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
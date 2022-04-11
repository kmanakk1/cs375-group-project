import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {
    int gridSize = 9;               // default sudoku size: 9
    int grid[][];                   // our 2d matrix "sudoku board"

    Solver() {
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

    boolean usedInBox(int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row + startRow][col + startCol] == number)
                    return true;
        return false;
    }

    boolean isLegal(int row, int col, int number) {
        // check row
        for(int i=0; i<gridSize; i++)
            if(grid[row][i] == number)
                return false;
    
        // check col
        for(int i=0; i<gridSize; i++)
            if(grid[i][col] == number)
                return false;

        // check if we used this number in this box already
        if(usedInBox(row - row % 3, col - col % 3, number))
            return false;

        // all the checks passed, result is legal
        return true;
    }

    boolean naiveSolver(int row, int col) {
        // check if we have reached the end
        if((row == gridSize-1) && (col == gridSize))
            return true;
        
        // if we reach end of a col, move to next row
        if(col == gridSize) {
            row++;
            col = 0;
        }

        // current pos already has an item
        if(grid[row][col] > 0)
            return naiveSolver(row, col+1);
        
        for(int number = 1; number <= gridSize; number++) {
            if(isLegal(row, col, number)) {
                // use number in current row
                grid[row][col] = number;

                // continue solving
                if(naiveSolver(row, col+1))
                    return true;
            }

            // the number did not work out, try again
            grid[row][col] = 0;
        }

        // none of the numbers worked, no solution possible
        return false;
    }
}
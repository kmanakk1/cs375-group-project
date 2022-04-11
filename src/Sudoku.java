public class Sudoku {

    public static void main(String[] args) {
        Solver sudoku = new Solver();
        if(args.length < 1) {
            System.out.println("Usage: solver <puzzle file>");
            System.exit(1);
        }

        // load input
        sudoku.loadPuzzle(args[0]);

        sudoku.naiveSolver(0, 0);
        // TODO: call solver here
        // print output
        sudoku.printGrid();
    }
}
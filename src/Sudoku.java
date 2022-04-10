public class Sudoku {

    public static void main(String[] args) {
        BacktrackSolver sudoku = new BacktrackSolver();
        if(args.length < 1) {
            System.out.println("Usage: solver <puzzle file>");
            System.exit(1);
        }

        // load input
        sudoku.loadPuzzle(args[0]);

        // TODO: call solver here
        // print output
        sudoku.printGrid();
    }
}
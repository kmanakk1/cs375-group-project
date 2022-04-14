public class Sudoku {

    public static void main(String[] args) {
        Solver sudoku = new Solver();
        boolean solved = false;
        if(args.length < 2) {
            System.out.println("Usage: solver <puzzle file> <mode>");
            System.exit(1);
        }

        // load input
        sudoku.loadPuzzle(args[0]);

        long start = System.currentTimeMillis();

        switch(args[1]) {
            case "0":
                solved = sudoku.naiveSolver(0, 0);
                break;
            case "1":
                solved = sudoku.backtrackSolver();
                break;
            default:
                System.out.println("valid modes: 0, 1");
        }
        long end = System.currentTimeMillis();

        float timeTaken = (end-start)/1000f;

        // print output
        System.out.println("==========================");
        System.out.format("Algo: %s; Time: %05f\n", args[1], timeTaken);
        System.out.println("Solution: ================");
        if(solved) sudoku.printBoard();
    }
}
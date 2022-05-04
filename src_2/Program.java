public class Program {
    
    public static void main(String[] args) {
        Program program = new Program();
        Sudoku s = new Sudoku();
        s.readSudokyFromFile(args[0]);
        
        s.printBoard();
        System.out.println("-----------------");

        ISolver solver;
        switch (Integer.parseInt(args[1])) {
            case 0: solver = new NaiveSolver();
            break;
            case 1: solver = new BTLoopsSolver();
            break;
            case 2: solver = new BTBitMasksSolver();
            break;
            default: throw new IllegalArgumentException("Invalid solver.");
        }

        System.out.println("Took time: " + (program.timeSolver(solver, s)/1000000000.0));
        System.out.println("-----------------");
        
        s.printBoard();
    }

    public long timeSolver(ISolver solver, Sudoku s) {
        long start = System.nanoTime();
        solver.solve(s);
        return System.nanoTime() - start;
    }
}

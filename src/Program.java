public class Program {
    
    public static void main(String[] args) {
        boolean print = true;

        if (args.length < 2) {
            System.err.println("[USAGE]: ./run.sh <sudoku file> <0|1|2> [noprint]");
            System.exit(-1);
        }

        Program program = new Program();
        Sudoku s = new Sudoku();
        s.readSudokyFromFile(args[0]);

        int choice = -1;
        try {
            choice = Integer.parseInt(args[1]); 
        } catch (Exception e) {
            System.err.println("[USAGE]: ./run.sh <sudoku file> <0|1|2> [noprint]");
            System.exit(-1);
        }
        ISolver solver;
        switch (choice) {
            case 0: solver = new NaiveSolver();
            break;
            case 1: solver = new BTLoopsSolver();
            break;
            case 2: solver = new BTBitMasksSolver();
            break;
            default: throw new IllegalArgumentException("Invalid solver.");
        }
        
        if(args.length >= 3)
            if(args[2].equals("noprint"))
                print = false;

        if(print) {
            s.printBoard();
            System.out.println("-----------------");

            System.out.println("Took time: " + (program.timeSolver(solver, s)/1000000000.0));
            System.out.println("-----------------");

            s.printBoard();
        } else {
            System.out.println("solving " + args[0] + " using mode " + args[1] + " took time: " + (program.timeSolver(solver, s)/1000000000.0));
        }
        
    }

    public long timeSolver(ISolver solver, Sudoku s) {
        long start = System.nanoTime();
        solver.solve(s);
        return System.nanoTime() - start;
    }
}

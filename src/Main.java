



public class Main {

    public static void main(String[] args) {

        // ── 1. Resolve the maze file path ─────────────────────────────────
        String filename;
        if (args.length >= 1) {
            filename = args[0];
        } else {
            // Default demo file so the program still runs when launched
            // from an IDE without arguments.
            filename = "maze.csv";
            System.out.println("No file argument supplied – trying default: " + filename);
            System.out.println("Usage: java Main <maze-file>");
            System.out.println();
        }

        // ── 2. Load the maze from disk ─────────────────────────────────────
        GridReader reader = new GridReader();
        Maze maze = reader.readMaze(filename);

        if (maze == null) {
            System.out.println("Failed to load maze. Exiting.");
            System.exit(1);
        }

        System.out.println("Maze loaded successfully.");
        System.out.printf("  Spaces : %d%n", maze.getSpaceCount());
        System.out.printf("  Start  : %s%n", maze.getStart());
        System.out.printf("  End    : %s%n", maze.getEnd());
        System.out.println();

        // ── 3. Print the unsolved maze ─────────────────────────────────────
        System.out.println("=== Unsolved Maze ===");
        printMaze(maze);

        // ── 4. Solve the maze ──────────────────────────────────────────────
        MazeSolver solver = new MazeSolver(maze);

        long startTime = System.currentTimeMillis();
        solver.solve();
        long elapsed = System.currentTimeMillis() - startTime;

        System.out.println();

        // ── 5. Report the result ───────────────────────────────────────────
        // Check whether the end space ended up on the solution path.
        Space endSpace = maze.getSpace(maze.getEnd());
        boolean solved = (endSpace != null && endSpace.onPath);

        if (solved) {
            System.out.println("Maze solved! (" + elapsed + " ms)");
        } else {
            System.out.println("No solution exists for this maze. (" + elapsed + " ms)");
        }

        System.out.println();
        System.out.println("=== Solved Maze (* = path, x = dead end) ===");
        printMaze(maze);
    }

    // ── Helpers ────────────────────────────────────────────────────────────

    private static void printMaze(Maze maze) {
        CacyLinkedList<Space> spaces = maze.getSpaces();
        if (spaces == null) {
            System.out.println("  (maze has no printable spaces)");
            return;
        }

        // Find grid dimensions
        int maxRow = 0, maxCol = 0;
        spaces.initIterator();
        while (spaces.hasNext()) {
            Space space = spaces.next();
            if (space.getRow() > maxRow) maxRow = space.getRow();
            if (space.getCol() > maxCol) maxCol = space.getCol();
        }

        // Fill a 2D char grid
        char[][] grid = new char[maxRow + 1][maxCol + 1];
        spaces.initIterator();
        while (spaces.hasNext()) {
            Space space = spaces.next();
            grid[space.getRow()][space.getCol()] = symbolFor(space);
        }

        // Print row by row
        for (int r = 0; r <= maxRow; r++) {
            for (int c = 0; c <= maxCol; c++) {
                System.out.print(grid[r][c]);
                System.out.print(' '); // spacing between cells
            }
            System.out.println();
        }
    }

    /** Returns the display character for a single Space. */
    private static char symbolFor(Space space) {
        switch (space.getType()) {
            case START: return 'S';
            case END:   return 'E';
            default:
                if (space.onPath)      return '*';
                if (space.isDeadEnd)   return 'x';
                return space.getAdjacentCount() == 0 ? '#' : 'O';
        }
    }
}
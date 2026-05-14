public class Main {
    public static void main(String[] args) {

        // ── 1. Resolve the maze file path ─────────────────────────────────
        String filename;
        if (args.length >= 1) {
            filename = args[0];
        } else {
            // Default demo file so the program still runs when launched
            // from an IDE without arguments.
            filename = "Resources/sample_maze_no_soln.txt";
            System.out.println("No file argument supplied – trying default: " + filename);
            System.out.println("Usage: java Main <maze-file>");
            System.out.println();
        }

        // ── 2. Load the maze from disk and try to solve ─────────────────────
        Maze maze = GridReader.readMaze(filename);
        MazeSolver solver = new MazeSolver(maze);

        // ── 3. Report the result ───────────────────────────────────────────
        if (solver.solve()){
            System.out.println(new GridMazeOutput(maze).output(false));
            System.out.println(new GridMazeOutput(maze).output(true));
        }
        else {
            System.out.println("No path found");
        }
    }
}
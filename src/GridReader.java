import java.io.File;
import java.util.Scanner;

public class GridReader {
    public static final int MAX_ROWS = 50;
    public static final int MAX_COLS = 50;

    public Maze readMaze(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // First pass: read all tokens into a 2D grid
            String[][] grid = new String[MAX_ROWS][MAX_COLS];
            int rows = 0;
            int cols = 0;
            int sCount = 0, eCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split(",");

                // Validate consistent row width
                if (cols == 0) {
                    cols = tokens.length;
                } else if (tokens.length != cols) {
                    System.out.println("Error: Rows have inconsistent width.");
                    scanner.close();
                    return null;
                }

                for (int c = 0; c < cols; c++) {
                    grid[rows][c] = tokens[c].trim();
                    if (grid[rows][c].equals("S")) sCount++;
                    if (grid[rows][c].equals("E")) eCount++;
                }
                rows++;
            }
            scanner.close();

            // Validate S and E counts
            if (sCount != 1 || eCount != 1) {
                System.out.println("Error: Maze must have exactly one S and one E.");
                return null;
            }

            // Second pass: create Space objects
            Maze maze = new Maze();
            Space[][] spaceGrid = new Space[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Space s = new Space(grid[r][c], r, c);
                    spaceGrid[r][c] = s;
                    maze.addSpace(s);
                }
            }

            // Third pass: link adjacencies (up, down, left, right)
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Space current = spaceGrid[r][c];
                    if (!current.isOpen()) continue;

                    if (r > 0       && spaceGrid[r-1][c].isOpen()) current.addAdjacent(spaceGrid[r-1][c]); // up
                    if (r < rows-1  && spaceGrid[r+1][c].isOpen()) current.addAdjacent(spaceGrid[r+1][c]); // down
                    if (c > 0       && spaceGrid[r][c-1].isOpen()) current.addAdjacent(spaceGrid[r][c-1]); // left
                    if (c < cols-1  && spaceGrid[r][c+1].isOpen()) current.addAdjacent(spaceGrid[r][c+1]); // right
                }
            }

            return maze;

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
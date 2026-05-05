import jdk.jshell.spi.SPIResolutionException;

import java.io.File;
import java.io.FileNotFoundException;
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
            int rowCount = 0;
            int columnCount = 0;
            int startCount = 0, endCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split(",");

                // Validate consistent row width
                if (columnCount == 0) {
                    columnCount = tokens.length;
                } else if (tokens.length != columnCount) {
                    System.out.println("Error: Rows have inconsistent width.");
                    scanner.close();
                    return null;
                }

                for (int column = 0; column < columnCount; column++) {
                    grid[rowCount][column] = tokens[column].trim();
                    if (grid[rowCount][column].equals("S")) startCount++;
                    if (grid[rowCount][column].equals("E")) endCount++;
                }
                rowCount++;
            }
            scanner.close();

            // Validate S and E counts
            if (startCount != 1 || endCount != 1) {
                System.out.println("Error: Maze must have exactly one S and one E.");
                return null;
            }

            // Second pass: create Space objects
            Space[][] spaceGrid = new Space[rowCount][columnCount];

            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    String spaceData = grid[row][column];
                    if (!spaceData.equalsIgnoreCase("X")) {
                        Space.Type type = null;
                        if (grid[row][column].equalsIgnoreCase("S")) type = Space.Type.START;
                        else if (grid[row][column].equalsIgnoreCase("E")) type = Space.Type.END;
                        Space space = new Space(type, new GridID(row, column));
                        spaceGrid[row][column] = space;
                    }
                }
            }

            // Third pass: link adjacencies (up, down, left, right) and to load spaces into a list
            CacyLinkedList<Space> spaceList = new CacyLinkedList<>();
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    Space current = spaceGrid[row][column];
                    if (current == null) continue;

                    if (indexInBounds(row - 1, spaceGrid.length) && spaceGrid[row-1][column] != null) current.addAdjacent(spaceGrid[row-1][column].getId()); // up
                    if (indexInBounds(row + 1, spaceGrid.length) && spaceGrid[row+1][column] != null) current.addAdjacent(spaceGrid[row+1][column].getId()); // down

                    if (indexInBounds(column - 1, spaceGrid[row].length) && spaceGrid[row][column-1] != null) current.addAdjacent(spaceGrid[row][column-1].getId()); // left
                    if (indexInBounds(column + 1, spaceGrid[row].length) && spaceGrid[row][column+1] != null) current.addAdjacent(spaceGrid[row][column+1].getId()); // right

                    spaceList.add(current);
                }
            }

            return new Maze(spaceList);

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    private boolean indexInBounds(int value, int arrayLength){
        return 0 <= value && value < arrayLength;
    }
}
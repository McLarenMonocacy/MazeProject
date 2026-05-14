import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridReaderTest {

    @Test
    void readMaze() {
        assertNull(GridReader.readMaze("NOTAREALFILENAME;;;; "), "invalid file not read a null");
        assertNull(GridReader.readMaze("Resources/sample_maze_no_startend.txt"), "Maze without start and end not rad as null");
        Maze maze = GridReader.readMaze("Resources/sample_maze.txt");
        assertEquals(22, maze.getSpaceCount(), "Read in wrong number of spaces");
        assertTrue(maze.getStart().equals(new GridID(1,0)), "Start has wrong gridID");
        assertTrue(maze.getEnd().equals(new GridID(5,7)), "End has wrong gridID");
    }
}
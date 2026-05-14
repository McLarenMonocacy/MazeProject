import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeSolverTest {

    @Test
    void solve() {
        assertTrue(new MazeSolver(GridReader.readMaze("Resources/sample_maze.txt")).solve());
        assertTrue(new MazeSolver(GridReader.readMaze("Resources/sample_maze2.txt")).solve());
        assertTrue(new MazeSolver(GridReader.readMaze("Resources/sample_maze3.txt")).solve());
        assertFalse(new MazeSolver(GridReader.readMaze("Resources/sample_maze_no_soln.txt")).solve());
    }
}
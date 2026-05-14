import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    Maze maze = null;
    @BeforeEach
    void preTest(){
        CacyLinkedList<Space> spaces = new CacyLinkedList<>();
        spaces.add(new Space(Space.Type.END,new GridID(0,0)));
        spaces.add(new Space(Space.Type.START,new GridID(1,1)));
        spaces.add(new Space(null, new GridID(2,2)));
        maze = new Maze(spaces);
    }

    @Test
    void getSpace() {
        assertTrue(maze.getSpace(new GridID(0,0)).getId().equals(new GridID(0,0)), "Unable to find space");
        assertTrue(maze.getSpace(new GridID(1,1)).getId().equals(new GridID(1,1)), "Unable to find space");
        assertTrue(maze.getSpace(new GridID(2,2)).getId().equals(new GridID(2,2)), "Unable to find space");
        assertNull(maze.getSpace(new GridID(1, 2)), "Found space that doesn't exist");
    }

    @Test
    void getStart() {
        assertEquals(Space.Type.START, maze.getSpace(maze.getStart()).getType(), "Start not retried properly");
    }

    @Test
    void getEnd() {
        assertEquals(Space.Type.END, maze.getSpace(maze.getEnd()).getType(), "End not retried properly");
    }

    @Test
    void getSpaceCount() {
        assertEquals(3, maze.getSpaceCount(), "Incorrect count detected");
    }
}
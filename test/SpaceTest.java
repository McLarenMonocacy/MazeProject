import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void getId() {
        SpaceUUID id = new SpaceUUID();
        Space space = new Space(null, id);
        assertEquals(id, space.getId(), "Id not saved");
    }

    @Test
    void adjacentSpaces(){
        Space space = new Space(null, new SpaceUUID());
        assertEquals(0, space.getAdjacent().length(), "Adjacent spaces not starting as zero");
        space.addAdjacent(new SpaceUUID());
        space.addAdjacent(new SpaceUUID());
        space.addAdjacent(new SpaceUUID());
        assertEquals(3, space.getAdjacent().length(), "Adjacent spaces not at 3 after adding 3");
    }

    @Test
    void type(){
        Space spaceOpen = new Space(null, new SpaceUUID());
        Space spaceStart = new Space(Space.Type.START, new SpaceUUID());
        Space spaceEnd = new Space(Space.Type.END, new SpaceUUID());

        assertNull(spaceOpen.getType(), "Open space doesn't have null type");
        assertEquals(Space.Type.START,spaceStart.getType(), "Start space doesn't have START type");
        assertEquals(Space.Type.END,spaceEnd.getType(), "End space doesn't have END type");

    }

    @Test
    void onPath(){
        Space space = new Space(null,new SpaceUUID());
        assertFalse(space.onPath, "onPath should start false");
        assertFalse(space.isDeadEnd, "isDeadEnd should start false");
        space.onPath = true;
        space.isDeadEnd = true;
        assertTrue(space.onPath, "onPath should be true");
        assertTrue(space.isDeadEnd, "isDeadEnd should be true");
    }


}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void getId() {
        SpaceUUID id = new SpaceUUID();
        Space space = new Space(Space.Type.OPEN, id);
        assertEquals(id, space.getId(), "Id not saved");
    }

    @Test
    void adjacentSpaces(){
        Space space = new Space(Space.Type.OPEN, new SpaceUUID());
        assertEquals(0, space.getAdjacent().length(), "Adjacent spaces not starting as zero");
        space.addAdjacent(new SpaceUUID());
        space.addAdjacent(new SpaceUUID());
        space.addAdjacent(new SpaceUUID());
        assertEquals(3, space.getAdjacent().length(), "Adjacent spaces not at 3 after adding 3");
    }


}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void getId() {
        SpaceUUID id = new SpaceUUID();
        Space space = new Space(Space.Type.OPEN, id);
        assertEquals(id, space.getId(), "Id not saved");
    }


}
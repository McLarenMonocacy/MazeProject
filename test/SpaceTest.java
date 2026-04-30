import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void getId() {
        SpaceUUID id = new SpaceUUID();
        Space space = new Space(id);
        assertEquals(id, space.getId(), "Id not saved");
    }

    @Test
    void getTags() {
        CacyLinkedList<Space.Tag> tags = new CacyLinkedList<>();
        tags.add(Space.Tag.START);
        Space space = new Space(new SpaceUUID(), tags);
        assertEquals(tags, space.getTags(), "Tag list not saved");
    }
}
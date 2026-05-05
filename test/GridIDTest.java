import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GridIDTest {

    @Test
    void testEquals() {
        SpaceID id1a = new GridID(1,2); //A gridID
        SpaceID id1b = new GridID(1,2); //A gridID with the same values
        SpaceID id2 = new GridID(2,1);  //A gridID with different values
        SpaceID id0 = new SpaceUUID();              //Not a gridID

        assertTrue(id1a.equals(id1b), "GridID's with equal value not equal");
        assertFalse(id1a.equals(id2), "GridID's with unequal values equal");
        assertFalse(id0.equals(id2), "Different SpaceID types considered equal");

    }
}
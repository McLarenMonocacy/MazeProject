import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacyLinkedListTest {

    static CacyLinkedList<Integer> list;

    @BeforeEach
    void setup(){
        list = new CacyLinkedList<>();
    }

    @Test
    void add() {
        list.add(1);
        assertEquals(1,list.length(), "List with one object doesn't have length one");
        list.add(1);//add equal
        assertEquals(2,list.length(), "List with two objects doesn't have length two");
        list.add(2);//add unequal
        assertEquals(3,list.length(), "List with three objects doesn't have length three");
        list.add(1);//add equal to first again
        assertEquals(4,list.length(), "List with four objects doesn't have length four");
        list.add(null);//add null object
        assertEquals(4,list.length(), "Null object was added to the list");
    }

    @Test
    void addFirst(){
        list.add(1);
        list.add(2);
        list.addFirst(3);
        assertEquals(3, list.first(), "Object added to front not first object");
        assertNotEquals(1, list.first(), "Object added first was still first object");
        assertEquals(2, list.last(), "Unexpected object at end of list");
        assertNotEquals(3, list.last(), "Object added to front was still put at the end");

        list.addFirst(null);
        assertEquals(3, list.length(), "Adding null should not change the list");
        assertEquals(3, list.first(), "Adding null should not change the list");
        assertEquals(2, list.last(), "Adding null should not change the list");

    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        assertEquals(2, list.length(), "List starting with three objects with one removed doesn't have length two");
        assertTrue(list.contains(1), "List doesn't contains non removed object (first)");
        assertFalse(list.contains(2), "List still contains the removed object");
        assertTrue(list.contains(3), "List doesn't contains non removed object (last)");

        list.add(4);
        list.remove(4);
        assertEquals(2, list.length(), "List starting with three objects with one removed doesn't have length two");
        assertTrue(list.contains(1), "List doesn't contains non removed object (first)");
        assertTrue(list.contains(3), "List doesn't contains non removed object (middle)");
        assertFalse(list.contains(4), "List still contains the removed object (last)");

        list.add(5);
        list.remove(1);
        assertEquals(2, list.length(), "List starting with three objects with one removed doesn't have length two");
        assertFalse(list.contains(1), "List still contains the removed object (first)");
        assertTrue(list.contains(3), "List doesn't contains non removed object (middle)");
        assertTrue(list.contains(5), "List doesn't contains non removed object (last)");

        list.add(6);
        list.remove(null);
        assertEquals(3, list.length(), "Removing null shouldn't change the list size");
        assertTrue(list.contains(3), "List doesn't contains non removed object (first)");
        assertTrue(list.contains(5), "List doesn't contains non removed object (middle)");
        assertTrue(list.contains(6), "List doesn't contains non removed object (last)");

        list.remove(7);
        assertEquals(3, list.length(), "Removing an object not in the list shouldn't change the list size");
        assertTrue(list.contains(3), "List doesn't contains non removed object (first)");
        assertTrue(list.contains(5), "List doesn't contains non removed object (middle)");
        assertTrue(list.contains(6), "List doesn't contains non removed object (last)");
    }

    @Test
    void removeFirst() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeFirst();
        assertEquals(2, list.length(), "List starting with three objects with one removed doesn't have length two");
        assertFalse(list.contains(1), "List still contains the removed object");
        assertTrue(list.contains(2), "List doesn't contains non removed object (middle)");
        assertTrue(list.contains(3), "List doesn't contains non removed object (last)");

        list.removeFirst();
        assertEquals(1, list.length(), "List starting with two objects with one removed doesn't have length one");
        assertFalse(list.contains(2), "List still contains the removed object");
        assertTrue(list.contains(3), "List doesn't contains non removed object (last)");

        list.removeFirst();
        assertEquals(0, list.length(), "List starting with one objects with one removed doesn't have length zero");
        assertFalse(list.contains(3), "List still contains the removed object");

        list.removeFirst();
        assertEquals(0, list.length(), "RemoveFirst shouldn't do anything on an empty list");
    }

    @Test
    void removeLast() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeLast();
        assertEquals(2, list.length(), "List starting with three objects with one removed doesn't have length two");
        assertTrue(list.contains(1), "List doesn't contains non removed object (first)");
        assertTrue(list.contains(2), "List doesn't contains non removed object (middle)");
        assertFalse(list.contains(3), "List still contains the removed object");

        list.removeLast();
        assertEquals(1, list.length(), "List starting with two objects with one removed doesn't have length one");
        assertTrue(list.contains(1), "List doesn't contains non removed object (first)");
        assertFalse(list.contains(2), "List still contains the removed object");

        list.removeLast();
        assertEquals(0, list.length(), "List starting with one objects with one removed doesn't have length zero");
        assertFalse(list.contains(1), "List still contains the removed object");

        list.removeLast();
        assertEquals(0, list.length(), "RemoveLast shouldn't do anything on an empty list");
    }

    @Test
    void first() {
        list.add(1);
        assertEquals(1, list.first(), "First object not first object added");
        list.add(2);
        assertEquals(1, list.first(), "First object not first object added");
        list.add(3);
        assertEquals(1, list.first(), "First object not first object added");
    }

    @Test
    void last() {
        list.add(1);
        assertEquals(1, list.last(), "Last object not last object added");
        list.add(2);
        assertEquals(2, list.last(), "Last object not last object added");
        list.add(3);
        assertEquals(3, list.last(), "Last object not last object added");
    }

    @Test
    void contains() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(1), "Contains didn't find first object");
        assertTrue(list.contains(2), "Contains didn't find middle object");
        assertTrue(list.contains(3), "Contains didn't find last object");
        assertFalse(list.contains(4), "Contains found non-existent object");
        assertFalse(list.contains(null), "Contains found null object");
    }

    @Test
    void length() {
        assertEquals(0, list.length(), "Empty list doesn't have length zero");
        list.add(1);
        assertEquals(1, list.length(), "List of one object doesn't have length one");
        list.add(2);
        assertEquals(2, list.length(), "List of two objects doesn't have length two");
        list.removeFirst();
        assertEquals(1, list.length(), "List of one object doesn't have length one (2 -> 1)");
    }

    @Test
    void initIterator() {
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);

        assertNull(list.next(), "Uninitiated iterator giving non null values");
        assertFalse(list.hasNext(), "hasNext() returning true on uninitiated iterator");
        list.initIterator();
        assertTrue(list.hasNext(), "hasNext() returning false with un-iterated objects left");
        assertEquals(1, list.next(), "First iteration not returning first value");
        assertTrue(list.hasNext(), "hasNext() returning false with un-iterated objects left");
        assertEquals(2, list.next(), "Second iteration not returning second value");
        assertTrue(list.hasNext(), "hasNext() returning false with un-iterated objects left");
        assertEquals(1, list.next(), "Third iteration not returning third value");
        assertTrue(list.hasNext(), "hasNext() returning false with un-iterated objects left");
        assertEquals(3, list.next(), "Forth iteration not returning forth value");
        assertFalse(list.hasNext(), "hasNext() returning true with no objects left");
        assertNull(list.next(), "Iterating with no more values not returning null");
    }
}
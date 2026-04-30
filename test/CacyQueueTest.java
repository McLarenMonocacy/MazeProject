import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacyQueueTest {

    static CacyQueue<Integer> queue;

    @BeforeEach
    void setup(){
        queue = new CacyQueue<>();
    }
    @Test
    void queue() {
        queue.queue(1);
        assertEquals(1,queue.length(), "Queue with one object doesn't have length one");
        queue.queue(1);//add equal
        assertEquals(2,queue.length(), "Queue with two objects doesn't have length two");
        queue.queue(2);//add unequal
        assertEquals(3,queue.length(), "Queue with three objects doesn't have length three");
        queue.queue(1);//add equal to first again
        assertEquals(4,queue.length(), "Queue with four objects doesn't have length four");
        queue.queue(null);//add null object
        assertEquals(4,queue.length(), "Null object was added to the queue");
    }

    @Test
    void dequeue() {
        queue.queue(1);
        queue.queue(2);
        queue.queue(3);
        Integer output = queue.dequeue();
        assertEquals(1, output, "Dequeue didn't return first object added");
        assertEquals(2, queue.length(), "Queue starting with three objects with one removed doesn't have length two");

        output = queue.dequeue();
        assertEquals(2, output, "Dequeue didn't return second object added");
        assertEquals(1, queue.length(), "Queue starting with two objects with one removed doesn't have length one");

        output = queue.dequeue();
        assertEquals(3, output, "Dequeue didn't return third object added");
        assertEquals(0, queue.length(), "Queue starting with one objects with one removed doesn't have length zero");

        output = queue.dequeue();
        assertNull( output, "Dequeue didn't return null with empty queue");
        assertEquals(0, queue.length(), "Dequeue shouldn't do anything on an empty queue");
    }

    @Test
    void peek() {
        queue.queue(1);
        assertEquals(1, queue.peek(), "First object not first object added");
        queue.queue(2);
        assertEquals(1, queue.peek(), "First object not first object added");
        queue.queue(3);
        assertEquals(1, queue.peek(), "First object not first object added");
    }

    @Test
    void length() {
        assertEquals(0, queue.length(), "Empty queue doesn't have length zero");
        queue.queue(1);
        assertEquals(1, queue.length(), "Queue of one object doesn't have length one");
        queue.queue(2);
        assertEquals(2, queue.length(), "Queue of two objects doesn't have length two");
        queue.dequeue();
        assertEquals(1, queue.length(), "Queue of one object doesn't have length one (2 -> 1)");
    }
}
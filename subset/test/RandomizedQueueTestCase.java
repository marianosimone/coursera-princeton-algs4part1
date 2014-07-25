import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class RandomizedQueueTestCase {

    @Test
    public void emptyQueueShouldBeEmpry() {
        assertTrue(new RandomizedQueue<Integer>().isEmpty());
    }

    @Test
    public void emptyQueueShouldHaveZeroSize() {
        assertEquals(0, new RandomizedQueue<Integer>().size());
    }

    @Test
    public void enqueueingShouldIncreaseSizeByOne() {
        final RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    public void dequeingShouldReturnItAndEmptyTheQueue() {
        final RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        final Integer first = queue.dequeue(); 
        assertTrue(queue.isEmpty());
        assertEquals(Integer.valueOf(1), first);
    }

    @Test(expected=NullPointerException.class)
    public void enqueueingANullElementShouldThrowNPE() {
        new RandomizedQueue<Integer>().enqueue(null);
    }

    @Test(expected=NoSuchElementException.class)
    public void dequeueingFromEmptyQueueShouldThrowNSEE() {
        new RandomizedQueue<Integer>().dequeue();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void removingFromIteratorShouldThrowUOE() {
        new RandomizedQueue<Integer>().iterator().remove();
    }

    @Test
    public void shouldBeAbleToIterateOverElements() {
        final RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        final Iterator<Integer> it = queue.iterator();
        assertTrue(it.hasNext());
        final List<Integer> elements = new ArrayList<Integer>(3); 
        elements.add(it.next());
        assertTrue(it.hasNext());
        elements.add(it.next());
        assertTrue(it.hasNext());
        elements.add(it.next());
        assertFalse(it.hasNext());
        assertEquals(3, elements.size());
        assertTrue(elements.contains(Integer.valueOf(1)));
        assertTrue(elements.contains(Integer.valueOf(2)));
        assertTrue(elements.contains(Integer.valueOf(3)));
    }

    @Test(expected=NoSuchElementException.class)
    public void iteratingPastEndShouldThrowNSEE() {
        final RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        final Iterator<Integer> it = queue.iterator();
        assertEquals(Integer.valueOf(1), it.next());
        it.next();
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class DequeTestCase {

    @Test
    public void emptyDequeShouldBeEmpry() {
        assertTrue(new Deque<Integer>().isEmpty());
    }

    @Test
    public void emptyDequeShouldHaveZeroSize() {
        assertEquals(0, new Deque<Integer>().size());
    }

    @Test
    public void addingElementToDequeShouldIncreaseSizeByOne() {
        final Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }

    @Test
    public void removingFirstShouldReturnIt() {
        final Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        final Integer first = deque.removeFirst(); 
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(1), first);
    }

    @Test
    public void removingLastShouldReturnIt() {
        final Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        final Integer last = deque.removeLast(); 
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(2), last);
    }

    @Test(expected=NullPointerException.class)
    public void addingANullElementAtFrontShouldThrowNPE() {
        new Deque<Integer>().addFirst(null);
    }

    @Test(expected=NullPointerException.class)
    public void addingANullElementAtEndShouldThrowNPE() {
        new Deque<Integer>().addLast(null);
    }

    @Test(expected=NoSuchElementException.class)
    public void removingFirstForEmptyDequeShouldThrowNSEE() {
        new Deque<Integer>().removeFirst();
    }

    @Test(expected=NoSuchElementException.class)
    public void removingLastForEmptyDequeShouldThrowNSEE() {
        new Deque<Integer>().removeLast();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void removingFromIteratorShouldThrowUOE() {
        new Deque<Integer>().iterator().remove();
    }

    @Test
    public void shouldBeAbleToIterateOverElements() {
        final Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        final Iterator<Integer> it = deque.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());
        assertFalse(it.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void iteratingPastEndShouldThrowNSEE() {
        final Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        final Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(1), it.next());
        it.next();
    }
}

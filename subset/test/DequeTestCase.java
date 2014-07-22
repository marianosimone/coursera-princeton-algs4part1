import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DequeTestCase {

    @Test(expected=NullPointerException.class)
    public void addingANullElementAtFrontShouldThrowNPE() {
        final Deque deque = new Deque();
        deque.addFirst(null);
    }

    @Test(expected=NullPointerException.class)
    public void addingANullElementAtEndShouldThrowNPE() {
        final Deque deque = new Deque();
        deque.addLast(null);
    }
}

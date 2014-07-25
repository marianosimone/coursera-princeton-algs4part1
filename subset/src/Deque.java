import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
* A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue
* that supports inserting and removing items from either the front or the back of the data structure.
* As per requirements:
* - Implementation must:
*  - Support each deque operation in constant worst-case time
*  - Use space proportional to the number of items currently in the deque.
* - Iterator implementation must:
*  - Support the operations next() and hasNext() (plus construction) in constant worst-case time
*  - Use a constant amount of extra space per iterator. 
**/
public class Deque<Item> implements Iterable<Item> {

    private final LinkedList<Item> list;

    // construct an empty deque
    public Deque() {
        this.list = new LinkedList<Item>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    // return the number of items on the deque
    public int size() {
        return this.list.size();
    }

    /**
    * insert the item at the front
    * @throws NullPointerException if item is null
    */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        this.list.addFirst(item);
    }

    /**
    * insert the item at the end
    * @throws NullPointerException if item is null
    */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        this.list.addLast(item);
    }

    /**
    * delete and return the item at the front
    * @throws java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
    **/
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.list.pollFirst();
    }

    /**
    * delete and return the item at the end
    * @throws java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
    **/
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.list.pollLast();
    }

    private class DequeIterator implements Iterator<Item> {
        private final Iterator<Item> iterator;

        DequeIterator(final Deque<Item> deque) {
            this.iterator = deque.list.iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        /**
        * @throws java.util.NoSuchElementException if there are no more items to return
        */
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.iterator.next();
        }
        /**
        * @throws UnsupportedOperationException
        */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }
}

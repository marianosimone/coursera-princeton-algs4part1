import java.util.Iterator;

/**
* A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue
* that supports inserting and removing items from either the front or the back of the data structure.
**/
public class Deque<Item> implements Iterable<Item> {
/*
throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
Your deque implementation must support each deque operation in constant worst-case time and use space proportional to the number of items currently in the deque. Additionally, your iterator implementation must support the operations next() and hasNext() (plus construction) in constant worst-case time and use a constant amount of extra space per iterator. 
*/

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return 0;
    }

    /**
    * insert the item at the front
    * @throws NullPointerException if item is null
    */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Can't insert an empty element into Deque");
        }
    }

    /**
    * insert the item at the end
    * @throws NullPointerException if item is null
    */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Can't insert an empty element into Deque");
        }
    }

    /**
    * delete and return the item at the front
    * @throws java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
    **/
    public Item removeFirst() {
        return null;
    }

    /**
    * delete and return the item at the end
    * @throws java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
    **/
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return null;
    }
}

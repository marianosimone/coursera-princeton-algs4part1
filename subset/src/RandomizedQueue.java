import java.util.Iterator;

/**
* A randomized queue is similar to a stack or queue,
* except that the item removed is chosen uniformly at random from items in the data structure
**/
public class RandomizedQueue<Item> implements Iterable<Item> {
    /*
Throw a NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue; throw an UnsupportedOperationException if the client calls the remove() method in the iterator; throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time and
use space proportional to the number of items currently in the queue.
That is, any sequence of M randomized queue operations (starting from an empty queue) should take at most cM steps in the worst case, for some constant c.
Additionally, your iterator implementation must support construction in time linear in the number of items and it must support the operations next() and hasNext()
in constant worst-case time; you may use a linear amount of extra memory per iterator.
The order of two or more iterators to the same randomized queue should be mutually independent; each iterator must maintain its own random order. 

    */

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the queue empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the queue
    public int size() {
        return 0;
    }

    // add the item
    public void enqueue(Item item){
    }

    // delete and return a random item
    public Item dequeue() {
        return null;
    }

    // return (but do not delete) a random item
    public Item sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }
}

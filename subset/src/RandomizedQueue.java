import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
* A randomized queue is similar to a stack or queue,
* except that the item removed is chosen uniformly at random from items in the data structure
* 
* The implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time and
* use space proportional to the number of items currently in the queue.
* That is, any sequence of M randomized queue operations (starting from an empty queue) should take at most cM steps in the worst case, for some constant c.
* Additionally, your iterator implementation must support construction in time linear
* in the number of items and it must support the operations next() and hasNext() in constant worst-case time;
* you may use a linear amount of extra memory per iterator.
* The order of two or more iterators to the same randomized queue should be mutually independent; each iterator must maintain its own random order. 
**/
public class RandomizedQueue<Item> implements Iterable<Item> {

    private final List<Item> list;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.list = new ArrayList<Item>();
    }

    // is the queue empty?
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    // return the number of items on the queue
    public int size() {
        return this.list.size();
    }

    /**
    * add the item
    * @throws NullPointerException if item is null
    */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        this.list.add(item);
    }

    /**
    * delete and return a random item
    * @throws java.util.NoSuchElementException if empty
    **/
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, this.size());
        if (index == this.size()-1) {
            return this.list.remove(this.size()-1);
        }
        final Item value = this.list.get(index);
        this.list.set(index, this.list.remove(this.size()-1));
        return value;
    }

    /**
    * return (but do not delete) a random item
    * @throws java.util.NoSuchElementException if empty
    **/
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.list.get(StdRandom.uniform(0, this.size()));
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Iterator<Item> iterator;

        RandomizedQueueIterator(final RandomizedQueue<Item> queue) {
            final List<Item> innerList = new ArrayList<Item>(queue.size());
            for (int i = 0; i < queue.size(); ++i) {
                innerList.add(null);
            }
            for (int i = 0; i < queue.size(); ++i) {
                while (true) {
                    final int index = StdRandom.uniform(0, queue.size());
                    if (innerList.get(index) == null) {
                        innerList.set(index, queue.list.get(i));
                        break;
                    }
                }
            }
            this.iterator = innerList.iterator();
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

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(this);
    }
}

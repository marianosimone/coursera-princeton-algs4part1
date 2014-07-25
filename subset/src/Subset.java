import java.util.Iterator;

/**
* Subset client
* Write a client program Subset.java that:
* - takes a command-line integer k;
* - reads in a sequence of N strings from standard input using StdIn.readString();
* - prints out exactly k of them, uniformly at random.
* Each item from the sequence can be printed out at most once. You may assume that k ≥ 0 and no greater than the number of string N on standard input.
* The running time of Subset must be linear in the size of the input.
* You may use only a constant amount of memory plus either one Deque or RandomizedQueue object of maximum size at most N,
* where N is the number of strings on standard input.
* (For an extra challenge, use only one Deque or RandomizedQueue object of maximum size at most k.) 
*/
public class Subset {

    private static String readFromStdIn() {
        return StdIn.readString();
    }

    private static void process(final int k) {
        final RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int i = 0;
        while (!StdIn.isEmpty()) {
            i += 1;
            final String value = StdIn.readString();
            if (queue.size() < k) {
                queue.enqueue(value);
            } else if (StdRandom.uniform(0, i) < k) {
                queue.dequeue();
                queue.enqueue(value);
            }
        }
        final Iterator<String> it = queue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        final int k = Integer.valueOf(args[0]);
        if (k > 0) {
            process(k);
        }
    }
}

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PercolationTestCase {

    private static void test(final Percolation p, final int i, final int j, final boolean shouldFull, final boolean shouldPercolate) {
        p.open(i, j);
        assertEquals('(' + i + ',' + j + ") has different full status than expected", shouldFull, p.isFull(i, j));
        assertEquals("After opening (" + i + ',' + j + "), system has different percolates status than expected", shouldPercolate, p.percolates());
    }

    @Test
    public void sixBySixExample() {
        final Percolation percolation = new Percolation(6);
        test(percolation, 1, 6, true, false);
        test(percolation, 2, 6, true, false);
        test(percolation, 3, 6, true, false);
        test(percolation, 4, 6, true, false);
        test(percolation, 5, 6, true, false);
        test(percolation, 5, 5, true, false);
        test(percolation, 4, 4, false, false);
        test(percolation, 3, 4, false, false);
        test(percolation, 2, 4, false, false);
        test(percolation, 2, 3, false, false);
        test(percolation, 2, 2, false, false);
        test(percolation, 2, 1, false, false);
        test(percolation, 3, 1, false, false);
        test(percolation, 4, 1, false, false);
        test(percolation, 5, 1, false, false);
        test(percolation, 5, 2, false, false);
        test(percolation, 6, 2, false, false);
        test(percolation, 5, 4, true, true);
    }


    @Test
    public void threeByThreeExample() {
        final Percolation percolation = new Percolation(3);
        test(percolation, 1, 3, true, false);
        test(percolation, 2, 3, true, false);
        test(percolation, 3, 3, true, true);
        test(percolation, 3, 1, false, true);
        test(percolation, 2, 1, false, true);
        test(percolation, 1, 1, true, true);
    }

    @Test
    public void sevenBySevenExample() {
        final Percolation percolation = new Percolation(7);
        test(percolation, 6, 1, false, false);
        test(percolation, 7, 1, false, false);
        test(percolation, 7, 2, false, false);
        test(percolation, 7, 4, false, false);
        test(percolation, 1, 1, true, false);
        test(percolation, 1, 5, true, false);
        test(percolation, 2, 5, true, false);
        test(percolation, 3, 5, true, false);
        test(percolation, 4, 5, true, false);
        test(percolation, 5, 5, true, false);
        test(percolation, 6, 5, true, false);
        test(percolation, 7, 5, true, true);
        test(percolation, 2, 1, true, true);
        test(percolation, 4, 1, false, true);
        test(percolation, 5, 1, false, true);
        test(percolation, 3, 1, true, true);

    }
}

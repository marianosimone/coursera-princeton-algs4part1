import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class BoardTestCase {

    @Test
    public void expectedValueInFirstCornerIs1() {
        assertEquals(1, Board.expectedValue(3, 0, 0));
    }

    @Test
    public void expectedValueInSecondRowIsDimensionPlus1() {
        final int dimension = 3;
        assertEquals(dimension+1, Board.expectedValue(dimension, 1, 0));
    }

    @Test
    public void dimensionIsGuessedFromSize() {
        assertEquals(2, new Board(new int[][] {{2, 1}, {3, 0}}).dimension());
    }

    @Test
    public void nonSortedBlocksAreNotGoal() {
        assertFalse(new Board(new int[][] {{2, 1, 0}, {3, 4, 5}, {6, 7, 8}}).isGoal());
    }

    @Test
    public void sortedBlocksAreGoal() {
        assertTrue(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}).isGoal());
    }

    @Test
    public void equalBoardsAreEqual() {
        assertEquals(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}), new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}));
    }

    @Test
    public void differentBoardsAreNotEqual() {
        assertFalse(new Board(new int[][] {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}}).equals(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}})));
    }

    @Test
    public void toStringShouldBeWellFormed() {
        assertEquals("2 1 0\n3 4 5\n6 7 8", new Board(new int[][] {{2, 1, 0}, {3, 4, 5}, {6, 7, 8}}).toString());
    }
}

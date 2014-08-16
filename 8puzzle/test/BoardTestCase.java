import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

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

    private static <T> Collection<T> toCollection(final Iterable<T> iterable) {
        final Collection<T> rv = new LinkedList<T>();
        for (final T t: iterable) {
            rv.add(t);
        }
        return rv;
    }

    @Test
    public void zeroInMiddleShouldReturnAllNeighbors() {
        final Collection<Board> neighbors = toCollection(new Board(new int[][] {{2, 1, 3}, {4, 0, 5}, {6, 7, 8}}).neighbors());
        assertEquals(4, neighbors.size());
        assertThat(neighbors, hasItems(
            new Board(new int[][] {{2, 0, 3}, {4, 1, 5}, {6, 7, 8}}),
            new Board(new int[][] {{2, 1, 3}, {4, 7, 5}, {6, 0, 8}}),
            new Board(new int[][] {{2, 1, 3}, {0, 4, 5}, {6, 7, 8}}),
            new Board(new int[][] {{2, 1, 3}, {4, 5, 0}, {6, 7, 8}})
        ));
    }

    @Test
    public void zeroTopRowShouldReturn3Neighbors() {
        final Collection<Board> neighbors = toCollection(new Board(new int[][] {{2, 0, 3}, {4, 1, 5}, {6, 7, 8}}).neighbors());
        assertEquals(3, neighbors.size());
        assertThat(neighbors, hasItems(
            new Board(new int[][] {{0, 2, 3}, {4, 1, 5}, {6, 7, 8}}),
            new Board(new int[][] {{2, 3, 0}, {4, 1, 5}, {6, 7, 8}}),
            new Board(new int[][] {{2, 1, 3}, {4, 0, 5}, {6, 7, 8}})
        ));
    }

    @Test
    public void zeroInTopCornerShouldReturn2Neighbors() {
        final Collection<Board> neighbors = toCollection(new Board(new int[][] {{0, 1, 3}, {4, 2, 5}, {6, 7, 8}}).neighbors());
        assertEquals(2, neighbors.size());
        assertThat(neighbors, hasItems(
            new Board(new int[][] {{4, 1, 3}, {0, 2, 5}, {6, 7, 8}}),
            new Board(new int[][] {{1, 0, 3}, {4, 2, 5}, {6, 7, 8}})
        ));
    }

    @Test
    public void zeroInBottomCornerShouldReturn2Neighbors() {
        final Collection<Board> neighbors = toCollection(new Board(new int[][] {{4, 1, 3}, {5, 2, 8}, {6, 7, 0}}).neighbors());
        assertEquals(2, neighbors.size());
        assertThat(neighbors, hasItems(
            new Board(new int[][] {{4, 1, 3}, {5, 2, 0}, {6, 7, 8}}),
            new Board(new int[][] {{4, 1, 3}, {5, 2, 8}, {6, 0, 7}})
        ));
    }

    @Test
    public void zeroInBottomRowShouldReturn3Neighbors() {
        final Collection<Board> neighbors = toCollection(new Board(new int[][] {{2, 1, 3}, {4, 7, 5}, {6, 0, 8}}).neighbors());
        assertEquals(3, neighbors.size());
        assertThat(neighbors, hasItems(
            new Board(new int[][] {{2, 1, 3}, {4, 0, 5}, {6, 7, 8}}),
            new Board(new int[][] {{2, 1, 3}, {4, 7, 5}, {0, 6, 8}}),
            new Board(new int[][] {{2, 1, 3}, {4, 7, 5}, {6, 8, 0}})
        ));
    }

    private static int differences(final Board a, final Board b) {
        int differences = 0;
        for (int i = 0; i < a.dimension(); ++i) {
            for (int j = 0; j < a.dimension(); ++j) {
                if (a.blocks[i][j] != b.blocks[i][j]) {
                    differences += 1;
                }
            }
        }
        return differences;
    }

    @Test
    public void aTwinShouldOnlyHaveTwoDifferencesFromBottomRow() {
        final Board original = new Board(new int[][] {{2, 1, 3}, {4, 7, 5}, {6, 0, 8}});
        assertEquals(2, differences(original, original.twin()));
    }

    @Test
    public void aTwinShouldOnlyHaveTwoDifferencesFromTopCorner() {
        final Board original = new Board(new int[][] {{0, 1, 3}, {4, 7, 5}, {6, 2, 8}});
        assertEquals(2, differences(original, original.twin()));
    }

    @Test
    public void hammingDistanceForGoalBoardShouldBe0() {
        assertEquals(0, new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}).hamming());
    }

    @Test
    public void hammingDistanceForOneOffSHouldBe1() {
        assertEquals(1, new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}).hamming());
    }

    @Test
    public void hammingDistanceForTwoOffShouldBe3() {
        assertEquals(3, new Board(new int[][] {{3, 1, 2}, {4, 5, 6}, {7, 8, 0}}).hamming());
    }

    @Test
    public void hammingDistanceForExampleShouldBe5() {
        assertEquals(5, new Board(new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}}).hamming());
    }

    @Test
    public void manhattanDistanceForGoalBoardShouldBe0() {
        assertEquals(0, new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}).manhattan());
    }

    @Test
    public void manhattanDistanceForOneOffSHouldBe2() {
        assertEquals(1, new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}).manhattan());
    }

    @Test
    public void manhattanDistanceForTwoOffShouldBe4() {
        assertEquals(4, new Board(new int[][] {{3, 1, 2}, {4, 5, 6}, {7, 8, 0}}).manhattan());
    }

    @Test
    public void manhattanDistanceForExampleShouldBe10() {
        assertEquals(10, new Board(new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}}).manhattan());
    }
}

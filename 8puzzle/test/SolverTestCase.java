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

public class SolverTestCase {

    @Test
    public void solvingSolvedBoardIsTrivial() {
        final Solver solver = new Solver(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}})); 
        assertTrue(solver.isSolvable());
        assertEquals(0, solver.moves());
    }

    @Test
    public void solvingOneOffBoardIsOneMovementLong() {
        final Solver solver = new Solver(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}})); 
        assertTrue(solver.isSolvable());
        assertEquals(1, solver.moves());
    }
}

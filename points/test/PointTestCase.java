import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PointTestCase {

    @Test
    public void biggerYShouldCompareAsPositive() {
        assertTrue(new Point(1, 0).compareTo(new Point(0, 0)) > 0);
    }

    @Test
    public void smallerYShouldCompareAsNegative() {
        assertTrue(new Point(0, 0).compareTo(new Point(1, 0)) < 0);
    }

    @Test
    public void equalYShouldBreakTieByXPositive() {
        assertTrue(new Point(0, 1).compareTo(new Point(0, 0)) > 0);
    }

    @Test
    public void equalYShouldBreakTieByXNegative() {
        assertTrue(new Point(0, 0).compareTo(new Point(0, 1)) < 0);
    }

    @Test
    public void equalYAndXShouldCompareAsEqual() {
        assertTrue(new Point(3, 3).compareTo(new Point(3, 3)) == 0);
    }

    @Test
    public void slopeOfVerticalLineShouldBeInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, new Point(3, 5).slopeTo(new Point(3, 2)), 0.0);
    }

    @Test
    public void slopeOfHorizontalLineShouldBeZero() {
        assertEquals(0.0, new Point(5, 5).slopeTo(new Point(3, 5)), 0.0);
    }

    @Test
    public void degenerateSlopeShouldBeNegativeInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, new Point(3, 5).slopeTo(new Point(3, 5)), 0.0);
    }
}

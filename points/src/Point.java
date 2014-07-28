/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    /**
    * Compare points by the slopes they make with the invoking point (x0, y0).
    * Formally, the point (x1, y1) is less than the point (x2, y2) if and only if:
    * The slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
    * Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method. 
    **/
    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point o1, Point o2) {
            double o1Slope = slopeTo(o1);
            double o2Slope = slopeTo(o2);
            if (o1Slope == o2Slope) {
                return 0;
            }
            if (o1Slope < o2Slope) {
                return -1;
            }
            return 1;
        }
    }

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;

    private final int x;  // x coordinate
    private final int y;  // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.SLOPE_ORDER = new SlopeComparator();
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    // The slope is determined by (y1 − y0) / (x1 − x0).
    // The slope of a horizontal line segment is positive zero;
    // The slope of a vertical line segment is positive infinity;
    // The slope of a degenerate line segment (between a point and itself) is negative infinity. 
    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0.0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;


public class Fast {
    /**
    * Examines 4 points at a time and checks whether they all lie on the same line segment,
    * printing out any such line segments to standard output and drawing them using standard drawing.
    * To check whether the 4 points p, q, r, and s are collinear, check whether the slopes between p and q, between p and r, and between p and s are all equal. 
    *
    **/
    public static void main(String[] args) {
        In inputFile = new In(args[0]);
        int numberOfPoints = inputFile.readInt();
        final ArrayList<Point> points = new ArrayList<Point>(numberOfPoints);
        while (!inputFile.isEmpty()) {
            final Point p = new Point(inputFile.readInt(), inputFile.readInt());
            points.add(p);
            p.draw();
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Collections.sort(points);

        for (int i = 0; i < numberOfPoints-1; ++i) {
            final Point p = points.get(i);
            final List<Point> others = new ArrayList<Point>(numberOfPoints-1);  
            for (int j = i+1; j < numberOfPoints; ++j) {
                others.add(points.get(j));
            }
            Collections.sort(others, p.SLOPE_ORDER);
            int alignedPoints = 2;
            final double baseSlope = p.slopeTo(others.get(0));
            Point q = others.get(0);
            for (int j = 1; j < others.size(); ++j) {
                if (p.slopeTo(others.get(j)) == baseSlope) {
                    alignedPoints += 1;
                    q = others.get(j);
                } else {
                    break;
                }
            }
            if (alignedPoints >= 4) {
                System.out.print(p.toString() + " -> ");
                for (final Point o: others) {
                    System.out.print(o.toString());
                    if (o.compareTo(q) == 0) {
                        System.out.println();
                        p.drawTo(o);
                        break;
                    } else {
                        System.out.print(" -> ");
                    }
                } 
            }
        }
    }
}

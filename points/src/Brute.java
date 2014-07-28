import java.util.Arrays;

public class Brute {
    /**
    * Examines 4 points at a time and checks whether they all lie on the same line segment,
    * printing out any such line segments to standard output and drawing them using standard drawing.
    * To check whether the 4 points p, q, r, and s are collinear, check whether the slopes between p and q, between p and r, and between p and s are all equal. 
    *
    **/
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        In inputFile = new In(args[0]);
        int numberOfPoints = inputFile.readInt();
        final Point[] points = new Point[numberOfPoints];
        int i = 0;
        while (!inputFile.isEmpty()) {
            final Point p = new Point(inputFile.readInt(), inputFile.readInt());
            points[i] = p;
            p.draw();
            i+= 1;
        }
        Arrays.sort(points);
        
        for (int p = 0; p < numberOfPoints; ++p) {
            for (int q = p+1; q < numberOfPoints; ++q) {
                for (int r = q+1; r < numberOfPoints; ++r) {
                    for (int s = r+1; s < numberOfPoints; ++s) {
                        if (
                            points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])
                            && points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])
                        ) {
                            StdOut.println(
                                points[p].toString() + " -> " + points[q].toString() + " -> "
                                + points[r].toString() + " -> " + points[s].toString()
                            );
                            points[p].drawTo(points[s]);
                        }
                    }
                }
            }
        }
    }
}

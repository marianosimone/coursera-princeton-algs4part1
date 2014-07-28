
import java.util.Arrays;

public class Fast {
    
    public static void main(String[] args) {
        
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int numberOfPoints = in.readInt();
        
        final Point[] points = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = new Point(in.readInt(), in.readInt());
            points[i] = p;
            p.draw();
        }

        // sorted by coordinates
        Arrays.sort(points);
        
        final Point[] others = new Point[numberOfPoints];

        for (int i = 0; i < numberOfPoints-3; i++) {
            for (int j = i; j < numberOfPoints; j++) {
                others[j] = points[j];
            }

            final Point origin = others[i];
            // sort by slope
            Arrays.sort(others, i+1, numberOfPoints, origin.SLOPE_ORDER);
            Arrays.sort(others, 0, i, origin.SLOPE_ORDER);

            int firstAfterOrigin = i+1;
            int farthestInSegment = i+2;
            int beforeOrigin = 0;

            while (farthestInSegment < numberOfPoints) {
                final double baseSlope = origin.slopeTo(others[firstAfterOrigin]);
                // keep going while the slope is the same
                while (farthestInSegment < numberOfPoints && origin.slopeTo(others[farthestInSegment]) == baseSlope) {
                    farthestInSegment += 1;
                }
                // if a segment is found
                if (farthestInSegment - firstAfterOrigin >= 3) {
                    // check for overlaping segments
                    double previousSlope = Double.NEGATIVE_INFINITY;
                    while (beforeOrigin < i) {
                        previousSlope = origin.slopeTo(others[beforeOrigin]);
                        if (previousSlope < baseSlope) {
                            beforeOrigin += 1;   
                        } else {
                            break;
                        }
                    }
                    if (previousSlope != baseSlope) {
                        origin.drawTo(others[farthestInSegment-1]);
                        String output = origin.toString() + " -> ";
                        for (int l = firstAfterOrigin; l < farthestInSegment-1; l++)
                            output += others[l].toString() + " -> ";
                        output += others[farthestInSegment-1].toString();
                        StdOut.println(output);
                    }
                }
                firstAfterOrigin = farthestInSegment;
                farthestInSegment += 1;
            }
        }
    }
}

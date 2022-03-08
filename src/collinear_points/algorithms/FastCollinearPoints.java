package collinear_points.algorithms;

import collinear_points.rename.LineSegment;
import collinear_points.rename.Point;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints extends CollinearPoints {
    public FastCollinearPoints(Point[] points) {
        List<LineSegment> tmpSegments = new LinkedList<>();

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            double[] slope = new double[points.length - i - 1];
            for (int j = 0; j < slope.length; j++) {
                Point point = points[j + i + 1];
                assert origin != point;

                slope[j] = origin.slopeTo(point);
            }
            Point[] others = new Point[points.length - i - 1];
            for (int j = 0; j < others.length; j++) others[j] = points[j];
            Arrays.sort(others, );
            
        }

        segments = tmpSegments.toArray(new LineSegment[0]);
        numberOfSegments = segments.length;
    }
}

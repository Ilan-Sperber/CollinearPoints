package collinear_points.algorithms;

import collinear_points.rename.LineSegment;
import collinear_points.rename.Point;

import java.util.*;
import java.util.stream.Stream;

public class FastCollinearPoints extends CollinearPoints {
    public FastCollinearPoints(Point[] points) {
        List<LineSegment> tmpSegments = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            Point[] slopes = new Point[points.length - (i + 1)];
//            double[] slope = new double[points.length - i - 1];
//            for (int j = 0; j < slope.length; j++) {
//                Point point = points[j + i + 1];
//                assert origin != point;
//
//                slope[j] = origin.slopeTo(point);
//            }
//            Point[] others = new Point[points.length - i - 1];
//            for (int j = 0; j < others.length; j++) others[j] = points[j];
//            Arrays.sort(others, );

//            Point[] others = new Point[points.length - 1];
//            for (int j = 0; j < points.length; j++) {
//                if (j == i) continue;
//                others[j] = points[i];
//            }
//
//            Arrays.sort(others, origin.slopeOrder());
//
//            Point first = others[0];
//            int curSlope;
//            for (int j = 1; j < points.length; j++) {
//
//            }

            for (int j = 0; j < slopes.length; j++) {
                slopes[j] = points[i + j];
            }

            Arrays.sort(slopes, origin.slopeOrder());

            int inLine = 0;
            double cur = slopes[0].slopeTo(origin);
            for (int f = 1; f < slopes.length; f++) {
                double newSlope = slopes[f - 1].slopeTo(origin);
                if (newSlope == cur) {
                    inLine++;
                } else { // a line has ended
                    if (inLine >= 4) { // there are enough points in the line to be counted as a segment
                        Point[] onLine = new Point[inLine]; // all the points on this segment, to find the endpoints
                        for (int g = 0; g < inLine; g++)
                            onLine[g] = points[g + f];
                        Point end1 = Arrays.stream(onLine).max(Point::compareTo).orElseThrow();
                        Point end2 = Arrays.stream(onLine).min(Point::compareTo).orElseThrow();
                        tmpSegments.add(new LineSegment(end1, end2));
                    }
                    cur = newSlope;
                    inLine = 0;
                }
            }
        }

        Iterator itr = tmpSegments.iterator();
        while (itr.hasNext()) {
            LineSegment line = itr.next();

        }

        segments = tmpSegments.toArray(new LineSegment[0]);
        numberOfSegments = segments.length;
    }
}

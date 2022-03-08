package collinear_points.algorithms;

import collinear_points.rename.LineSegment;
import collinear_points.rename.Point;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BruteCollinearPoints extends CollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        // LinkedList is better since we are just adding to the end then making it an array
        List<LineSegment> tmpSegments = new LinkedList<>(); // I just tried to make a LinkedList with a capacity

        int n = points.length;

        for (int a = 0; a < n - 3; a++) {
            for (int b = a + 1; b < n - 2; b++) {
                for (int c = b + 1; c < n - 1; c++) {
                    for (int d = c + 1; d < n; d++) {
//                        System.out.println(a + " " + b + " " + c + " " + d);
                        Point aPoint = points[a]; // just a point on the (possible) line
                        Comparator<Point> comparator = aPoint.slopeOrder();
                        if (comparator.compare(points[b], points[c]) == 0 && comparator.compare(points[c], points[d]) == 0) { // if all the points are in a line
                            // first endpoint is farthest from point a (or any point)
                            // second endpoint is farthest from the first

                            final int finalB = b, finalC = c, finalD = d; // stupid that this is necessary
                            Supplier<Stream<Point>> line = () -> Stream.of(aPoint, points[finalB], points[finalC], points[finalD]); // because we keep closing the stream
//                            System.out.println(Arrays.toString(line.get().toArray())); // can be copy+pasted into desmos

                            Point end1 = line.get().max(Comparator.comparingDouble(p -> p.distance(aPoint))).orElseThrow(); // farthest from a point
                            Point end2 = line.get().max(Comparator.comparingDouble(p -> p.distance(end1))).orElseThrow(); // farthest from an endpoint
                            tmpSegments.add(new LineSegment(end1, end2));
                        }
                    }
                }
            }
        }

        segments = tmpSegments.stream().distinct().toArray(LineSegment[]::new); // remove duplicate lines
        numberOfSegments = segments.length;
    }
}

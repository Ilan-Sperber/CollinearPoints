package collinear_points.algorithms;

import collinear_points.rename.LineSegment;
import collinear_points.rename.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BruteCollinearPointsAnyLength extends CollinearPoints {
    private final int size;
    private final int pointsPerLine;
    private final int[] readers;
    private final Point[] points;

    public BruteCollinearPointsAnyLength(Point[] points) {
        this(points, 4);
    }

    public BruteCollinearPointsAnyLength(Point[] points, int pointsPerLine) {
        this.pointsPerLine = pointsPerLine;
        this.points = points;

        // LinkedList is better since we are just adding to the end then making it an array
        List<LineSegment> tmpSegments = new LinkedList<>(); // I just tried to make a LinkedList with a capacity

        size = points.length;

        System.out.println(size);

        readers = new int[pointsPerLine];
        Arrays.setAll(readers, i -> i); // set each value to its index
//        System.out.println(Arrays.toString(readers));

        // from 0 tp n - ppp + 1
        //   from ^+1 to n - ppp + 2
        //     from ^+1 to n - ppp + 3

        // readers[0] is the global

        Supplier<Stream<Point>> line = () -> Arrays.stream(readers).mapToObj(i -> points[i]); // because we keep closing the stream

        while (readers[0] <= size - pointsPerLine) {
            if (isLine()) {
                // first endpoint is farthest from point a (or any point)
                // second endpoint is farthest from the first

//                System.out.println(Arrays.toString(line.get().toArray())); // can be copy+pasted into desmos

                Point end1 = line.get().max(Comparator.comparingDouble(p -> p.distance(points[readers[0]]))).orElseThrow(); // farthest from a point
                Point end2 = line.get().max(Comparator.comparingDouble(p -> p.distance(end1))).orElseThrow(); // farthest from an endpoint
                tmpSegments.add(new LineSegment(end1, end2));
            }

            // reset to plus readers[0] + its index

            // only the last moves unless its reset
            int last = pointsPerLine - 1;
            if (readers[last] == size - pointsPerLine + last) {
                reset(last);
            } else {
                readers[last]++;
            }
        }

        segments = tmpSegments.stream().distinct().toArray(LineSegment[]::new); // remove duplicate lines
        numberOfSegments = segments.length;
    }

    private void reset(int i) {
        if (i == 0) return;
        readers[i - 1]++;
        if (readers[i - 1] == size - pointsPerLine + i) reset(i - 1);
        readers[i] = readers[i - 1] + 1;
    }

    private boolean isLine() {
        Comparator<Point> comparator = points[readers[0]].slopeOrder();
        for (int i = 1; i < pointsPerLine - 1; i++)
            if (comparator.compare(points[readers[i]], points[readers[i + 1]]) != 0)
                return false;
        return true;
    }
}

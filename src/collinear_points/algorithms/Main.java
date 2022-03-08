package collinear_points.algorithms;

import collinear_points.rename.LineSegment;
import collinear_points.rename.Point;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int size = 10;
        StdDraw.setScale(0, size);
//        StdDraw.filledCircle(0, 0, size);

        int numberOfPoints = 30;

        Point[] points = new Point[numberOfPoints];
        Random rng = ThreadLocalRandom.current();

        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Point(rng.nextInt(size), rng.nextInt(size));
//            points[i] = new Point(Math.random(), Math.random());
        }

        points = Arrays.stream(points).distinct().toArray(Point[]::new);
        System.out.println(numberOfPoints - points.length + " duplicates removed");

        points = new Point[] { new Point(1, 1), new Point(2, 2), new Point(7, 7), new Point(5, 4), new Point(9, 9), new Point(3, 3) };
//        points = new Point[] { new Point(1, 9), new Point(7, 5), new Point(1, 9), new Point(5, 9) };

        for (Point p : points) {
//            System.out.println(p);
            p.draw();
        }

        CollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println();
        System.out.println(Arrays.toString(collinear.segments()));
        System.out.println(collinear.numberOfSegments() + " lines");

//        for (LineSegment ls : collinear.segments()) ls.draw();

//        System.out.println();

//        collinear = new BruteCollinearPointsAnyLength(points, 5);
//        System.out.println();
//        System.out.println(Arrays.toString(collinear.segments()));
//        System.out.println(collinear.numberOfSegments() + " lines");

        for (LineSegment ls : collinear.segments()) ls.draw();
    }
}

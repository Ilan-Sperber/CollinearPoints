package collinear_points.rename;

import java.util.*;
import edu.princeton.cs.algs4.StdDraw;

public record Point(int x, int y) implements Comparable<Point> {

    public void draw() {
//        for (int i = -50; i <= 50; i++) {
//            for (int j = -50; j <= 50; j++) {
//                StdDraw.point(x + i, y + j);
//            }
//        }
        StdDraw.filledCircle(x, y, .125);
//        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        return (double) (y - that.y) / (x - that.x);
    }

    @Override
    public int compareTo(Point that) {
        return y == that.y ? Double.compare(x, that.x)
                           : Double.compare(y, that.y);
    }

    public Comparator<Point> slopeOrder() {
        return Comparator.comparingDouble(this::slopeTo);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double distance(Point that) {
        return Math.hypot(x - that.x, y - that.y);
    }

    public static void main(String[] args) {
        Point a = new Point(5, 5);
        Point b = new Point(7, 7);
        Point c = new Point(9, 8);

        System.out.println(a.slopeOrder().compare(b, c));
    }
}
package collinear_points.algorithms;

import collinear_points.rename.LineSegment;

//public interface CollinearPoints {
//    int numberOfSegments();
//    LineSegment[] segments();
//}

// all you need is a constructor that sets numberOfSegments and segments variables

public abstract class CollinearPoints {
    protected int numberOfSegments;
    protected LineSegment[] segments;

    public final int numberOfSegments() {
        return numberOfSegments;
    }

    public final LineSegment[] segments() {
        return segments;
    }
}

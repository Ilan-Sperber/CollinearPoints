package collinear_points.rename;

public record LineSegment(Point p, Point q) {
    public LineSegment {
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to LineSegment constructor are the same point: " + p);
        }
    }

    public void draw() {
        p.drawTo(q);
    }

    @Override
    public String toString() {
        return p + " -> " + q;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported");
    }

    public double slope() {
        return p.slopeTo(q);
        }

    public boolean collinear(LineSegment other) {
        if (other.slope() == this.slope()){
            if (other.p.equals(p)||other.p.slopeTo(p)==other.slope()){
                return true;
            }
        }
        return false;
    }
}

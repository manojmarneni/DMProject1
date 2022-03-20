package com.company;

public class Point {

    double x;
    double y;
    double prob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;
    }

    @Override
    public String toString() {
        return "('" + x +
                "','" + y +"')";
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    double distance (Point other) {
        Point result = new Point();
        result.y = Math.abs (y - other.y);
        result.x = Math.abs (x- other.x);
        return Math.sqrt((result.y)*(result.y) +(result.x)*(result.x));
    }


}

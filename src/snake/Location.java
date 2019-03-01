package snake;

import java.awt.Point;

public class Location extends Point {

    private static final long serialVersionUID = 1L;

    /**
     * The location constructor that takes two int variables
     * 
     * @param x
     *            the x value of the location
     * @param y
     *            the y value of the location
     */
    public Location(int x, int y) {
        super(x, y);
    }

    /**
     * The location constructor that takes two double variables
     * 
     * @param x
     *            the x value of the location
     * @param y
     *            the y value of the location
     */
    public Location(double x, double y) {
        super.setLocation(x, y);
    }

    /**
     * Returns a copy of a given point
     * 
     * @return Point
     */
    public Location copy() {
        Location copy = new Location(super.x, super.y);
        return copy;
    }

    /**
     * Gets the distance between two locations as a double
     * 
     * @param l
     *            The second location
     * @return Double
     */
    public double distance(Location l) {

        // Null check
        if (l == null) {
            return -1.0;
        }

        double x1 = getX(), y1 = getY(), x2 = l.getX(), y2 = l.getY();

        return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
    }

}

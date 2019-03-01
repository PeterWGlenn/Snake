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

}

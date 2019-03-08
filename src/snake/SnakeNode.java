package snake;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Location;

/**
 * A data type to represent the body of the snake.
 * 
 * @author Peter Glenn
 * @version 2.25.19
 */
public class SnakeNode {

    private static final int bodyPixelSize = (int) (50 * Game.SCALE);

    private Location location;
    private Location lastLocation;
    private SnakeNode next;
    private Color color;

    /**
     * The constructor for SnakeNode that stores a location and next value for a
     * Snake Node
     * 
     * @param l
     *            The location
     * @param n
     *            The next SnakeNode
     * @param c
     *            The color of the SnakeNode
     */
    public SnakeNode(Location l, SnakeNode n, Color c) {
        location = l;
        lastLocation = l.copy();
        next = n;
        color = c;
    }

    /**
     * Returns the location of the Snake Node
     * 
     * @return Location
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Returns the last location of the Snake Node
     * 
     * @return Location
     */
    protected Location getLastLocation() {
        return lastLocation;
    }

    /**
     * A getter that returns the next snake node
     * 
     * @return SnakeNode
     */
    protected SnakeNode next() {
        return next;
    }

    /**
     * A setter that sets the next to a given SnakeNode
     * 
     * @param n
     *            The new SnakeNode
     */
    protected void setNext(SnakeNode n) {
        next = n;
    }

    /**
     * A setter that set the location to a given point
     * 
     * @param l
     *            The new location of the SnakeNode
     */
    protected void setLocation(Location l) {
        lastLocation = location.copy();
        location = l;
    }

    /**
     * A setter that set the location to a given x and y value
     * 
     * @param x
     *            The new x coordinate
     * @param y
     *            The new y coordinate
     */
    protected void setLocation(double x, double y) {
        lastLocation = location.copy();
        location.setLocation(x, y);
    }

    /**
     * Updates the next Node's location to be closer to the previous Node
     */
    protected void pull() {

        // Null check
        if (next == null) {
            return;
        }

        if (nextDistance() > Snake.snakeStep) {
            next.setLocation(lastLocation.copy());
            next.pull();
        }
    }

    /**
     * Returns the distance between the Node and its next Node
     * 
     * @return double
     */
    protected double nextDistance() {
        return location.distance(next.location);
    }

    /**
     * Renders the SnakeNode for a particular graphic
     * 
     * @param g
     *            The graphics
     */
    public void render(Graphics g) {

        g.setColor(color);
        g.fillOval((int) location.getX() - bodyPixelSize / 2,
                (int) location.getY() - bodyPixelSize / 2, bodyPixelSize,
                bodyPixelSize);
    }

    /**
     * Gets the size of the body segment
     * 
     * @return int
     */
    public int size() {
        return bodyPixelSize;
    }

}

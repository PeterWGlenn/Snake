package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import main.Game;

/**
 * A data type to represent the body of the snake.
 * 
 * @author Peter Glenn
 * @version 2.25.19
 */
public class SnakeNode {

    private static final int bodyPixelSize = (int) (50 * Game.SCALE);

    private Point location;
    private Point lastLocation;
    private SnakeNode next;

    /**
     * The constructor for SnakeNode that stores a location and next value for a
     * Snake Node
     * 
     * @param l
     *            The location
     * @param n
     *            The next SnakeNode
     */
    public SnakeNode(Point l, SnakeNode n) {
        location = l;
        lastLocation = location;
        next = n;
    }

    /**
     * Returns the location of the Snake Node
     * 
     * @return Point
     */
    protected Point getLocation() {
        return location;
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
    protected void setLocation(Point l) {
        if (getLastLocationDistance() > Snake.snakeStep * 20) {
            lastLocation = location;
        }
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
        if (getLastLocationDistance() > Snake.snakeStep * 20) {
            lastLocation = location;
        }
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

        // if (nextDistance() > Snake.snakeStep / 4) {
        next.location = lastLocation;
        next.pull();
        // }
    }

    /**
     * Returns the distance between the Node and its next Node
     * 
     * @return double
     */
    protected double nextDistance() {

        double x1 = location.getX(), y1 = location.getY(),
                x2 = next.location.getX(), y2 = next.location.getY();

        return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
    }

    /**
     * Renders the SnakeNode for a particular graphic
     * 
     * @param g
     *            The graphics
     */
    public void render(Graphics g) {

        g.setColor(Color.GREEN);
        g.fillOval((int) location.getX() - bodyPixelSize / 2,
                (int) location.getY() - bodyPixelSize / 2, bodyPixelSize,
                bodyPixelSize);

        g.setColor(Color.ORANGE);
        g.fillOval((int) lastLocation.getX() - bodyPixelSize / 2,
                (int) lastLocation.getY() - bodyPixelSize / 2, bodyPixelSize,
                bodyPixelSize);
    }

    /**
     * Gets the distance between the location and the last location
     * 
     * @return double
     */
    private double getLastLocationDistance() {
        double x1 = location.getX(), y1 = location.getY(),
                x2 = lastLocation.getX(), y2 = lastLocation.getY();

        return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
    }

}

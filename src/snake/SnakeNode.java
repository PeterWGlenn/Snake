package snake;

import java.awt.Point;

/**
 * A data type to represent the body of the snake.
 * 
 * @author Peter Glenn
 * @version 2.25.19
 */
public class SnakeNode {

    private Point location;
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

}

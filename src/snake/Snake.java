package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The snake is controlled by the player. There is exactly one snake on the
 * screen at any given time. When the snake's head collides with an apple, the
 * snake's length increases by one. If the snake's head collides with its tail
 * or a wall then the game starts over.
 * 
 * @author Peter Glenn
 * @version 2.24.2019
 */
public class Snake {

    private SnakeNode head;
    private SnakeNode neck;
    private int size;

    /**
     * Creates a new Snake and sets its location to a point value
     * 
     * @param location
     *            The location of the head of the Snake
     */
    public Snake(Point location) {
        head = new SnakeNode(location, null);
        neck = new SnakeNode(location, null);
        size = 1;
    }

    /**
     * Increases the size of the Snake by 1
     */
    public void grow() {
        size++;
    }

    /**
     * Renders the Snake
     * 
     * @param g
     *            The graphics param
     */
    public void render(Graphics g) {

        if (neck != null) {
            // Draw over the neck
            g.setColor(Color.ORANGE);
            g.fillRect((int) neck.getLocation().getX(),
                    (int) neck.getLocation().getY(), 100, 100);
        }

        // Draw the head
        g.setColor(Color.RED);
        g.fillRect((int) head.getLocation().getX(),
                (int) head.getLocation().getY(), 100, 100);

        // Set neck location to current head location
        neck.setLocation(head.getLocation());
    }

}

package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import main.Game;
import main.World;

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

    private static int bodyPixelSize = (int) (50 * Game.SCALE);

    private SnakeNode head;
    private SnakeNode neck;
    private int size;
    private int direction;

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
        direction = -1;
    }

    /**
     * The update method moves the snake and tests if he is close enough to the
     * apple to eat it.
     */
    public void update() {
        move();
    }

    /**
     * Changes the location of the Snake based on its direction
     */
    public void move() {
        double xStep = head.getLocation().getX();
        double yStep = head.getLocation().getY();

        // Stopped
        if (direction == -1) {
            return;
        }

        // North
        if (direction == 0 && (yStep - 1) > 0) {
            yStep = yStep - 1;
        }
        // East
        if (direction == 1 && (xStep + 1) < World.length()) {
            xStep = xStep + 1;
        }
        // South
        if (direction == 2 && (yStep + 1) < World.height()) {
            yStep = yStep + 1;
        }
        // West
        if (direction == 3 && (xStep - 1) > 0) {
            xStep = xStep - 1;
        }

        head.setLocation(xStep, yStep);
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
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect((int) neck.getLocation().getX(),
                    (int) neck.getLocation().getY(), bodyPixelSize,
                    bodyPixelSize);
        }

        // Draw the head
        g.setColor(Color.GREEN);
        g.fillRect((int) head.getLocation().getX(),
                (int) head.getLocation().getY(), bodyPixelSize, bodyPixelSize);

        // Set neck location to current head location
        neck.setLocation(head.getLocation());
    }

}

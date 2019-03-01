package snake;

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

    /**
     * The amount of pixels the Snake can move in one tick
     */
    protected static final int snakeStep = (int) (6 * Game.SCALE);

    private SnakeNode head;
    private SnakeNode tail;
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
        tail = head;
        size = 10;
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
        if (direction == 0 && (yStep - snakeStep) > 0) {
            yStep = yStep - snakeStep;
        }
        // East
        if (direction == 1 && (xStep + snakeStep) < World.length()) {
            xStep = xStep + snakeStep;
        }
        // South
        if (direction == 2 && (yStep + snakeStep) < World.height()) {
            yStep = yStep + snakeStep;
        }
        // West
        if (direction == 3 && (xStep - snakeStep) > 0) {
            xStep = xStep - snakeStep;
        }

        head.pull();
        head.setLocation(xStep, yStep);

    }

    /**
     * Increases the size of the Snake by 1
     */
    public void grow() {

        size++;

        int offsetX = tail.getLocation().x;
        int offsetY = tail.getLocation().y;

        SnakeNode newNode = new SnakeNode(new Point(offsetX, offsetY), null);

        tail.setNext(newNode);
        tail = newNode;

    }

    /**
     * Renders the Snake
     * 
     * @param g
     *            The graphics param
     */
    public void render(Graphics g) {

        // Draw the body
        SnakeNode dummy = head.next();
        while (dummy != null) {
            dummy.render(g);
            dummy = dummy.next();
        }

        // Draw the head
        head.render(g);

    }

    /**
     * Allows the KeyboardInput class to set the direction of the Snake
     * 
     * @param d
     *            The new direction represented by an int
     */
    protected void setDirection(int d) {
        direction = d;
    }

}

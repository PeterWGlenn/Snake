package snake;

import java.awt.Graphics;

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
    protected static final Location snakeStartingLocation = new Location(
            Game.WIDTH / 2, Game.HEIGHT / 2);

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
    public Snake() {
        head = new SnakeNode(snakeStartingLocation.copy(), null);
        tail = head;
        size = 0;
        direction = -1;
    }

    /**
     * The update method moves the snake and tests if he is close enough to the
     * apple to eat it.
     */
    public void update() {
        move();
        // checkHeadCollideWithTail();
    }

    /**
     * Changes the location of the Snake based on its direction
     */
    public void move() {

        double xStep = head.getLocation().getX();
        double yStep = head.getLocation().getY();

        double colBox = head.size() / 2;

        // Stopped
        if (direction == -1) {
            return;
        }

        // North
        if (direction == 0
                && (yStep - snakeStep) > World.wallWidth() + colBox) {
            yStep = yStep - snakeStep;
        }
        // East
        if (direction == 1 && (xStep + snakeStep) < World.length()
                - World.wallWidth() - colBox) {
            xStep = xStep + snakeStep;
        }
        // South
        if (direction == 2 && (yStep + snakeStep) < World.height()
                - World.wallWidth() - colBox) {
            yStep = yStep + snakeStep;
        }
        // West
        if (direction == 3
                && (xStep - snakeStep) > World.wallWidth() + colBox) {
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

        // Add SnakeNodes to the end of the Snake
        for (int i = 0; i < 8; i++) {
            SnakeNode newNode = new SnakeNode(tail.getLastLocation(), null);

            tail.setNext(newNode);
            tail = newNode;
        }

    }

    /**
     * If the head collides with the tail, the snake is killed with the die()
     * method
     */
    public void checkHeadCollideWithTail() {

        // Loop through the Snake, starting with the Node after head
        if (size > 1) {
            SnakeNode dummy = head.next().next().next();
            while (dummy != null) {

                double dist = head.getLocation().distance(dummy.getLocation());
                if (dist < head.size()) {
                    die();
                }

                dummy = dummy.next();
            }
        }

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

    /**
     * Kills the snake and spawns another
     */
    protected void die() {
        head = new SnakeNode(snakeStartingLocation.copy(), null);
        tail = head;
        size = 0;
        direction = -1;
    }

}

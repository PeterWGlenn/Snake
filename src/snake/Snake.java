package snake;

import java.awt.Color;
import java.awt.Graphics;

import main.Apple;
import main.Game;
import main.Location;
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
    protected static final int snakeStep = (int) (10 * Game.SCALE);
    protected static final Location snakeStartingLocation = new Location(
            Game.WIDTH / 2, Game.HEIGHT / 2);

    private SnakeNode head;
    private SnakeNode tail;
    private int size;
    private int direction;
    private Color color;

    /**
     * Creates a new Snake
     */
    public Snake() {
        color = Color.GREEN;
        head = new SnakeNode(snakeStartingLocation.copy(), null, color);
        tail = head;
        size = 0;
        direction = -1;
    }

    /**
     * Creates a new Snake and sets its location to a point value. Also sets the
     * color of the snake.
     * 
     * @param c
     *            The color of the Snake
     */
    public Snake(Color c) {
        color = c;
        head = new SnakeNode(snakeStartingLocation.copy(), null, color);
        tail = head;
        size = 0;
        direction = -1;
    }

    /**
     * The update method moves the snake and tests if he is close enough to the
     * apple to eat it.
     */
    public void update(Apple apple) {
        move();
        checkHeadCollideWithApple(apple);
        checkHeadCollideWithTail();
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
        if (direction == 0) {
            if ((yStep - snakeStep) > World.wallWidth() + colBox) {
                yStep = yStep - snakeStep;
            }
            else {
                die();
                return;
            }
        }
        // East
        if (direction == 1) {
            if ((xStep + snakeStep) < World.length() - World.wallWidth()
                    - colBox) {
                xStep = xStep + snakeStep;
            }
            else {
                die();
                return;
            }
        }
        // South
        if (direction == 2) {
            if ((yStep + snakeStep) < World.height() - World.wallWidth()
                    - colBox) {
                yStep = yStep + snakeStep;
            }
            else {
                die();
                return;
            }
        }
        // West
        if (direction == 3) {

            if ((xStep - snakeStep) > World.wallWidth() + colBox) {
                xStep = xStep - snakeStep;
            }
            else {
                die();
                return;
            }
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
            SnakeNode newNode = new SnakeNode(tail.getLastLocation(), null,
                    color);

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
            SnakeNode dummy = head.next();

            // Deal with naturally colliding Nodes
            for (int i = 0; i < (head.size() / snakeStep); i++) {
                dummy = dummy.next();
            }

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
     * If the head collides with the apple, the apple is eaten by the snake
     * 
     * @param apple
     *            The apple to be eaten
     */
    public void checkHeadCollideWithApple(Apple apple) {

        double dist = head.getLocation().distance(apple.getLocation());
        if (dist < (head.size() / 2) + (Apple.size() / 2)) {
            apple.eat(this);
        }

    }

    /**
     * Checks if the Snake collides with a given Location
     * 
     * @param l
     *            The given Location
     * @return boolean
     */
    public boolean checkSnakeCollideWithLocation(Location l) {

        SnakeNode dummy = head;
        while (dummy != null) {

            double dist = dummy.getLocation().distance(l);
            if (dist < dummy.size()) {
                return true;
            }

            dummy = dummy.next();
        }

        return false;

    }

    /**
     * Renders the Snake
     * 
     * @param g
     *            The graphics parameter
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
     * Checks to see if the snake will instantly run into its tail by moving in
     * the given direction.
     * 
     * @param d
     *            The next direction.
     * @return boolean
     */
    protected boolean willNotDieInstantly(int d) {

        if (size > 0) {
            if (direction == 0 && d == 2) {
                return false;
            }
            else if (direction == 1 && d == 3) {
                return false;
            }
            else if (direction == 2 && d == 0) {
                return false;
            }
            else if (direction == 3 && d == 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Kills the snake and spawns another
     */
    protected void die() {
        head = new SnakeNode(snakeStartingLocation.copy(), null, color);
        tail = head;
        size = 0;
        direction = -1;
    }

    /**
     * Gets the size of the Snake
     * 
     * @return int
     */
    public int size() {
        return size;
    }

}

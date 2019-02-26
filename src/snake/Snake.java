package snake;

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
    private SnakeNode tail;
    private int size;

    /**
     * Creates a new Snake and sets its location to a point value
     * 
     * @param location
     *            The location of the head of the Snake
     */
    public Snake(Point location) {
        tail = new SnakeNode(location, null);
        head = new SnakeNode(location, tail);
        size = 1;
    }

    /**
     * Increases the size of the Snake by 1
     */
    public void grow() {
        size++;
    }

}

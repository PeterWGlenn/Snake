package main;

import java.awt.Color;
import java.awt.Graphics;

import snake.Snake;

/**
 * The Apple class manages an apple that spawns every time the snake eats an
 * apple. Exactly one apple appears on the screen at any one time.
 * 
 * @author Peter Glenn
 * @version 2.24.2019
 */
public class Apple {

    private static final int appleSize = (int) (50 * Game.SCALE);

    private static final int minX = appleSize / 2 + World.wallWidth();
    private static final int maxX = World.length() - World.wallWidth()
            - (appleSize / 2);
    private static final int minY = appleSize / 2 + World.wallWidth();
    private static final int maxY = World.height() - World.wallWidth()
            - (appleSize / 2);

    private Location location;

    /**
     * Creates an apple at some point p
     * 
     * @param p
     *            The location of the apple
     */
    public Apple() {
        location = Location.randomLocation(maxX, minX, maxY, minY);
    }

    /**
     * Gets the location of the apple
     * 
     * @return Point
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Moves the apple's location
     */
    public void eat(Snake snake) {

        location = Location.randomLocation(maxX, minX, maxY, minY);

        while (snake.checkSnakeCollideWithLocation(location)) {
            location = Location.randomLocation(maxX, minX, maxY, minY);
        }

        snake.grow();

    }

    /**
     * Renders an apple
     * 
     * @param g
     *            The graphics
     */
    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.fillOval(location.x - (appleSize / 2), location.y - (appleSize / 2),
                appleSize, appleSize);

    }

    /**
     * Returns the apple size
     * 
     * @return int
     */
    public static int size() {
        return appleSize;
    }

}

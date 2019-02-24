package main;

import java.awt.Point;

/**
 * The Apple class manages an apple that spawns every time the snake eats an
 * apple. Exactly one apple appears on the screen at any one time.
 * 
 * @author Peter Glenn
 * @version 2.24.2019
 */
public class Apple {

    private Point location;

    /**
     * Creates an apple at some point p
     * 
     * @param p
     *            The location of the apple
     */
    public Apple(Point p) {
        location = p;
    }

    /**
     * Gets the location of the apple
     * 
     * @return Point
     */
    public Point getLocation() {
        return location;
    }

}

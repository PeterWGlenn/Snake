package main;

import java.awt.Color;
import java.awt.Graphics;

public class World {

    private static final int viewOffetX = (Game.WIDTH / 400);
    private static final int viewOffetY = (Game.HEIGHT / 30);

    private static int length = Game.WIDTH - viewOffetX;
    private static int height = Game.HEIGHT - viewOffetY;
    private static int wallWidth = 50;

    public static int length() {
        return length;
    }

    public static int height() {
        return height;
    }

    public static int wallWidth() {
        return wallWidth;
    }

    /**
     * Renders the World object for a particular graphic
     * 
     * @param g
     *            The graphics
     */
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, length, height);

        g.setColor(Color.GRAY);
        g.fillRect(wallWidth, wallWidth, length - 2 * wallWidth,
                height - 2 * wallWidth);
    }

}

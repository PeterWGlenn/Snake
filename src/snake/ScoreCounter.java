package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.World;

/**
 * Renders and updates a text box that holds the score of the player
 * 
 * @author Peter Glenn
 * @version 3.7.2019
 *
 */
public class ScoreCounter {

    private static final int fromTheLeft = (int) (30 * Game.SCALE)
            + World.wallWidth();
    private static final int fromTheTop = (int) (10 * Game.SCALE)
            + World.wallWidth();
    private static final int textHeight = (int) (50 * Game.SCALE);

    private static int highScore;

    private Snake snake;

    /**
     * Creates a new score counter for a given snake
     * 
     * @param s
     *            The snake to count for
     */
    public ScoreCounter(Snake s) {
        snake = s;
        highScore = 0;
    }

    /**
     * Renders the text for the score
     * 
     * @param g
     *            The graphics
     */
    public void render(Graphics g) {

        Font font = new Font("TimesRoman", Font.PLAIN, textHeight);

        g.setColor(Color.YELLOW);
        g.setFont(font);

        g.drawString("Score: " + snake.size(), fromTheLeft,
                fromTheTop + textHeight);

        if (snake.size() > highScore) {
            highScore = snake.size();
        }
        g.drawString("High Score: " + highScore, fromTheLeft,
                2 * fromTheTop + textHeight);

    }

}

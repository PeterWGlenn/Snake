package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardInput class tests for key presses and updates the Snake
 * accordingly.
 * 
 * @author Peter Glenn
 * @version 3.7.19
 *
 */
public class KeyboardInput extends KeyAdapter implements KeyListener {

    private Snake snake;

    /**
     * Construct a new KeyboardInput for a given Snake
     * 
     * @param n
     *            The Snake that gets updated by the keyboard input
     */
    public KeyboardInput(Snake n) {
        snake = n;
    }

    /**
     * Tests for certain key presses and moves the Snake field
     * 
     * @param e
     *            The KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int c = e.getKeyCode();

        // Movement
        if (c == KeyEvent.VK_UP || c == KeyEvent.VK_W) {
            snake.setDirection(0);
        }

        if (c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_D) {
            snake.setDirection(1);
        }

        if (c == KeyEvent.VK_DOWN || c == KeyEvent.VK_S) {
            snake.setDirection(2);
        }

        if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_A) {
            snake.setDirection(3);
        }

        // Debug
        if (c == KeyEvent.VK_G) {
            snake.grow();
        }
        if (c == KeyEvent.VK_K) {
            snake.die();
        }

    }

}

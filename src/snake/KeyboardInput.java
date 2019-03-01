package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput extends KeyAdapter implements KeyListener {

    private Snake snake;

    public KeyboardInput(Snake n) {
        snake = n;
    }

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

    }

}

package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snake.Snake;

public class Game extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    private static boolean isRunning;

    public static final double SCALE = 0.5;
    public static final int WIDTH = (int) (2500 * SCALE);
    public static final int HEIGHT = (int) (1500 * SCALE);
    public static final int FPS = 60;

    private static Game gameComponent = new Game();
    private static JFrame frame = new JFrame();

    private static Snake snake = new Snake(new Point(WIDTH / 2, HEIGHT / 2));

    public Game() {
        setFocusable(true);
        requestFocus();
        start();
    }

    public void start() {
        isRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {

        while (isRunning) {

            try {

                // Update Snake
                snake.update();

                // Repaint
                repaint();

                double tick = 1000.0 / FPS;
                Thread.sleep((long) tick);

            }
            catch (Exception e) {
                System.out.println("Game loop exception: " + e.toString());
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint World
        // world.render(g);

        // Paint Snake
        snake.render(g);

        // Paint Apples

        // Paint Text Area
    }

    public static void main(String[] args) {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        frame.setTitle("Snake");
        frame.setVisible(true);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameComponent);

        // Keyboard Input
        // frame.addKeyListener(new KeyboardInput());

        // Spawn Snake and Apple
        // Blob.reset();
    }

}

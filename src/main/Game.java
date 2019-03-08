package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snake.KeyboardInput;
import snake.ScoreCounter;
import snake.Snake;

/**
 * The Game class holds the Snake, World, and Apple objects and manages the Game
 * clock. It updates the Snake and renders the needed object.
 * 
 * @author Peter Glenn
 * @version 3.7.19
 *
 */
public class Game extends JPanel implements Runnable {

    /**
     * The scale at which the game is rendered
     */
    public static final double SCALE = 1.0;
    /**
     * The width of the frame in pixels
     */
    public static final int WIDTH = (int) (1920 * SCALE);
    /**
     * The height of the frame in pixels
     */
    public static final int HEIGHT = (int) (1080 * SCALE);
    /**
     * The frames per second of the game
     */
    public static final int FPS = 60;

    private static final long serialVersionUID = 1L;
    private static boolean isRunning;
    private static Game gameComponent = new Game();
    private static JFrame frame = new JFrame();

    private static Snake snake = new Snake();
    private static World world = new World();
    private static Apple apple = new Apple();
    private static ScoreCounter scoreCounter = new ScoreCounter(snake);

    /**
     * Initializes a new Game object
     */
    private Game() {
        setFocusable(true);
        requestFocus();
        start();
    }

    /**
     * Starts the game by setting isRunning to true.
     */
    private void start() {
        isRunning = true;
        new Thread(this).start();
    }

    /**
     * Quits the game loop by setting isRunning to false.
     */
    private void stop() {
        isRunning = false;
    }

    /**
     * The main game loop updates the snake object, repaints all the objects,
     * and then waits for an appropriate amount of ticks. The loop breaks when
     * isRunning is false.
     */
    @Override
    public void run() {

        while (isRunning) {

            try {

                // Update Snake
                snake.update(apple);

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

    /**
     * Renders the world, snake, apple, and scoreCounter for a particular
     * graphics
     * 
     * @param g
     *            The graphics to render to
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint World
        world.render(g);

        // Paint Snake
        snake.render(g);

        // Paint Apples
        apple.render(g);

        // Paint Score Counter
        scoreCounter.render(g);

    }

    /**
     * The main method sets up the frame for the Game and adds a key listener
     * 
     * @param args
     *            The arguments
     */
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
        frame.addKeyListener(new KeyboardInput(snake));
    }

}

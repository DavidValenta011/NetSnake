package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.Clip;

/**
 * 
 * @author david
 *
 */
public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
	public static final int B_WIDTH = 600;
    public static final int B_HEIGHT = 300;
    public static final int DOT_SIZE = 30;
    private static int DELAY = 140; // Inverted value of game speed


    private boolean inGame = true;

    private Timer timer;
    Apple apple;
    Snake snake;
    Snake snake2;
    Clip errorSound;

    public Board() {
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        this.setLayout(null);
        snake = new Snake();
        snake.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(snake);
        snake2 = new Snake();
        snake2.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(snake2);
        apple = new Apple(B_WIDTH / DOT_SIZE, B_HEIGHT / DOT_SIZE);
        apple.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(apple, BorderLayout.NORTH);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }

    
    private void initGame() {
    	errorSound = Load.sound("src/resources/WinXPError.wav");
        timer = new Timer(DELAY, this);
        timer.start();
        
        snake2.initSnake(snake.getDirection(), 8, 8);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            //g.drawImage(apple, apple_x, apple_y, this);

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if (snake.CheckCollisions(apple)) {
        	snake.AddDot();
        	apple.setRandomLocation();
        }
    }

    private void checkCollision() {

        //inGame = snake.checkCollision();
        
        if (snake.CheckCollisions(snake)) {
        	playSound(errorSound);
        	inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (inGame) {
        	
            checkApple();
            checkCollision();
            snake.move();
            snake2.move();
        }

        repaint();
    }
    
    private static void playSound(Clip clip) {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);  // Rewind to the beginning of the sound
            clip.start();  // Start playing the sound
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (snake.getDirection() != Direction.Right)) {
                snake.ChangeDirection(Direction.Left);
                snake2.ChangeDirection(Direction.Left);
            }
            if ((key == KeyEvent.VK_RIGHT) && (snake.getDirection() != Direction.Left)) {
            	snake.ChangeDirection(Direction.Right);
            	snake2.ChangeDirection(Direction.Right);
            }
            if ((key == KeyEvent.VK_UP) && (snake.getDirection() != Direction.Down)) {
            	snake.ChangeDirection(Direction.Up);
            	snake2.ChangeDirection(Direction.Up);
            }
            if ((key == KeyEvent.VK_DOWN) && (snake.getDirection() != Direction.Up)) {
            	snake.ChangeDirection(Direction.Down);
            	snake2.ChangeDirection(Direction.Down);
            }
            if (key == KeyEvent.VK_SPACE) {
            	/*inGame = true;
            	initBoard();
            	initGame();
            	snake.initSnake(Direction.Left, 5, 5);*/
            }
        }
    }
}

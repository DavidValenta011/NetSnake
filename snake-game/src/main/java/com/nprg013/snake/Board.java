package com.nprg013.snake;

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

enum GameMode {
	SinglePlayer,
	SinglePlayerInfinity,
	MultiPlayerMaster,
	MultiPlyerSlave
}

/**
 * 
 * @author david
 *
 */
public class Board extends JPanel implements ActionListener, GameFrame {
    private static final long serialVersionUID = 1L;

	private boolean inGame = true;

    private Timer timer;
    Apple apple;
    Snake snake;
    Snake snake2;
    Obstacles obstacles;
    Clip errorSound;
    
    GameMode mode;

    public Board() {
    	mode = GameMode.SinglePlayerInfinity;
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        this.setLayout(null);
        snake = new Snake(mode, this);
        snake.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(snake);
        snake2 = new Snake(mode, this);
        snake2.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        //this.add(snake2);
        apple = new Apple(B_WIDTH / DOT_SIZE, B_HEIGHT / DOT_SIZE);
        apple.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(apple, BorderLayout.NORTH);
        
        obstacles = new Obstacles(ObstaclePlacementMode.Play);
        obstacles.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(obstacles);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        timer = new Timer(DELAY, this);
        
        errorSound = Load.sound("src/resources/WinXPError.wav");
        
        restartGame();
    }

    
    private void restartGame() {
    	inGame = true;
        
        timer.start();
        
        snake.initSnake(snake.getDirection(), 5,  5);
        obstacles.RegenerateRandomObstacles(snake.GetSquares());
        apple.setRandomLocation(obstacles.GetSquares());
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

    /**
     * 
     * Check collision with apple
     */
    private void checkApple() {
        if (snake.CheckCollisions(apple)) {
        	snake.AddDot();
        	apple.setRandomLocation(obstacles.GetSquares());
        }
    }

    private void checkCollision() {

        //inGame = snake.checkCollision();
        
        if (snake.CheckCollisions(snake) || snake.CheckCollisions(obstacles)) {
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
            //snake2.move();
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
            	restartGame();
            }
        }
    }
}

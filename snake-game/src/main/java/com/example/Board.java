package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
	private static int B_WIDTH = 300;
    private static int B_HEIGHT = 300;
    public static int DOT_SIZE = 25;
    private static int DELAY = 140;

    private int apple_x;
    private int apple_y;

    private boolean inGame = true;

    private Timer timer;
    
    private Image apple;
    
    Apple apple2;
    Snake snake;

    public Board() {
        
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        this.setLayout(null);
        snake = new Snake(DOT_SIZE, B_WIDTH, B_HEIGHT);
        snake.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(snake);
        apple2 = new Apple(B_WIDTH / DOT_SIZE, B_HEIGHT / DOT_SIZE);
        apple2.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        this.add(apple2, BorderLayout.NORTH);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }

    private void initGame() {
        
        System.out.println("initializátor");
        timer = new Timer(DELAY, this);
        timer.start();
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

        /*if ((snake.x[0] == apple_x) && (snake.y[0] == apple_y)) {

            snake.AddDot();
            locateApple();
        }*/
        if (snake.CheckCollisions(apple2)) {
        	snake.AddDot();
        	apple2.setRandomLocation();
        }
    }

    private void checkCollision() {

        //inGame = snake.checkCollision();
        
        if (snake.CheckCollisions(snake)) {
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
            
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (snake.getDirection() != Direction.Right)) {
                snake.ChangeDirection(Direction.Left);
            }

            if ((key == KeyEvent.VK_RIGHT) && (snake.getDirection() != Direction.Left)) {
            	snake.ChangeDirection(Direction.Right);
            }

            if ((key == KeyEvent.VK_UP) && (snake.getDirection() != Direction.Down)) {
            	snake.ChangeDirection(Direction.Up);
            }

            if ((key == KeyEvent.VK_DOWN) && (snake.getDirection() != Direction.Up)) {
            	snake.ChangeDirection(Direction.Down);
            }
        }
    }
}

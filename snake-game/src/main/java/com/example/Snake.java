package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;



enum Direction {
	Left,
	Right,
	Up,
	Down
}

public class Snake extends JComponent implements GameObject {
    private static final long serialVersionUID = 1L;
	private final int DOT_SIZE;
    private final int B_WIDTH;
    private final int B_HEIGHT;

    public int[] x;
    public int[] y;
    
    public Square[] squares;
    
    
    private int dots;

    private boolean leftDirection;
    private boolean rightDirection;
    private boolean upDirection;
    private boolean downDirection;
    
    private Image ball;
    private Image head;

    public Snake(int dotSize, int bWidth, int bHeight) {
        DOT_SIZE = dotSize;
        B_WIDTH = bWidth;
        B_HEIGHT = bHeight;

        x = new int[(B_WIDTH * B_WIDTH) / (DOT_SIZE * DOT_SIZE)];
        y = new int[(B_WIDTH * B_WIDTH) / (DOT_SIZE * DOT_SIZE)];
        
        squares = new Square[(B_WIDTH * B_WIDTH) / (DOT_SIZE * DOT_SIZE)];

        // Initialize the snake's starting position and direction
        loadImages();
        initSnake();
    }

    private void initSnake() {
        dots = 3;

        // Set the initial coordinates for the snake's body
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
            
            squares[z] = new Square(5 - z, 5);
        }
        
        for (int z = dots; z < squares.length; z++) {
        	squares[z] = new Square(0, 0);
        }

        // Set the initial direction
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }
    
    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();
        
        if (iid.getImageLoadStatus() == MediaTracker.COMPLETE) {
            System.out.println("Ball se načetl");
        } else {
        	System.out.println("Ball se nenačetl");
        }

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    public boolean CheckCollisions(GameObject other) {
    	if (other == this) {
    		return checkCollision();
    	}
    	for (Square square : other.GetSquares()) {
    		for (int i = 0; i < dots; i++) {
    			if (square.x == squares[i].x && square.y == squares[i].y) {
    				return true;
    			}
    		}
		}
		return false;
    }
	public ArrayList<Square> GetSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		for (int i = 0; i < dots; i++) {
			result.add(new Square(squares[i].x, squares[i].y));
		}
		return result;
		// Zmena jestli se ukaze naGITITIT§§§
	}
    
    public void move() {
        // Move the snake's body segments
        /*for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }*/
        
        for (int z = dots; z > 0; z--) {
            squares[z] = new Square(squares[z - 1].x, squares[z - 1].y);
        }

        // Move the snake's head based on the current direction
        /*if (leftDirection) {
            x[0] -= DOT_SIZE;
        } else if (rightDirection) {
            x[0] += DOT_SIZE;
        } else if (upDirection) {
            y[0] -= DOT_SIZE;
        } else if (downDirection) {
            y[0] += DOT_SIZE;
        }*/
        
        squares[0].Move(getDirection());
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(B_HEIGHT, B_WIDTH);
    }

    // Override the paintComponent method to render the snake
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*for (int z = 0; z < dots; z++) {
            if (z == 0) {
                g.drawImage(head, x[z], y[z], this);
            } else {
                g.drawImage(ball, x[z], y[z], this);
            }
        }*/
        
        for (int z = 0; z < dots; z++) {
            if (z == 0) {
                g.drawImage(head, squares[z].x * DOT_SIZE, squares[z].y * DOT_SIZE, this);
            } else {
                g.drawImage(ball, squares[z].x * DOT_SIZE, squares[z].y * DOT_SIZE, this);
            }
        }
    }
    
    public void ChangeDirection(Direction direction) {
    	leftDirection = false;
        rightDirection = false;
        upDirection = false;
        downDirection = false;
        
        switch (direction) {
        case Left:
        	leftDirection = true;
        	break;
        case Right:
        	rightDirection = true;
        	break;
        case Up:
        	upDirection = true;
        	break;
        case Down:
        	downDirection = true;
        	break;
        	
        }
    }
    
    public void AddDot() {
    	dots++;
    }
    
    public boolean checkCollision() {

    	boolean result = true;
        /*for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                result= false;
            }
        }
        if (y[0] >= B_HEIGHT) {
        	result = false;
        }
        if (y[0] < 0) {
        	result = false;
        }
        if (x[0] >= B_WIDTH) {
        	result = false;
        }
        if (x[0] < 0) {
        	result = false;
        }*/
        
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (squares[0].x == squares[z].x) && (squares[0].y == squares[z].y)) {
                result= false;
            }
        }
        if (squares[0].y * DOT_SIZE >= B_HEIGHT) {
        	result = false;
        }
        if (squares[0].y < 0) {
        	result = false;
        }
        if (squares[0].x * DOT_SIZE >= B_WIDTH) {
        	result = false;
        }
        if (squares[0].x < 0) {
        	result = false;
        }
        
        return !result;
    }
    
    public Direction getDirection() {
    	if (leftDirection) {
    		return Direction.Left;
    	}
    	if (rightDirection) {
    		return Direction.Right;
    	}
    	if (upDirection) {
    		return Direction.Up;
    	}
    	if (downDirection) {
    		return Direction.Down;
    	}
    	else {
    		return Direction.Left;
    	}
    }
}







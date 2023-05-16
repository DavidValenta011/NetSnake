package com.example;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JComponent;



enum Direction {
	Left,
	Right,
	Up,
	Down
}

/**
 * 
 * @author david
 *
 */
public class Snake extends JComponent implements GameObject {
    private static final long serialVersionUID = 1L;
    public Square[] squares;
    
    
    private int dots;
    
    private Direction direction;
    
    private Image ball;
    private Image head;

    /**
     * 
     */
    public Snake() {
        
        squares = new Square[(B_HEIGHT * B_WIDTH) / (DOT_SIZE * DOT_SIZE)];

        // Initialize the snake's starting position and direction
        loadImages();
        initSnake(Direction.Left, 5, 5);
    }

    /**
     * 
     * @param direction
     * @param x
     * @param y
     */
    public void initSnake(Direction direction, int x, int y) {
        dots = 3;

        // Set the initial coordinates for the snake's body
        for (int i = 0; i < dots; i++) {
        	switch (direction) {
        	case Left:
        		squares[i] = new Square(x + i, y);
        		break;
        	case Right:
        		squares[i] = new Square(x - i, y);
        		break;
        	case Down:
        		squares[i] = new Square(x, y - i);
        		break;
        	case Up:
        		squares[i] = new Square(x, y + i);
        		break;
        	default:
        		squares[i] = new Square(0, 0);
        		break;
        	}
        }
        
        // Dont forget to init remaining dots to an arbitrary value
        for (int i = dots; i < squares.length; i++) {
        	squares[i] = new Square(0, 0);
        }

        // Set the initial direction      
        this.direction = direction;
    }
    
    private void loadImages() {        
        ball = Load.squareImage("src/resources/dot.png",  DOT_SIZE);
        head = Load.squareImage("src/resources/head.png",  DOT_SIZE);
    }

    public boolean CheckCollisions(GameObject other) {
    	if (other == this) {
    		return checkCollisionItself();
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
    
    /**
     * Checks if the snake collides with itself or the game border.
     * @return Snake collides itself or game border
     */
    private boolean checkCollisionItself() {  
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (squares[0].x == squares[z].x) && (squares[0].y == squares[z].y)) {
                return true;
            }
        }
        if (squares[0].y * DOT_SIZE >= B_HEIGHT) {
        	return true;
        }
        if (squares[0].y < 0) {
        	return true;
        }
        if (squares[0].x * DOT_SIZE >= B_WIDTH) {
        	return true;
        }
        if (squares[0].x < 0) {
        	return true;
        }
        
        return false;
    }
    
	public ArrayList<Square> GetSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		for (int i = 0; i < dots; i++) {
			result.add(new Square(squares[i].x, squares[i].y));
		}
		return result;
	}
    
	/**
	 * Makes snake to move to neighbor position, which is determined
	 * by snake direction.
	 * 
	 */
    public void move() {
        // Move the snake's body segments      
        for (int z = dots; z > 0; z--) {
            squares[z] = new Square(squares[z - 1].x, squares[z - 1].y);
        }
        
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
        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                g.drawImage(head, squares[i].x * DOT_SIZE, squares[i].y * DOT_SIZE, this);
            } else {
                g.drawImage(ball, squares[i].x * DOT_SIZE, squares[i].y * DOT_SIZE, this);
            }
        }
    }
    
    /**
     * Setter for snake direction
     * @param direction Direction to set
     */
    public void ChangeDirection(Direction direction) {
    	this.direction = direction;
    }
    
    /**
     * Getter for snake direction
     * @return Current direction of the snake
     */
    public Direction getDirection() {
    	return direction;
    }
    
    /**
     * Increments snake length
     */
    public void AddDot() {
    	dots++;
    	System.out.println("Actual score=" + (dots - 3));
    }
}







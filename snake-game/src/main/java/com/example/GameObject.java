package com.example;

import java.util.ArrayList;

/**
 * Interface for game objects
 * @author david
 *
 */
public interface GameObject
{
	final int DOT_SIZE = Board.DOT_SIZE;
	final int B_HEIGHT = Board.B_HEIGHT;
	final int B_WIDTH = Board.B_WIDTH;
	
	/**
	 * Checks if this game objects collides with the given game
	 * object. 
	 * Please note, that we are calculating the offset (square)
	 * coordinates, not the pixel coordinates
	 * @param other game object to inspect
	 * @return objects collides
	 */
	boolean CheckCollisions(GameObject other);
	
	/**
	 * 
	 * @return ArrayList of Squares where is this game object defined
	 */
	ArrayList<Square> GetSquares();
}

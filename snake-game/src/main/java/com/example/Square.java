package com.example;

/**
 * Auxiliary object, that stores two integer coordinates
 * @author david
 *
 */
public class Square
{
	/**
	 * Initializes the pair of coordinates
	 * @param x
	 * @param y
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
	
	/**
	 * Increment or decrement x or y coordinate according to the
	 * given direction
	 * @param direction
	 */
	public void Move(Direction direction) {
		switch (direction) {
			case Left:
				x--;
				break;
			case Right:
				x++;
				break;
			case Up:
				y--;
				break;
			case Down:
				y++;
				break;
		}
	}
}

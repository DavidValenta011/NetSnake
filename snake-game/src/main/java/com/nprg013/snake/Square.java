package com.nprg013.snake;

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
	
	public void MoveToOppositeSite(Direction direction, int width, int height, int dotSize) {
		/*if (x >= 0 && x < width / dotSize && y >= 0 && y < height / dotSize) {
			return;
		}
		switch (direction) {
		case Left:
			x = x + (width / dotSize + 1);
			break;
		case Right:
			x = x - (width / dotSize + 1);
			break;
		case Up:
			y = y + (height / dotSize + 1);
			break;
		case Down:
			y = y - (height / dotSize + 1);
			break;
		}*/
		
		if (x < 0) {
			x += (width / dotSize);
		}
		if (x >= width /dotSize) {
			x -= (width / dotSize);
		}
		if (y < 0) {
			y += (height / dotSize);
		}
		if (y >= height / dotSize) {
			y -= (height / dotSize);
		}
	}
}

package com.example;

public class Square
{
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
	
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

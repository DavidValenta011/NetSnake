package com.example;

import java.util.ArrayList;

public interface GameObject
{
	final int DOT_SIZE = Board.DOT_SIZE;
	final int B_HEIGHT = Board.B_HEIGHT;
	final int B_WIDTH = Board.B_WIDTH;
	boolean CheckCollisions(GameObject other);
	ArrayList<Square> GetSquares();
}

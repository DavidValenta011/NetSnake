package com.example;

import java.util.ArrayList;

public interface GameObject
{
	int DOT_SIZE = Board.DOT_SIZE;
	boolean CheckCollisions(GameObject other);
	ArrayList<Square> GetSquares();
}

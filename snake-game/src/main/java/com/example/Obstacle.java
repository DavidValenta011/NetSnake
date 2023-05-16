package com.example;

import java.util.ArrayList;

public class Obstacle implements GameObject
{
	public boolean CheckCollisions(GameObject other) {
		return true;
	}
	public ArrayList<Square> GetSquares() {
		return new ArrayList<Square>();
	}
}

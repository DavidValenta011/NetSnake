package com.nprg013.snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;


enum ObstaclePlacementMode {
	Edit,
	Play
}

public class Obstacles extends JComponent implements GameObject
{
	private static final long serialVersionUID = 1L;
	ObstaclePlacementMode mode;
	private ArrayList<Square> positions;
	private Image img;
	
	public Obstacles(ObstaclePlacementMode mode) {
		this.mode = mode;
		positions = new ArrayList<Square>();
		loadImages();
	}
	
	public Obstacles(ObstaclePlacementMode mode, String filename) {
		this.mode = mode;
		loadImages();
	}
	
	private void loadImages() {        
        img = Load.squareImage("src/resources/stone.png",  DOT_SIZE);
    }
	
	public boolean CheckCollisions(GameObject other) {
		for (Square square : other.GetSquares()) {
    		for (int i = 0; i < positions.size(); i++) {
    			if (square.x == positions.get(i).x && square.y == positions.get(i).y) {
    				return true;
    			}
    		}
		}
		return false;
	}
	
	public ArrayList<Square> GetSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		for (int i = 0; i < positions.size(); i++) {
			result.add(new Square(positions.get(i).x, positions.get(i).y));
		}
		return result;
	}
	
	public void RegenerateRandomObstacles(ArrayList<Square> occupiedPositions) {
		positions = new ArrayList<Square>();
		Random random = new Random();

        // Generate a random integer between 0 and 9
        int randomNumber = random.nextInt(10);
        
        int maxObstacleCount = ((B_HEIGHT * B_WIDTH) / (DOT_SIZE * DOT_SIZE)) / 60;
        int minObstacleCount = ((B_HEIGHT * B_WIDTH) / (DOT_SIZE * DOT_SIZE)) / 80;
        int randomObstacleCount = random.nextInt(maxObstacleCount - minObstacleCount + 1) + minObstacleCount;
        int counter = 0;
        int padding = 2;
        outer: while (counter < randomObstacleCount) {
        	//B_WIDTH / DOT_SIZE, B_HEIGHT / DOT_SIZE
        	int randX = random.nextInt(B_WIDTH / DOT_SIZE);
        	int randY = random.nextInt(B_HEIGHT / DOT_SIZE);
        	for (int i = 0; i < occupiedPositions.size(); i++) {
        		if (Math.abs(randX - occupiedPositions.get(i).x) <= padding && Math.abs(randY - occupiedPositions.get(i).y) <= padding) {
        			continue outer;
        		}
        	}
        	positions.add(new Square(randX, randY));
        	counter++;
        }
	}
	
	private void AddObstacle(int x, int y) {
		positions.add(new Square(x, y));
	}
	
	private void DeleteObstacle(int x, int y) {
		positions.removeIf(item -> item.x == x && item.y == y);
	}
	
	public void ObstacleEditClick(int x, int y) {
		if (positions.stream().anyMatch(item -> item.x == x && item.y == y)) {
			DeleteObstacle(x, y);
		}
		else {
			AddObstacle(x, y);
		}
	}
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(B_WIDTH, B_HEIGHT);
    }

    // Override the paintComponent method to render the snake
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        
        for (int i = 0; i < positions.size(); i++) {
            g.drawImage(img, positions.get(i).x * DOT_SIZE, positions.get(i).y * DOT_SIZE, this);
        }
    }
	
	private void LoadFromFile() {
		
	}
}

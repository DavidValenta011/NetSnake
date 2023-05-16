package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.util.Random;


public class Apple extends JComponent implements GameObject
{
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	
	private int rangeX;
	private int rangeY;
	
	private Image img;
	
	private Random random;
	
	public Apple(int rangeX, int rangeY) {
		random = new Random();
		this.rangeX = rangeX;
		this.rangeY = rangeY;
		loadImages();
		setRandomLocation();
	}
	
	private void loadImages() {

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        img = iia.getImage();
    }
	
	public void setRandomLocation() {
		x = random.nextInt(rangeX);
		y = random.nextInt(rangeY);
		System.out.print(Integer.toString(x) + "§§" + Integer.toString(y));
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    // Override the paintComponent method to render the snake
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.fillRect(10, 10, 20000, 15000); // Specify the rectangle's position and dimensions
        g.drawImage(img, x * DOT_SIZE, y * DOT_SIZE, this);
    }
	
	public boolean CheckCollisions(GameObject other) {
		for (Square square : other.GetSquares()) {
			if (square.x == x && square.y == y) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Square> GetSquares() {
		return new ArrayList<Square>(Arrays.asList(new Square(x, y)));
	}
}

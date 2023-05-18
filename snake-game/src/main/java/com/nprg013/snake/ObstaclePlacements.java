package com.nprg013.snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ObstaclePlacements extends JPanel implements GameFrame
{
	private static final long serialVersionUID = 1L;
	
	private final Obstacles obstacles;
	
	public ObstaclePlacements() {
		this.setLayout(null);
		setBackground(Color.black);
		obstacles = new Obstacles(ObstaclePlacementMode.Play);
        obstacles.setBounds(0, 0, B_WIDTH, B_HEIGHT);
        obstacles.RegenerateRandomObstacles(new ArrayList<Square>());
        this.add(obstacles);
        System.out.println("NR of obstacles: " + obstacles.GetSquares().size());
        
        // Add a mouse listener to the panel
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(); // X-coordinate of the mouse click
                int y = e.getY(); // Y-coordinate of the mouse click
                obstacles.ObstacleEditClick(x / DOT_SIZE, y / DOT_SIZE);
                repaint();
                System.out.println("Mouse clicked at position: (" + x + ", " + y + ")");
                
            }
        });
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(B_WIDTH, B_HEIGHT);
    }

    // Override the paintComponent method to render the snake
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	String msg = "Obstacles in conflict with snake starting position will be removed.";
        Font small = new Font("Helvetica", Font.BOLD, 9);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 9, B_HEIGHT - 9);
        
        String msg2 = "Click on the board to add or remove an obstacle.";
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg2, 9, 15);
    }

}

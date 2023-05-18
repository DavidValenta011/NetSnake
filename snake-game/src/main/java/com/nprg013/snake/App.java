package com.nprg013.snake;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * 
 * @author david
 *
 */
public class App extends JFrame {

    private static final long serialVersionUID = 1L;
	public static final int B_WIDTH = 600;
    public static final int B_HEIGHT = 600;
    public static final int DOT_SIZE = 30;
    public static int DELAY = 140; // Inverted value of game speed

	public App() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new ObstaclePlacements());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new App();
            ex.setVisible(true);
        });
    }
}
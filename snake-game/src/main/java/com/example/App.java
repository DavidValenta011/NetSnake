package com.example;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * 
 * @author david
 *
 */
public class App extends JFrame {

    private static final long serialVersionUID = 1L;

	public App() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
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
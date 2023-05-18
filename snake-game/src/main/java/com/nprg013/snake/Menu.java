package com.nprg013.snake;

import javax.swing.*;
import java.awt.*;


public class Menu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JButton addButton;
    private JButton removeButton;
    private JList<String> listBox;

    public Menu() {
        super("Swing Form Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager for the JFrame
        setLayout(new BorderLayout());

        // Create the buttons and list box
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        listBox = new JList<>(new DefaultListModel<>());

        // Add the buttons and list box to the content pane
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JPanel listBoxPanel = new JPanel(new BorderLayout());
        listBoxPanel.add(new JScrollPane(listBox), BorderLayout.CENTER);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(listBoxPanel, BorderLayout.CENTER);

        // Set the form size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

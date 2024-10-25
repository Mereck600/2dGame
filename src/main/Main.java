package main;

import javax.swing.JFrame;

public class Main {
	/**
	 * 
	 * This is creating the game window
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Legends of Aetheria"); //adds the title to the game window
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		
		window.pack(); //This makes the window to be sized to the preferred size and layout of the subcomponents
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		
		gamePanel.startGameThread();
		
		
	}

}

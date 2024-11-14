package main;

import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;

public void saveGame(GamePanel gp) {
    try (PrintWriter writer = new PrintWriter(new FileWriter("savedata.txt"))) {
        // Save player position
        writer.println("playerPositionX=" + gp.player.worldX);
        writer.println("playerPositionY=" + gp.player.worldY);

        // Save other data (like player health, inventory, etc.)
        writer.println("playerHealth=" + gp.player.life);
        writer.println("playerLevel=" + gp.player.level);
        
        // Add more lines as needed for other player attributes or game state
        System.out.println("Game saved successfully.");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

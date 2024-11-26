package main;
import java.io.*;

import entity.Entity;
import object.OBJ_Sheild_Wood;
import object.OBJ_Sword_Normal;

public class GameSaveLoad {

    // Save method
    public void SaveGame(GamePanel gp, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);

            // Writing player data to file as space-separated values
            writer.write(gp.player.worldX + " " +
                         gp.player.worldY + " " +
                         gp.player.level + " " +
                         gp.player.maxLife + " " +
                         gp.player.strength + " " +
                         gp.player.attack + " " +
                         gp.player.exp + " " +
                         gp.player.nextLevelExp + " " +
                         gp.player.coin + " " +
                         gp.player.currentWeapon + " " + // Example: Weapon ID
                         gp.player.currentSheild);        // Example: Shield ID

            writer.close();
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
            e.printStackTrace();
        }
    }
    

    // Load method
    public void LoadSave(GamePanel gp, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Read the line and split by spaces
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(" ");

                // Assign values to the player's attributes based on the read data
                gp.player.worldX = Integer.parseInt(data[0]);
                gp.player.worldY = Integer.parseInt(data[1]);
                gp.player.level = Integer.parseInt(data[2]);
                gp.player.maxLife = Integer.parseInt(data[3]);
                gp.player.strength = Integer.parseInt(data[4]);
                gp.player.attack = Integer.parseInt(data[5]);
                gp.player.exp = Integer.parseInt(data[6]);
                gp.player.nextLevelExp = Integer.parseInt(data[7]);
                gp.player.coin = Integer.parseInt(data[8]);
                
                // Eventually make this able to be set by id because it will be pain in asshole otherwise
              //  gp.player.currentWeapon = new Entity(Integer.parseInt(data[9])); // Weapon ID
               // gp.player.currentSheild = new Entity(Integer.parseInt(data[10])); // Shield ID
                gp.player.currentWeapon = new OBJ_Sword_Normal(gp);
                gp.player.currentSheild = new OBJ_Sheild_Wood(gp);
                
            }

            reader.close();
            System.out.println("Game loaded successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while loading the game.");
            e.printStackTrace();
        }
    }
}

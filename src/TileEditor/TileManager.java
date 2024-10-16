package TileEditor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class TileManager {
    public Tile[] tile;
    public int[][] mapTileNum;
    public final int tileSize = 48; // size of each tile
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    private int currentTile = 0; // The currently selected tile index

    public TileManager() {
        tile = new Tile[50]; // Assume you have 50 different tiles
        mapTileNum = new int[maxWorldCol][maxWorldRow];
        getTileImages();
        loadMap("src/maps/worldV2.txt"); // Load initial map
    }

    public void getTileImages() {
    	setup(0, "grass00"); //grass
		setup(1, "grass00"); //wall
		setup(2, "grass00"); //water
		setup(3, "grass00"); //earth
		setup(4, "grass00"); //tree
		setup(5, "grass00"); //sand
		setup(6, "grass00"); //sand
		setup(7, "grass00"); //sand
		setup(8, "grass00"); //sand
		setup(9, "grass00"); //sand
		//actual tiles
		setup(10, "grass00"); // all above are actually grass but dont use them 
		setup(11, "grass01"); //grass
		//water
		setup(12, "water00"); //wall
		setup(13, "water01"); //water
		setup(14, "water02"); //earth
		setup(15, "water03"); //tree
		setup(16, "water04"); //sand
		setup(17, "water05"); //sand
		setup(18, "water06"); //sand
		setup(19, "water07"); //sand
		setup(20, "water08"); //sand
		setup(21, "water09");
		setup(22, "water10"); //sand
		setup(23, "water11");
		setup(24, "water12"); //sand
		setup(25, "water13");
		//road
		setup(26, "road00"); 
		setup(27, "road01"); 
		setup(28, "road02");
		setup(29, "road03"); 
		setup(30, "road04"); 
		setup(31, "road05"); 
		setup(32, "road06"); 
		setup(33, "road07"); 
		setup(34, "road08"); 
		setup(35, "road09"); 
		setup(36, "road10"); 
		setup(37, "road11"); 
		setup(38, "road12"); 
		//earth
		setup(39, "earth"); //earth
		setup(40, "wall"); //wall
		setup(41, "tree"); //tree
    }

    public void setup(int index, String imageName) {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentTile(int tileIndex) {
        currentTile = tileIndex; // Set selected tile index
    }

    public void setTileAt(int col, int row) {
        if (col < maxWorldCol && row < maxWorldRow) {
            mapTileNum[col][row] = currentTile; // Update map with the selected tile
        }
    }

    public void saveMap(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int row = 0; row < maxWorldRow; row++) {
                for (int col = 0; col < maxWorldCol; col++) {
                    writer.write(mapTileNum[col][row] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int row = 0; row < maxWorldRow; row++) {
                String[] line = reader.readLine().split(" ");
                for (int col = 0; col < maxWorldCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(line[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportMapAsImage(String filePath) {
        int mapWidth = maxWorldCol * tileSize;
        int mapHeight = maxWorldRow * tileSize;

        // Create a BufferedImage of the map size
        BufferedImage mapImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = mapImage.createGraphics();

        // Draw the entire map onto the BufferedImage
        draw(g2, mapWidth, mapHeight, tileSize);

        g2.dispose();

        try {
            // Save the BufferedImage as a file
            File outputFile = new File(filePath + ".png");
            ImageIO.write(mapImage, "png", outputFile);
            System.out.println("Map image exported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw a single tile (used by TilePanel for dynamic drawing)
    public void drawTile(Graphics2D g2, int col, int row, int tileSize) {
        if (col < maxWorldCol && row < maxWorldRow) {
            int tileIndex = mapTileNum[col][row];
            BufferedImage image = tile[tileIndex].image;

            // Calculate position of tile
            int x = col * tileSize;
            int y = row * tileSize;

            // Draw the tile
            g2.drawImage(image, x, y, tileSize, tileSize, null);
        }
    }

    // Draw method to draw the entire map
    public void draw(Graphics2D g2, int screenWidth, int screenHeight, int tileSize) {
        for (int col = 0; col < maxWorldCol; col++) {
            for (int row = 0; row < maxWorldRow; row++) {
                int tileIndex = mapTileNum[col][row];
                BufferedImage image = tile[tileIndex].image;
                int x = col * tileSize;
                int y = row * tileSize;
                if (x < screenWidth && y < screenHeight) {
                    g2.drawImage(image, x, y, tileSize, tileSize, null);
                }
            }
        }
    }
}

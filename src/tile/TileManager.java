/**
 * Ok so TileManager is a class that displays the tiles
 * to recap GamePanel is where this is called.
 * remeber not to put layers above in the gamePanel
 * also all tiles are 48px so when making the screen keep in mind 
 */

package tile;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile [] tile;
	public int mapTileNum[][]; //array 
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[50]; // this means im creating 10 kinds of tiles ie grass water wall can be changed 
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //this is the array that reads .txt file 
		getTileImage();
		loadMap("/maps/woldMapWithDamage.txt");
		
	}
	
	public void getTileImage() {
		
			//instanciate the tile array
			//calls the setup method below 
			//passes the index of the array passes the name of the tile and passes the collision bool
		
			setup(0, "grass00", false); //grass
			setup(1, "grass00", false); //wall
			setup(2, "grass00", false); //water
			setup(3, "grass00", false); //earth
			setup(4, "grass00", false); //tree
			setup(5, "grass00", false); //sand
			setup(6, "grass00", false); //sand
			setup(7, "grass00", false); //sand
			setup(8, "grass00", false); //sand
			setup(9, "grass00", false); //sand
			//actual tiles
			setup(10, "grass00", false); // all above are actually grass but dont use them 
			setup(11, "grass01", false); //grass
			//water
			setup(12, "water00", true); //wall
			setup(13, "water01", true); //water
			setup(14, "water02", true); //earth
			setup(15, "water03", true); //tree
			setup(16, "water04", true); //sand
			setup(17, "water05", true); //sand
			setup(18, "water06", true); //sand
			setup(19, "water07", true); //sand
			setup(20, "water08", true); //sand
			setup(21, "water09", true);
			setup(22, "water10", true); //sand
			setup(23, "water11", true);
			setup(24, "water12", true); //sand
			setup(25, "water13", true);
			//road
			setup(26, "road00", false); 
			setup(27, "road01", false); 
			setup(28, "road02", false);
			setup(29, "road03", false); 
			setup(30, "road04", false); 
			setup(31, "road05", false); 
			setup(32, "road06", false); 
			setup(33, "road07", false); 
			setup(34, "road08", false); 
			setup(35, "road09", false); 
			setup(36, "road10", false); 
			setup(37, "road11", false); 
			setup(38, "road12", false); 
			//earth
			setup(39, "earth", false); //earth
			setup(40, "wall", true); //wall
			setup(41, "tree", true); //tree
			
			
			
			
			
			
		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName +".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow ) {
				String line = br.readLine(); //reads a line of text from the .txt 
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" "); //split string at space and put in array
					
					int num = Integer.parseInt(numbers[col]); 
					
					mapTileNum[col][row]= num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e){
			
		}
	}
	//implementing a camera into this method so the game loads the character and it seems like the world moves 
	// three things we want to knwo for this method 1. tile image, 2. where do draw x, where to draw y
	//heres should be my final version of this unless i edit this text chaged it to render only what the player
	//can see because my computer becomes a jet and feels like its going to explode 
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow =0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			//first we check the tiles of world x, then we need to know where to draw it 
			// worldx is pos on map screen x is where on map we need to draw the tile 
			//so if player is at worldx&worldy:500 then we draw 500,500 thats why we sub here 
			// adding the gp.player makes it so the player is not displayed at the center this offsets it and disply in middle of the screen
			
			//this creates an imaginary boundray wich the screen will display and it is expanded a little to
			//avoid blackscreen overlaying 
			if(worldX+ gp.tileSize > gp.player.worldX -gp.player.screenX &&
					worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize< gp.player.worldY +gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null); //tile[ileNum].image is the idex of above func
				
			}
			
			worldCol++; //increase col by one 
		
			if(worldCol == gp.maxWorldCol) { //if it hits the max for col which is 16, reset col&x then increase y
				worldCol = 0; 
				
				worldRow++; //moves to next row 
				
				
			}
			
		}
	
	}
}


/**  kinda recycle bin for code:
 * 	heres what i was doing before I realized there was a better way for the draw method  
 *
 * //row order decending increases row and in rows descending increases columns 
	// ive got a 5x5 grid to start 240 should be max x
	//row one 
	g2.drawImage(tile[1].image, 0,0, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 48,0, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 96,0, gp.tileSize ,gp.tileSize, null); // must increase by 48px
	g2.drawImage(tile[1].image, 144,0, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 192,0, gp.tileSize ,gp.tileSize, null); 
	
	//row two 
	g2.drawImage(tile[1].image, 0,48, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 48,48, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 96,48, gp.tileSize ,gp.tileSize, null); // must increase by 48px
	g2.drawImage(tile[0].image, 144,48, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 192,48, gp.tileSize ,gp.tileSize, null); 
	
	//row three 
	g2.drawImage(tile[1].image, 0,96, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 48,96, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 96,96, gp.tileSize ,gp.tileSize, null); // must increase by 48px
	g2.drawImage(tile[0].image, 144,96, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 192,96, gp.tileSize ,gp.tileSize, null); 
	
	//row four 
	g2.drawImage(tile[1].image, 0,144, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 48,144, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[0].image, 96,144, gp.tileSize ,gp.tileSize, null); // must increase by 48px
	g2.drawImage(tile[0].image, 144,144, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 192,144, gp.tileSize ,gp.tileSize, null); 
	
	//row five 
	g2.drawImage(tile[1].image, 0,192, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[2].image, 48,192, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[2].image, 96,192, gp.tileSize ,gp.tileSize, null); // must increase by 48px
	g2.drawImage(tile[2].image, 144,192, gp.tileSize ,gp.tileSize, null); 
	g2.drawImage(tile[1].image, 192,192, gp.tileSize ,gp.tileSize, null); 
	
	******************************************************************
	*****************************************************************
	*this code below will print a screen of grass tiles but not exactly what i want im putting it down here 
	*incase i need something simple for later.
	*
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row =0;
		int x =0;
		int y =0;
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null); 
			col++; //increase col by one 
			x += gp.tileSize; // add a tile size to the x value
			if(col == gp.maxScreenCol) { //if it hits the max for col which is 16, reset col&x then increase y
				col = 0; 
				x =0;
				row++;
				y += gp.tileSize; 
				
			}
			
		}
	
	
	*/
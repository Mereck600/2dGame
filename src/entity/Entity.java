/**
 * This is the parent class for the Player Class and any other character classes

 * entity class : stores variables that will be used in player, monster and npc classes
 * Abstract
 */

package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	public int worldX, worldY; //world x and y 
	
	// Posn worldCtr;
	// Posn[] dirs = new Posn[] { new Posn(0, 0), new Posn(0, -1), new Posn(0 1), new Posn(-1, 0), new Posn(1, 0) };
	
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;//describes an image with an accessible buffer of image data. (used to store img)
	public String direction = "down";    // make this an int --- to correspond to Direction values
	
	public int spriteCounter =0;
	public int spriteNum =1;
	public Rectangle solidArea = new Rectangle(0,0, 48,48);  //this will be hit makers
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialouges[] = new String[20];
	int dialougeIndex = 0;
	
	public BufferedImage image, image2, image3 ;
	public String name;
	public boolean collision = false;
	
	//Character status
	public int maxLife;  //shared by all entites
	public int life;
	
	
	
	public Entity (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	//Method for allowing npcs to speak
	//moved it from the npc so you simply need to make sure npcs are extending entity
	public void speak() {
		if(dialouges[dialougeIndex]==null) {
			dialougeIndex =0;
		}
		gp.ui.currentDialouge = dialouges[dialougeIndex];
		dialougeIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
		
	}
	public void update() {
		
		setAction(); //subclass takes priority
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		//if false npc can move
		if(collisionOn == false) {
			switch(direction) {
			case"up":
				worldY -= speed; //in java the uppeer left is 0,0 and x increase to right and y+ as go down
				break;
			case"down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;

			}
		}

		spriteCounter++;
		if(spriteCounter > 13) { //possibly make this lower 10toofast 12a little fast 15maybe slow
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum =1;				
			}
			spriteCounter =0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		BufferedImage image = null;

		
		if(worldX+ gp.tileSize > gp.player.worldX -gp.player.screenX &&
				worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize< gp.player.worldY +gp.player.screenY) {
			
			// image = imageArray[ spriteNum ] [direction]
			//image arr in the npc and in player 
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if(spriteNum ==1 ) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;			
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); //tile[ileNum].image is the idex of above func
			
		}
		
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
	
}

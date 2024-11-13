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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	public int worldX, worldY; //world x and y 
	
	// Posn worldCtr;
	// Posn[] dirs = new Posn[] { new Posn(0, 0), new Posn(0, -1), new Posn(0 1), new Posn(-1, 0), new Posn(1, 0) };
	
	// SORT VARIABLES LATER SO IT DOESN'T LOOK LIKE POS
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;//describes an image with an accessible buffer of image data. (used to store img)
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public String direction = "down";    // make this an int --- to correspond to Direction values
	
	public int spriteCounter =0;
	public int spriteNum =1;
	public Rectangle solidArea = new Rectangle(0,0, 48,48);  //this will be hit makers
	public Rectangle attackArea = new Rectangle(0,0, 0,0); //entities attack area and this can depend on attack or weapon will be overridden
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public boolean invincible = false; 
	public int invincibleCounter = 0;
	String dialouges[] = new String[20];
	int dialougeIndex = 0;
	
	public BufferedImage image, image2, image3 ;
	public String name;
	public boolean collision = false;
	public int type; //The type of the entity 0=player 1=npc 2=monster
	boolean hpBarOn =false;
	int hpBarCounter = 0;
	
	//Character status
	public int maxLife;  //shared by all entites
	public int life;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying =false;
	int dyingCounter =0;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentSheild;
	
	//Item atributes
	public int attackValue;
	public int defenseValue;
	
	
	
	public Entity (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	public void damgaeReaction() {} //override in classes
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
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type ==2 && contactPlayer==true) {
			if(gp.player.invincible == false) {
				//we can give damage
				gp.playSE(6);
				
				int damage = attack -gp.player.defense;
				if(damage<0 ) {
					damage = 0;
				}
				
				
				gp.player.life -=damage;
				gp.player.invincible= true;
				
			}
		}
		
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
		//this needs to be outside of key if statement
				if(invincible == true) {
					invincibleCounter++;
					if(invincibleCounter > 35) {  // change this if we need to make this shorter for gameplay
						invincible =false;
						invincibleCounter =0;
					}
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
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				break;
			case "right":
				if(spriteNum ==1 ) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				break;			
			}
			
			//monster HPBar
			if(type == 2 && hpBarOn ==true) {
				double oneScale = (double) gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-15,gp.tileSize+2,12);
				g2.setColor(new Color(225,0,30));
				g2.fillRect(screenX,screenY -15,(int)hpBarValue,10); //cast to change to int
				hpBarCounter++;
				
				if(hpBarCounter > 600) { //ten seconds 600 frames
					hpBarCounter =0;
					hpBarOn =false;
				}
				
			}
			 			
			
			
			//stuff when player or monster is hit
			if(invincible == true) {
				hpBarOn =true; 
				hpBarCounter =0;
				changeAlpha(g2,0.4f);
				
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); //tile[ileNum].image is the idex of above func
			changeAlpha(g2,1f);  // reset
		}
		
	}
	public void dyingAnimation(Graphics2D g2) {  //create a blinking death effect by changing the alpha value
		dyingCounter++;
		int i =5; //change this if we want to adjust timing
		
		if(dyingCounter <= i) {changeAlpha(g2,0);}
		if(dyingCounter <i && dyingCounter <=i*2) {changeAlpha(g2,1f);}//System.out.println("working 1"); 
		if(dyingCounter >i*2 && dyingCounter <=i*3) { changeAlpha(g2,0f);} //System.out.println("working 2")
		if(dyingCounter >i*3 && dyingCounter <=i*4) {changeAlpha(g2,1f);}//System.out.println("working 3");
		if(dyingCounter >i*4 && dyingCounter <=i*5) {changeAlpha(g2,0f);}//System.out.println("working 4");
		if(dyingCounter >i*5 && dyingCounter <=i*6) {changeAlpha(g2,1f);}//System.out.println("working 5");
		if(dyingCounter >i*6 && dyingCounter <=i*7) {changeAlpha(g2,0f);}//System.out.println("working 6");
		if(dyingCounter >i*7 && dyingCounter <=i*8) {changeAlpha(g2,1f);}//System.out.println("working 7");
		if(dyingCounter >i*8) {
			dying =false;
			alive=false;
			//System.out.println("working end");
		}
		
	}
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  alphaValue));		
	}
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaledImage(image, width, height);
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
	
}

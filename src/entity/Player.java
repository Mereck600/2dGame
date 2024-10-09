package entity;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import java.awt.image.BufferedImage;

public class Player extends Entity {

	
	KeyHandler keyH;
	public final int screenX;   //the background scrolls as the player moves 
	public final int screenY;  //these dont change
	//public int hasKey =0; //can change this so something else but for now leaving it as key bc of interactions w/ door.
	int standCounter =1;
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyH = keyH;

		screenX =gp.screenWidth/2 -(gp.tileSize/2); //returns halfway point of the screen
		screenY =gp.screenHeight/2 - (gp.tileSize/2); 
		//colision area for the player character 
		solidArea = new Rectangle(); // x,y,width,height
		solidArea.x = 8; //this is where it starts 
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x; //we want to record the default values 
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32; //this will be smaller than the character
		solidArea.height = 32;


		setDefaultValues();
		getPlayerImage();

	}
	//sets the default values of the player
	public void setDefaultValues() {
		worldX = gp.tileSize *23; //these are screen position they are players pos on world map
		worldY = gp.tileSize *21; 
		speed = 4; 
		direction = "down"; 
	}
	//gets the pictues needed for the player model. 
	public void getPlayerImage() {
		//similar to tile we are scaling the image outside of the main draw method to fix the rendering time
		//only passing the name into the setup though because not indexing the player images
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
		
		
	}
	
	public void update() {
		//this if is what chagnges the player character from walking animation to standing 
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			//all of these ifs handle movement of the character
			if(keyH.upPressed == true) {
				direction = "up";


			}
			else if(keyH.downPressed == true) {
				direction = "down";

			}
			else if(keyH.leftPressed == true){
				direction = "left";


			}
			else if(keyH.rightPressed == true) {
				direction = "right";

			}
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this); //pases player into checkTile method in collionChecker 

			//check Object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//update gets called 60x per sec so every frame this below is
			//called and when it hits 12 the player image will change
			//if colliosion is false playe can move
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
		else { ///makes the player stop after a set time so that that it looks more natural
			standCounter++;
			if(standCounter ==20) {
				spriteNum = 1;
				standCounter = 0;
			}
			
		}


	}
	/**
	 * this determines what happends after a collision with an object.
	 * 
	 */
	public void pickUpObject(int i) {

	    if(i != 999 ) { // Ensure the object is not null    && gp.obj[i] != null
	       
	    }
	}
	
	public void interactNPC(int i) {
		if(i != 999 ) { // Ensure the object is not null    && gp.obj[i] != null
		       System.out.println("You hit npc");
	    }
	}

	public void draw(Graphics2D g2) {
		//test object :
		//g2.setColor(Color.white); // setColor(color c) sets a color for drawing objects		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize); 
		//*******************************************//

		//here is the method that draws the player image  should be self explainitory
		//switch works the same as an if statement for the most part. 
		BufferedImage image = null;

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
		g2.drawImage(image,  screenX,  screenY, null); 
		//image oobserver is the null val. the other stuff draws that image with the size gp
	}
}





/*
 *  String objectName = gp.obj[i].name;

	        switch(objectName) {
	            case "Key":
	            	gp.playSE(1); //this calls playSE from gp and sets it to the key effect
	                hasKey++;
	                gp.obj[i] = null; // Remove the object after using its name
	                gp.ui.showMessage("You have picked up a key!");
	                
	               
	                
	                // System.out.println("Key: " + hasKey);
	                break;

	            case "Door":
	                if(hasKey > 0) {
	                	gp.playSE(3); //this calls playSE from gp and sets it to the door effect
	                    gp.obj[i] = null; // Remove the door
	                    hasKey--; // Reduce the number of keys
	                    gp.ui.showMessage("You opened a door!");
	                }
	                else {
	                	gp.ui.showMessage("You need a key to open the door");
	                }
	                //System.out.println("Key: " + hasKey);
	                break;
	            case "Boots": //makes the player faster
	            	gp.playSE(2); //this calls playSE from gp and sets it to the powerUP effect
	            	speed += 2;
	            	gp.obj[i] = null;
	            	gp.ui.showMessage("Speed Up!");
	            	
	            
	            	break;
	            case "Chest":
	            	gp.ui.gameFinished =true;
	            	gp.stopMusic();
	            	gp.playSE(4);
	            	
	                break;
        	   }
 */

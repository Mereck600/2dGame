package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Sheild_Wood;
import object.OBJ_Sword_Normal;

import java.awt.image.BufferedImage;



public class Player extends Entity {


	KeyHandler keyH;
	public final int screenX;   //the background scrolls as the player moves 
	public final int screenY;  //these dont change
	//public int hasKey =0; //can change this so something else but for now leaving it as key bc of interactions w/ door.
	int standCounter =1;
	public boolean attackCancelled = false;
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

		attackArea.width = 36; //temp value to be changed based on the weapon the player has 
		attackArea.height = 36; //shoter than tile size



		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();

	}
	//sets the default values of the player
	public void setDefaultValues() {
		worldX = gp.tileSize *23; //these are screen position they are players pos on world map
		worldY = gp.tileSize *21; 
		speed = 4; 
		direction = "down"; 

		//player status
		maxLife = 6;  //means 3 heart 2lives is one heart
		life = maxLife;
		level =1;
		strength =1;  //the more strenght he has the more damage player give
		dexterity =1; // the more dexterity player has the less damage they recieve
		exp=0;
		nextLevelExp = 5;
		coin = 0;  //starts broke asf
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentSheild = new OBJ_Sheild_Wood(gp);
		attack = getAttack();  //total attack value decieded by strenght and weapon
		defense = getDefense();  //decided by dexterity and sheild
		
		
	}
	/*
	 * method to get the attack value of the player based on weapon and character
	 */
	public int getAttack() {
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentSheild.defenseValue;
	}
	
	//gets the pictues needed for the player model. 
	public void getPlayerImage() {
		//similar to tile we are scaling the image outside of the main draw method to fix the rendering time
		//only passing the name into the setup though because not indexing the player images
		up1 = setup("/player/boy_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/player/boy_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/player/boy_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/player/boy_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/player/boy_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/player/boy_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/player/boy_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/player/boy_right_2",gp.tileSize,gp.tileSize);


	}

	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize, 2*gp.tileSize);
		attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize, 2*gp.tileSize);
		attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize,2* gp.tileSize);
		attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize,2*gp.tileSize);
		attackLeft1 = setup("/player/boy_attack_left_1",2 *gp.tileSize,gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2",2* gp.tileSize,gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1",2 * gp.tileSize,gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2",2* gp.tileSize,gp.tileSize);

	}

	public void update() {

		if(attacking == true) {
			attacking();

		}

		//this if is what chagnges the player character from walking animation to standing 
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
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

			//Check Monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);


			//Check Event
			gp.eHandler.checkEvent();

			//update gets called 60x per sec so every frame this below is
			//called and when it hits 12 the player image will change
			//if colliosion is false playe can move
			if(collisionOn == false && keyH.enterPressed == false) {

				//	worldCtr = worldCtr.translate( dirs[direction].scale(speed) );


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
			
			if(keyH.enterPressed == true && attackCancelled==false) {
				gp.playSE(7);
				attacking =true;
				spriteCounter =0;
			}
			
			attackCancelled =false;

			gp.keyH.enterPressed =false;


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

		//this needs to be outside of key if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible =false;
				invincibleCounter =0;
			}
		}


	}
	/**
	 * this determines what happends after a collision with an object.
	 * 
	 */

	public void attacking() {
		spriteCounter++;
		if (spriteCounter <= 5) {
			spriteNum = 1;
		} else if (spriteCounter <= 25) {
			spriteNum = 2;
			//store x,y and solid area based on the weapon location
			//Save the current stuff
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			//adjust the playeres world x/y for the attack area
			switch(direction) {
			case "up": worldY -=attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			//attack area brecomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height; //cahnge players solid area
			//check monster colliosion with new world x and y and solid area
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			//After checking collision restore original settings
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;



		} else { // Resetting at the end of the animation
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	/**
	 * Method to pick upp objects currently removed because I didn't want it atm 
	 * @param i
	 */
	public void pickUpObject(int i) {

		if(i != 999 ) { // Ensure the object is not null    && gp.obj[i] != null

		}
	}

	public void interactNPC(int i) {
		if(gp.keyH.enterPressed == true) {
			if(i != 999 ) { // Ensure the object is not null    && gp.obj[i] != null aka if the player is interacting with npc 
				attackCancelled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			

		}
		//gp.keyH.enterPressed =false;     currently this is giving bugs in my eventHandler class because it auto sets to false
	}

	public void contactMonster(int i ) {
		if(i != 999) {//player touches monster
			if(invincible == false) {
				gp.playSE(6);
				int damage = gp.monster[i].attack -defense;
				if(damage<0 ) {
					damage = 0;
				}
				life -= damage;
				invincible =true;
				if(life <=0) {
					alive =false;
				}
			}

		}

	}

	public void damageMonster(int i) {
		if(i!= 999) {
			//System.out.println("hit");
			if(gp.monster[i].invincible == false) {
				gp.playSE(5);
				
				int damage = attack -gp.monster[i].defense;
				if(damage<0 ) {
					damage = 0;
				}
				gp.monster[i].life -=damage;
				gp.ui.addMessage(damage +" Damage!");
				gp.monster[i].invincible=true;
				gp.monster[i].damgaeReaction();
				

				if(gp.monster[i].life<=0) {  //if monster dies set their index value to null so it is not displayed
					
					//gp.monster[i]=null;   this is to have the monster simply vanish uncomment if we need quick implementation rather than good 
					gp.monster[i].dying = true;
					gp.ui.addMessage("Killed the " +gp.monster[i].name +"!");
					exp += gp.monster[i].exp;
					gp.ui.addMessage("Gained " +gp.monster[i].exp +" EXP");
					checkLevelUp();
					gp.playSE(8);
					
					gp.gameState = gp.dialogueState;
					gp.ui.currentDialouge = "You are now level " + level+ "!\n" + "You feel stronger!"; 
					

				}
			}
		}
		else {System.out.println("miss");}
	}

	public void checkLevelUp() {
		if(exp>= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife +=2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			
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
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			if(attacking ==true) {
				tempScreenY = screenY -gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}

			break;

		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;} }
			if(attacking ==true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "left":

			if(attacking ==false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;} }
			if(attacking ==true) {
				tempScreenX = screenX -gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum ==1 ) {image = right1;}
				if(spriteNum == 2) {image = right2;} }
			if(attacking ==true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			break;			
		}

		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));  //seting the opacity level of the draw method so that it is 70% 
		}
		g2.drawImage(image,  tempScreenX,  tempScreenY, null); 

		//reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f)); 

		//image oobserver is the null val. the other stuff draws that image with the size gp

		//DEbug 	
		//		g2.setFont(new Font("Arial", Font.PLAIN,26));
		//		g2.setColor(Color.white);
		//		g2.drawString("Invincible: " +invincibleCounter,10,400);

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

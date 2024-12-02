package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_OldMan extends Entity{
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getImage();
		setDialouge() ;
	}

	public void getImage() {
		//similar to tile we are scaling the image outside of the main draw method to fix the rendering time
		//only passing the name into the setup though because not indexing the player images
		up1 = setup("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/oldman_right_2",gp.tileSize,gp.tileSize);


	}
	
	public void setDialouge() {
		dialouges[0] = "Hello, lad.";
		dialouges[1] = "So You've come to this island \n to find the treasure?";
		dialouges[2] = "I used to be a Great wizard... \n but now i'm a bit too old";
		dialouges[3] = "Gods I was strong then.";
		dialouges[4] = "If you are hurt walk up the path \n and press enter on the water";
		
		
		
	}
	
	/**this will almost work like an ai will be different for all npcs 
	 * 
	 */
	public void setAction() {

		actionLockCounter ++;


		if(actionLockCounter == 120) {
			Random random = new Random();

			int i = random.nextInt(100) +1; //1->100 pick a number 

			if(i <= 25 ) {
				direction = "up";

			}
			if(i > 25 && i <= 50 ) {
				direction = "down";
			}
			if(i > 50 && i<=75) {
				direction = "left";
			}
			if(i>75 && i <=100) {
				direction = "right";
				}
			actionLockCounter = 0;
		}
		

	}
	/**
	 * Makes the npc talk 
	 * through text but could add a sound effect later
	 */
	public void speak() {
		//character specific speech
		super.speak();
		gp.playSE(10);  //-> example of a sound effect added in here
		
	}


}

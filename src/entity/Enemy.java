package entity;

import main.GamePanel;

public class Enemy extends Entity{

	public Enemy(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getImage();
		setDialouge() ;
	}
	public void setDialouge() {
		dialouges[0] = "hrrgh.";
		dialouges[1] = "I'll get you back for that\n ";
		dialouges[2] = "Let's get him \n ";
		dialouges[3] = "aaaaagh";
		dialouges[4] = "I hope he doesn't take our loot";
		
		
	}
	
	public void getImage() {
		//I will switch this to the enemy pixel art once I get that made.
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
}
}


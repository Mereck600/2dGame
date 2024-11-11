package entity;

import main.GamePanel;

public class Enemy extends Entity{

	public Enemy(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getImage();
		setDialouge() ;
		takeDamage(); 
		dealDamage();
	}
	private void dealDamage() {
		//When player is near enough for the enemy to swing it will attack and deal damage
		
	}
	private void takeDamage() {
		// when the player hits the enemy it will deal damage to the enemy taking away some of its health
		
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
		up1 = setup("/enemy/goblin_up1");
		up2 = setup("/enemy/goblin_up2");
		down1 = setup("/enemy/goblin_down1");
		down2 = setup("/enemy/goblin_down2");
		left1 = setup("/enemy/goblin_left1");
		left2 = setup("/enemy/goblin_left2");
		right1 = setup("/enemy/goblin_right1");
		right2 = setup("/enemy/goblin_right2");
}
	
}


package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity{
	GamePanel gp;
	
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		// TODO Auto-generated constructor stub
		name = "Green Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		type = 2;
		attack = 5;
		defense =0;
		exp = 2;
		
		
		
		solidArea.x =3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		
	}
	
	public void getImage() {
		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

		
//		
//		up1 = 	setup("/res/monster/greemslime_down_1");
//		up2 = 	setup("/res/monster/greenslime_down_2");
//		down1 = setup("/res/monster/greenslime_down_1");
//		down2 = setup("/res/monster/greenslime_down_2");
//		left1 = setup("/res/monster/greenslime_down_1");
//		left2 = setup("/res/monster/greenslime_down_2");
//		right1 = setup("/res/monster/greenslime_down_1");
//		right2 = setup("/res/monster/greenslime_down_2");
	}
	/**
	 * Set action kind of works like the npc movement 
	 * Really just play with the direction of the slime
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
	 * This is what happens when it is hit
	 * The direction also changes
	 */
	public void damageReaction() {
		actionLockCounter=0;
		direction = gp.player.direction;	
	}

}

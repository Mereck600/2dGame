package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Goblin  extends Entity{
	GamePanel gp;
	
	public MON_Goblin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		// TODO Auto-generated constructor stub
		name = "Goblin";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		type = 2;
		attack = 7;
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
		up1 = setup("/monster/goblin_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/goblin_up2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/goblin_down1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/goblin_down2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/goblin_left1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/goblin_left2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/goblin_right1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/goblin_right2", gp.tileSize, gp.tileSize);

		
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
	
	public void damageReaction() {
		actionLockCounter=0;
		direction = gp.player.direction;	
	}

}

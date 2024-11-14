package object;

import entity.Entity;
import main.GamePanel;

/**
 * Class for the sheild it has a name and an image with certain attributes	
 */
public class OBJ_Sheild_Wood extends Entity {

	public OBJ_Sheild_Wood(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "wood Sheild";
		down1 = setup("/objects/shield_wood",gp.tileSize,gp.tileSize);
		defenseValue =1;
		
	}

}

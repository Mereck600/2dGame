package object;

import entity.Entity;
import main.GamePanel;

/**
 * class for the sword object it has a name and certain attributes
 */
public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "Normal Sword";
		down1 = setup("/objects/sword_normal",gp.tileSize,gp.tileSize);
		attackValue =1;
		
	}

}

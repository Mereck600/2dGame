package object;

import java.io.IOException;

import main.GamePanel;
import javax.imageio.ImageIO;

import entity.Entity;


	
public class OBJ_Boots extends Entity {
	 
	public OBJ_Boots(GamePanel gp) {
		super(gp);

		name="Boots";
		down1 = setup("/objects/boots");
		
		
	
	}

}

/**
 * Displays the objects
 */
package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	/**
	 * instanciate default objects and place them on map using object arry from game pannel
	 */
	public void setObject() {
		
		
		
		gp.obj[1] = new OBJ_Door(gp); //real door
		gp.obj[1].worldX = 10 *gp.tileSize;
		gp.obj[1].worldY = 12  *gp.tileSize;
		
		
		
				
		
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize *21;
		gp.npc[0].worldY = gp.tileSize *21;
		
		
		
		
	}
	
	public void setMonster() {
		int i =0; 
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = 23 * gp.tileSize;
		gp.monster[i].worldY = 36 * gp.tileSize;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = 23 *  gp.tileSize;
		gp.monster[i].worldY = 37 * gp.tileSize;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = 24 *  gp.tileSize;
		gp.monster[i].worldY = 37 * gp.tileSize;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = 34 *  gp.tileSize;
		gp.monster[i].worldY = 42 * gp.tileSize;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = 38 *  gp.tileSize;
		gp.monster[i].worldY = 42 * gp.tileSize;
		i++;
		
	}
	
	
	
	
}




/*
 * gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 23 *gp.tileSize; //this is based on tile map column	
		gp.obj[0].worldY = 7 *gp.tileSize; //this is based on tile map row this is where I want specific Object
		
		gp.obj[1] = new OBJ_Key(gp); //second key
		gp.obj[1].worldX = 23 *gp.tileSize;
		gp.obj[1].worldY = 40 *gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp); //second key
		gp.obj[2].worldX = 38 *gp.tileSize;
		gp.obj[2].worldY = 8 *gp.tileSize;
		
		
		gp.obj[3] = new OBJ_Door(gp); //first door
		gp.obj[3].worldX = 10 *gp.tileSize;
		gp.obj[3].worldY = 11  *gp.tileSize;
		
		
		gp.obj[4] = new OBJ_Door(gp); //second door
		gp.obj[4].worldX = 8 *gp.tileSize;
		gp.obj[4].worldY = 28 *gp.tileSize;
		
		
		gp.obj[5] = new OBJ_Door(gp); //third door
		gp.obj[5].worldX = 12 *gp.tileSize;
		gp.obj[5].worldY = 22 *gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest(gp); //chest 
		gp.obj[6].worldX = 10 *gp.tileSize;
		gp.obj[6].worldY = 7 *gp.tileSize;
		
		gp.obj[7] = new OBJ_Boots(gp); //chest 
		gp.obj[7].worldX = 37 *gp.tileSize;
		gp.obj[7].worldY = 42 *gp.tileSize;
		
 */

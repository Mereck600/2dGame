package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	//constructor
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	//method: ok so we are looking for the hitbox here in this method 
	public void checkTile(Entity entity) { //entity not player bc it is not just for player
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x +entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		//from this we can find their col and row numbers

		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;

		int tileNum1, tileNum2; // only 2 nums because we only really care about left and right shoulder

		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision ==true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision ==true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision ==true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision ==true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;


		}




	}
	/**
	 * 
	 *recieve entity player or something else and check if entity is player or not 
	 *return integer
	 *check if player hits object and if yes return index and process reaction
	 *Uses different method than the tile checker because there are more tiles thna objects and 
	 *it would be too inefficent
	 */
	public int checkObject(Entity entity, boolean player) {
		 int index = 999;
		//scan obj array 
		for(int i =0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//get entity's solid are pos
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				//get objects solid area posn
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				switch (entity.direction) {
			    case "up":
			        entity.solidArea.y -= entity.speed;
			        
			        break; 
			    case "down":
			        entity.solidArea.y += entity.speed;
			        
			        break; 
			    case "left":
			        entity.solidArea.x -= entity.speed;
			        
			        break; 
			    case "right":
			        entity.solidArea.x += entity.speed;
			        
			        break; 
			}
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
		        	 if(gp.obj[i].collision == true) { 
						  entity.collisionOn = true; }
					  if(player ==  true) { 
						  index =i; }
		          //  System.out.println("down collision");
		        }
				//Reset the values so that the numbers don't keep adding 
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
			}

		}

		return index; 

	} 
	//npc or something else collision
	public int checkEntity(Entity entity, Entity[] target) {
		 int index = 999;
			//scan obj array 
			for(int i =0; i < target.length; i++) {
				if(target[i] != null) {
					//get entity's solid are pos
					entity.solidArea.x = entity.worldX + entity.solidArea.x;
					entity.solidArea.y = entity.worldY + entity.solidArea.y;

					//get objects solid area posn
					target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
					target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

					switch (entity.direction) {
				    case "up":entity.solidArea.y -= entity.speed; break; 
				    case "down": entity.solidArea.y += entity.speed; break; 
				    case "left": entity.solidArea.x -= entity.speed; break; 
				    case "right":entity.solidArea.x += entity.speed;break; 
					}
					if(entity.solidArea.intersects(target[i].solidArea)) {
						if(target[i] != entity) {
							entity.collisionOn = true; 
							 index =i; 
						}
						
					}
					//Reset the values so that the numbers don't keep adding 
					entity.solidArea.x = entity.solidAreaDefaultX;
					entity.solidArea.y = entity.solidAreaDefaultY;
					target[i].solidArea.x = target[i].solidAreaDefaultX;
					target[i].solidArea.y = target[i].solidAreaDefaultY;
					
				}

			}

			return index; 
	}
	
	/**collision for when a player hits an npc
	 * 
	 * @param entity
	 */
	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer =false;
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		//get objects solid area posn
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

		switch (entity.direction) {
	    case "up":
	        entity.solidArea.y -= entity.speed; break; 
	    case "down":
	        entity.solidArea.y += entity.speed;break; 
	    case "left":
	        entity.solidArea.x -= entity.speed; break; 
	    case "right":
	        entity.solidArea.x += entity.speed;break; 
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
        	
			  entity.collisionOn = true; 
			  contactPlayer=true;
		  	 }
		//Reset the values so that the numbers don't keep adding 
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		return contactPlayer;
		
	}
}

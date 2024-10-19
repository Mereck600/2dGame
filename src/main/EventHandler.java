package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width =2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}
	
	/*
	 * Checks to see if a entity has hit a certain spot
	 * just making mainly goofy events for now w
	 */
	public void checkEvent() {
		if(hit(27,17,"right") == true) { damagePit(gp.dialogueState);}
		//if(hit(27,1,"right") == true) { teleport(gp.dialogueState);}
		if(hit(25,19,"any") == true) { teleport(gp.dialogueState);}
		if(hit(25,23,"any") == true) { teleport(gp.dialogueState);}
		//if(hit(27,17,"right") == true) { teleport(gp.dialogueState);}
		if(hit(23,12,"any") == true) { healingPool(gp.dialogueState); }  //call hit at point on grid then set the method you want to run
		
		}
	/*
	 * method to check the event colliosn works similar to object collision
	 */
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit =false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;
		//check to seee if player collides
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;  //check the players direction 
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		//reset
		
		return hit;
		
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialouge = "You have fallen into a pit";
		gp.player.life -=1;
		
	}
	
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialouge = "You have drank the water. \n You have now healed.";
			gp.player.life = gp.player.maxLife;
								
		}
		// gp.keyH.enterPressed = false;   giving errors so i rest in player class
	}
	
	public void teleport(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialouge = "WIZARD TIME";
		gp.player.worldX = gp.tileSize*37;
		gp.player.worldY = gp.tileSize*10;
	}
}

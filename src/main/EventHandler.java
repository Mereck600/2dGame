package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	//Rectangle eventRect;
	//int eventRectDefaultX, eventRectDefaultY;
	EventRect eventRect[][];
	int prexiousEventX, previousEventY;
	boolean canTouchEvent = true; // makes it so event can only happen if the player moves away from the event
	private int previousEventX;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		int col =0;
		int row =0;
				while(col <gp.maxWorldCol && row < gp.maxWorldRow) {

					eventRect[col][row] = new EventRect();
					eventRect[col][row].x = 23;
					eventRect[col][row].y = 23;
					eventRect[col][row].width =2;
					eventRect[col][row].height = 2;
					eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
					eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
					
					col++;
					if(col == gp.maxWorldCol) {
						col = 0;
						row++;
					}
					
				}
		
		
		
	}
	
	/*
	 * Checks to see if a entity has hit a certain spot
	 * just making mainly goofy events for now w
	 */
	public void checkEvent() {
		//Check to see if the player is more than one tile away form the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if(distance > gp.tileSize){
			canTouchEvent = true;
			
		}
		//put events we want here **Braxton if you want to make an even that you dont see below use same style as i have here for methods **
		//look down at the teleport or damagapit for inspo. 
		if(canTouchEvent == true) { 
			
			//Events
			//call hit at point on grid w/ dir then set the method you want to run
			if(hit(27,17,"right") == true) { damagePit(27,17,gp.dialogueState);}
			if(hit(25,19,"any") == true) { teleport(gp.dialogueState);}
			if(hit(25,23,"any") == true) { teleport(gp.dialogueState);}
			if(hit(23,12,"any") == true) { healingPool(gp.dialogueState); }  
			
		}
		
		}
	/*
	 * method to check the event colliosn works similar to object collision
	 */
	public boolean hit(int col, int row, String reqDirection) {
		boolean hit =false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		//check to seee if player collides
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;  //check the players direction 
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
				
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		//reset
		
		return hit;
		
	}
	
	public void damagePit(int col, int row,int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialouge = "You have fallen into a pit";
		gp.player.life -=1;
		
		//eventRect[col][row].eventDone =true;
		canTouchEvent = false;
		
	}
	/*
	 * Healing will rest health but also rest the monsters on the map
	 */
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCancelled =true;
			gp.playSE(2);
			gp.ui.currentDialouge = "You have drank the water. \n You have now healed.";
			gp.player.life = gp.player.maxLife;
			gp.aSetter.setMonster(); //reset the monsters 
								
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

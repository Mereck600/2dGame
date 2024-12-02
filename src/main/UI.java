package main;

import java.awt.Graphics2D;

import entity.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import object.OBJ_Key;
import object.OBJ_Heart;
//import object.SuperObject;


public class UI {
	//comented out code was code for the previous ui that had the treasure game
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, fontType_80B;
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter =0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	
	public boolean gameFinished = false;
	public String currentDialouge = "";
	
	public int commandNum = 0;
	
	public int titleScreenState = 0;  //uses the number for diff screens 0 is standard
	//double playTime;
	//DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	




	public UI(GamePanel gp) {
		this.gp = gp;
		//maruMonica = new Font("Courier Regular", Font.PLAIN, 40);
		fontType_80B = new Font("Courier Regular", Font.BOLD, 80);
		
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create HUD object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half= heart.image2;
		heart_blank = heart.image3;
		
		
		
		
		// font style size don'ttcreate a new instance in game loop just ref	
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
	}
	/**
	 * this method recieves the text based on the players interactions (pickUp)
	 * and then allows it to displays it on the screen
	 * @param text
	 */
	public void addMessage(String text) {
//		message = text;
//		messageOn = true;
		
		message.add(text);
		messageCounter.add(0);
		
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//playstate
		if(gp.gameState == gp.playState) {
			//play stuff
			drawPlayerLife();
			drawMessage();
		}
		
		//pause
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//dialouge 
		if(gp.gameState == gp.dialogueState) {
			drawDialougeScreen();
			 
		}
		//characterState
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
		}
		if(gp.gameState == gp.saveState) {
			drawSaveScreen();
		}
		if(gp.gameState == gp.dieState) {
			drawDieScreen();
		}
		if(gp.gameState == gp.loadState) {
			drawLoadScreen();
		}
		
	}
	
	/**
	 * Here is the method for the draw load screen but all of the othem methods below are pretty similar in concept.
	 * basically just drawing rectangles and strings its not very complicated there are helper methods below
	 * the rectangles and the strigns that are drawn are dependent on a few things but besides that straight foward
	 * Also I am feeling lazy so the save an load are pretty much the same 
	 */
	public void drawLoadScreen() {

		//create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		String text ="";
		
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX = frameX +20;
		int textY = frameY +gp.tileSize;
		int tailX = (frameX + frameWidth) -20;
		int drawX;
		
		final int lineHeight =55;
		
		text = "Select Save file";
		g2.drawString(text, textX, textY);
		textY+= lineHeight;
		
		text = "to load";
		g2.drawString(text, textX+gp.tileSize, textY);
		
		drawSubWindow(textX-(gp.tileSize/2),textY+10,frameWidth,10);
		textY+= lineHeight;
		
		
		
		text = "Restart";
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save One";
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save Two";
		g2.drawString(text, textX, textY);
		if(commandNum == 2) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save Three";
		g2.drawString(text, textX, textY);
		if(commandNum == 3) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
	}
	
	/** 
	 * method to draw the die screen
	 * 
	 */
	public void drawDieScreen() {
		//window
				int x = gp.tileSize*2;
				int y = gp.tileSize/2;
				int width = gp.screenWidth -(gp.tileSize*4);
				int height = gp.tileSize*4;
				int drawX;
				String text;
				
				drawSubWindow(x,y,width,height);
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
				
				
				text = "You have died";
				int dieX = getXforCenteredText(text)-gp.tileSize;
				
				y += 40;
				
				g2.drawString(text , dieX, y);
				y += 40;
				
				
				
				
				text = "Load Save";
				int sX = getXforCenteredText(text) -gp.tileSize;
				if(commandNum == 0) {
					drawX = getXforAlignToRightText(text, sX);
					g2.drawString(">", drawX+2*gp.tileSize,y);  //change to draw image if we want to use a selector image
				}
				
				g2.drawString(text, sX, y);
				y += 40;
				

				text = "Back to Title";
				int tX = getXforCenteredText(text)-gp.tileSize;
				if(commandNum == 1) {
					drawX = getXforAlignToRightText(text, tX);
					g2.drawString(">", drawX+2*gp.tileSize,y);  //change to draw image if we want to use a selector image
				}
				g2.drawString(text, tX, y);
				y += 40;
			

				

				
				 //split text in the dialouge by keyword \n and draw and then increase y to displays
					
					
					
					
				
					
					
					
					
				
		
	}
	/**
	 * Method to draw the ui for the save screen
	 */
	public void drawSaveScreen() {

		//create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		String text ="";
		
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX = frameX +20;
		int textY = frameY +gp.tileSize;
		int tailX = (frameX + frameWidth) -20;
		int drawX;
		
		final int lineHeight =55;
		
		text = "Select Save";
		g2.drawString(text, textX, textY);
		textY+= lineHeight;
		
		text = "Restart";
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save One";
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save Two";
		g2.drawString(text, textX, textY);
		if(commandNum == 2) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
		text = "Save Three";
		g2.drawString(text, textX, textY);
		if(commandNum == 3) {
			drawX = getXforAlignToRightText(text, tailX);
			g2.drawString("<", drawX+gp.tileSize,textY);  //change to draw image if we want to use a selector image
		}
		textY+= lineHeight;
		
	}
	
	/**
	 * Method to draw the players health
	 */
	public void drawPlayerLife() {
		//gp.player.life = 2; test to make sure its displaying correctly	
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		
		int i = 0;
		//draw Max Life
		while(i < gp.player.maxLife/2){
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		//reset
		 x = gp.tileSize/2;
		 y = gp.tileSize/2;
		 i = 0;
		 
		 //draw current life
		 while(i < gp.player.life) {
			 g2.drawImage(heart_half, x, y, null);
			 i++;
			 if(i < gp.player.life) {
				 g2.drawImage(heart_full, x, y, null);
				 
			 }
			 i++;
			 x += gp.tileSize;
		 }
	}
	
	/**
	 * THis drawMessage method is more fun than the others but it still follows the draw string and recatangles but this time
	 * we get to instanciate over and arrayList full of special messages depending on what needs to be drawn for character
	 */
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		
		for(int i=0; i<message.size(); i++) { //scans message array and displays them one by one
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i)+1; //baiscly ++ nut needs weird syntax
				messageCounter.set(i,counter); //must use this syntax for arrylist thsi sets counter to array
				messageY+=50;
				
				if(messageCounter.get(i)>180) {
					message.remove(i);
					messageCounter.remove(i);
					
					
					
				}
			}
		}
		
	}
	public void drawCharacterScreen() {
		
		
		//create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX = frameX +20;
		int textY = frameY +gp.tileSize;
		final int lineHeight =35;
		
		//NAMES
		g2.drawString("Level ", textX, textY);
		textY+= lineHeight;
		g2.drawString("Life ", textX, textY);
		textY+=lineHeight;
		g2.drawString("Strength ", textX, textY);
		textY+=lineHeight;
		g2.drawString("Dexterity ", textX, textY);
		textY+=lineHeight;
		g2.drawString("Attack ", textX, textY);
		textY+=lineHeight;
		g2.drawString("EXP ", textX, textY);
		textY+=lineHeight;
		g2.drawString("Next Level ", textX, textY);
		textY+=lineHeight;
		g2.drawString("Coin ", textX, textY);
		textY+=20 +lineHeight;
		g2.drawString("Weapon ", textX, textY);
		textY+=15+lineHeight;
		g2.drawString("Shield", textX, textY);
		textY+=lineHeight;
		
		//values aligned right
		
		int tailX = (frameX + frameWidth) -30;
		
		//reset text y
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.life + "/" +gp.player.maxLife);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.strength);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.dexterity);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.attack);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.exp);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;

		value = String.valueOf(gp.player.coin);  //gets the level as a string
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value,textX,textY); 
		textY+=lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize,textY-14,null);
		textY+=gp.tileSize;
		
		g2.drawImage(gp.player.currentSheild.down1,tailX-gp.tileSize,textY-14,null);

		

		
		
		
		
			
		
		
		
	}
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			//If we want to set a different background color for the title screen could also display an image if we want
			g2.setColor(new Color (0,0,0));  // ie. 70,120,80 -greenish use any rgb im gonna leave at black
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			
			//Ttile name
			//sets position and what I want to call the name of the game
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96));
			String text = "Legends of Aetheria";
			int x = getXforCenteredText(text);
			int y = gp.tileSize *3;
			
			//Shadow for the text
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			//displays the color and the text title 
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//Character image displayed on the title 
			x = gp.screenWidth/2 -(gp.tileSize*2)/2; //should place character at center also could just use a number
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y,gp.tileSize*2, gp.tileSize*2,null);
			
			//Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			text = "New Game";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize,y);  //change to draw image if we want to use a selector image
			}
			
			text = "Load Game";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize,y);  //change to draw image if we want to use a selector image
			}
			text = "Quit";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize,y);  //change to draw image if we want to use a selector image
			}
		}
		
		else if(titleScreenState == 1) {
			
			//player Selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "select your class";
			int x =  getXforCenteredText(text);
			int y =  gp.tileSize*3;
			g2.drawString(text,x,y);
			
			text = "Fighter";
			x =  getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text,x,y);		
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Wizard";
			x =  getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);		
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Regular dude";
			x =  getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);		
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Go Back";
			x =  getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text,x,y);		
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}			
			
		}
		
		else if(titleScreenState == 2) {
			
			//player Selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Directions";
			int x =  getXforCenteredText(text);
			int y =  gp.tileSize*3;
			g2.drawString(text,x,y);
			
			text = "Press WASD to move";
			x =  getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text,x,y);		
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "K to save, L to Load";
			x =  getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);		
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Enter to interact and fight";
			x =  getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);		
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Press Enter to continue!";
			x =  getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text,x,y);		
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}			
			
		}
		
		
		 
		
	}
	
	public void drawPauseScreen(){
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "Paused";
		int x = getXforCenteredText(text);
		
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialougeScreen() {
		//window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth -(gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));

		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialouge.split("\n") ) {  //split text in the dialouge by keyword \n and draw and then increase y to displays
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width,int height) {
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		
		g2.fillRoundRect(x,y,width,height,35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
		
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX -length;
		return x;
	}


}









//old code
/**
* if(gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			text = "You found the tresure!";
			//returns the length of the text in a complicated way 
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
			 x = gp.screenWidth/2 - textLength/2;
			 y = gp.screenHeight/2 - (gp.tileSize*3);
			 g2.drawString(text, x, y);
			 
			 text = "Your time is: " + dFormat.format(playTime)+ "!";
				//returns the length of the text in a complicated way 
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
				 x = gp.screenWidth/2 - textLength/2;
				 y = gp.screenHeight/2 + (gp.tileSize*4);
				 g2.drawString(text, x, y);
			 

				g2.setFont(arial_80B);
				g2.setColor(Color.yellow);
				text = "Congratulations ";
				//returns the length of the text in a complicated way 
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
				 x = gp.screenWidth/2 - textLength/2;
				 y = gp.screenHeight/2 + (gp.tileSize*2);
				 g2.drawString(text, x, y);
			 
			gp.gameThread = null; //this should stop the game
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
			g2.drawString("x "+ gp.player.hasKey, 74, 65); // y idicates the baseline of the text get fucked
			
			//Time
			playTime +=(double)1/60;
			g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);

			//message
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

				messageCounter++;

				if(messageCounter > 120) { //2 seconds 
					messageCounter = 0;
					messageOn =false;
				}
			}
		}
*/

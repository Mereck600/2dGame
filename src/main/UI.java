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
	public String message = "";
	int messageCounter =0;
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
	public void showMessage(String text) {
		message = text;
		messageOn = true;

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
		
	}
	
	/*
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

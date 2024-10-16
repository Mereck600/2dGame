package main;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;


public class UI {
	//comented out code was code for the previous ui that had the treasure game
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	//BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter =0;
	public boolean gameFinished = false;
	public String currentDialouge = "";
	//double playTime;
	//DecimalFormat dFormat = new DecimalFormat("#0.00");
	




	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
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
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		
		//playstate
		if(gp.gameState == gp.playState) {
			//play stuff
		}
		
		//pause
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//dialouge 
		if(gp.gameState == gp.dialogueState) {
			drawDialougeScreen();
			 
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
		
		x += gp.tileSize;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32));
		g2.drawString(currentDialouge, x, y);
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

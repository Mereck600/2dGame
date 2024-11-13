package main;

import javax.swing.JPanel;


import entity.Entity;
import entity.Player;
//import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
	/**
	 * This is to set up the tiles and pixels on the screen 
	 */
	//Screen settings
	final int originalTileSize = 16; //means 16x16 tile default size for player character can update later
	final int scale = 3;  // this will make the character look 48x48 since pc is bigger screen
	public final int tileSize = originalTileSize * scale; //48x48 tile actuall displayed on screen 
	
	public final int maxScreenCol = 16; // 16 tiles on the screen vertically 
	public final int maxScreenRow = 12; // 16 tiles on the screen horizontally ratio 4x3
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels 
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//world map parameters: these can be changed if we want
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = tileSize * maxWorldCol;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	
	//FPS 
	int FPS = 60;
	//SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);	
	Sound music = new Sound(); //overall game music
	Sound se = new Sound(); //Sound effects for the game
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this); //starts the event handler
	Thread gameThread; //This is the clock set up once started it will run until stopped kinda like a loop
	
	//Entity and Onjects 
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[10]; //ten slots for object and can be replaced in the game but only 10 displayed
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20]; // number of monsters we can display
	ArrayList<Entity> entityList = new ArrayList<>(); //handles entity drawing order by worldY lowest is at 0 and highest is at end of array drawn
	
	
	
	//Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState=1;
	public final int pauseState = 2;
	public final int dialogueState =3; 
	public final int characterState =4;
	
	
	
	//another stare for chaning locations
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //if set to true all the drawing for this component will be buffered off screen
		this.addKeyListener(keyH);
		this.setFocusable(true); //this makes the gamePanel ale to recieve input
		
	}
	public void setUpGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster(); //calls the entities we want
		//playMusic(0);
		//stopMusic();	//	Rember you may need to uncomment this ************************************************************
		gameState = titleState;
	}

/**
 * this is the run method i.e. runnable and starts the thread thread calls this 
 */
	
	public void startGameThread() {
		gameThread = new Thread(this); //passing game panel class to this constructor 
		gameThread.start();
	}
	
	/**
	 * THis is the core of the game this is the game loop so in the loop it updates then repaints etc
	 */
	@Override
	//public void run() {
		//timing is in nano instead of millis to be more precise and aviod err 1bil nano is 1sec
		//double drawInterval = 1000000000/FPS; //0.01666secs therefore allocated time for a loop is this 
		//double nextDrawTime = System.nanoTime() + drawInterval; 
		//while(gameThread != null) {
			 
			
			
			
			//step 1: update information such as characters positions 
			//update();
			
			
			//step2: Draw the screen with the updated information attemping to do either 30 or 60 fps
			//repaint(); //this is how you call paint component method
			
			
			
			//try { //pauses the loop until the sleeptime is done 
				//double remainingTime = nextDrawTime - System.nanoTime();
				//remainingTime = remainingTime /1000000; //this is now correct bc you need to convert to mil
				
				//if(remainingTime <0) {
				//	remainingTime = 0;  //means that if the code takes too long set to 0 
				//}
				//Thread.sleep((long) remainingTime);
				
				//nextDrawTime += drawInterval;
				
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			
		//}
			
	//}
	/**
	 * every loop we add pasttime divided by drawInterval to delta 
	 * then we update and repaint and then reset delta
	 * fps is 60fps.
	 * this is more accurate than the aboe thread method
	 */
	public void run() {
		double drawInterval = 1000000000/FPS;
		
		double delta=0;
		long lastTime = System.nanoTime();
		long currentTime;
		while(gameThread !=null) {
			
		currentTime = System.nanoTime();
		delta +=(currentTime -lastTime)/drawInterval;
		lastTime = currentTime ;
		if(delta >= 1) {
		update();
		repaint();
		delta--;
		}
		
		}
	}
	
	
	/**
	 * Every time a key is pressed it is captured by KeyHandler and th eupdate chages the player cords
	 * then the pain component is called and rePaints the player model
	 */
	public void update() {
		if(gameState == playState) {
			//player
			player.update();
			for(int i =0; i<npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive ==true && monster[i].dying == false) {
						monster[i].update();
					}
					if(monster[i].alive ==false) {
						monster[i]=null;
					}
					
				}
			}
			
		}
		if(gameState == pauseState) {
			//nothing
		}
		
	}
	/**
	 * Existing componnet in java used to paint different characters or elements on screen
	 * graphics class is a class used to draw objects on screen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  //super means parent class ie. JPanel 
		
		Graphics2D g2 = (Graphics2D)g;//Graphics2D is a class that extends the graphics class to provide control over geo, cords, color, text
		//debug
		long drawStart =0;
		if(keyH.checkDrawTime ==true) {
			drawStart = System.nanoTime();
		}
		
		//Ttile Screen
		
		if(gameState == titleState) {
			ui.draw(g2);
		}
		//(add more)
		else {
			//*** DO NOT PUT DRAW ITEMS ABOVE (VERY IMPORTANT)  ****//
			tileM.draw(g2); // this needs to be before player tiles or any other layer this is base
			
			entityList.add(player);
			
			for(int i=0; i<npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
					
				}
			}
			for(int i =0; i<obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for(int i =0; i<monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			
			//sort
			Collections.sort(entityList, new Comparator<Entity>() {
				
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY,  e2.worldY);
					return result;
				}
			});
			
			//draw entities
			for(int i =0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
				
			}
			//Empty Entity list
			entityList.clear();
			
			
			//ui
			ui.draw(g2);
		}
		
		
		
		
		
		//more debug
		
		if(keyH.checkDrawTime ==true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10,400);
			System.out.println("Draw Time: " + passed);
		}
		
		
		g2.dispose(); //saves memory while not in use
		
		
		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i ) {
		se.setFile(i);
		se.play();
	}
	
}

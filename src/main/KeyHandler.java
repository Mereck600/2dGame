package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * So this class will be used to handle keyboard inputs as seen in the name 
 * KeyListner is the interface for recienving keyboard events
 */
public class KeyHandler implements KeyListener{
	//these 2 overrides needed for this to work 
	GamePanel gp;

	int directionPressed;

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	//debug
	public boolean checkDrawTime;

	//talk boolean



	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * this is used to get the direction of what key was pressed where
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // returns the integer keyCode associated witht he key in this event

		//Title State
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}

		//play state
		else if(gp.gameState == gp.playState){
			playState(code);
		}
		//pause state
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}	

		//dialouge state
		else if(gp.gameState == gp.dialogueState) {
			dialougeState(code);
		}
		//character state
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		else if(gp.gameState == gp.saveState) {
			saveState(code);
		}
		else if(gp.gameState == gp.dieState) {
			dieState(code);
		}
		else if(gp.gameState == gp.loadState) {
			loadState(code);
		}




	}
	/** 
	 * Key handler for the load 
	 * @param code
	 */
	public void loadState(int code) {
		if(code == KeyEvent.VK_W) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =3; 
			}

		}
		if(code == KeyEvent.VK_S) { //user presses w key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 3) {
				gp.ui.commandNum =0; 
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =3; 
				}
			}

		}

		if(code == KeyEvent.VK_UP) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =3; 
			}

		}
		if(code == KeyEvent.VK_DOWN) { //user presses w key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 3 ) {
				gp.ui.commandNum =0; 
			}

		}
		//Press the enter Key and then save the game
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.loadGame("basesave.txt");
				gp.gameState = gp.playState;
				}
			if(gp.ui.commandNum == 1) {
				gp.loadGame("savedata1.txt");
				gp.gameState = gp.playState;
			}
			if(gp.ui.commandNum == 2) {
				gp.loadGame("savedata2.txt");
				gp.gameState = gp.playState;
			}
			if(gp.ui.commandNum == 3) {
				gp.loadGame("savadata3.txt");
				gp.gameState = gp.playState;
			}
		}
		
	}

	public void saveState(int code) {
		if(code == KeyEvent.VK_W) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =3; 
			}

		}
		if(code == KeyEvent.VK_S) { //user presses w key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 3) {
				gp.ui.commandNum =0; 
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =3; 
				}
			}

		}

		if(code == KeyEvent.VK_UP) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =3; 
			}

		}
		if(code == KeyEvent.VK_DOWN) { //user presses w key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 3 ) {
				gp.ui.commandNum =0; 
			}

		}
		//Press the enter Key and then save the game
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.saveGame("basesave.txt");
				gp.gameState = gp.playState;
				}
			if(gp.ui.commandNum == 1) {
				gp.saveGame("savedata1.txt");
				gp.gameState = gp.playState;
			}
			if(gp.ui.commandNum == 2) {
				gp.saveGame("savedata2.txt");
				gp.gameState = gp.playState;
			}
			if(gp.ui.commandNum == 3) {
				gp.saveGame("savadata3.txt");
				gp.gameState = gp.playState;
			}
		}
	}
	/**
	 * Unholy evil code that makes me sad
	 * far too long and needs to be made simpler
	 * heres how it works you take a keyCode and then have a ui variable which is for what state the title screen is in aka which menu
	 * Next you check to see the players key input and move a cursor accordingly; i.e. ui class. 
	 * Then check if the player hits enter and do a set thing based on where in the code/ menu system you are.
	 * @param code
	 */
	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =2; 
				}

			}
			if(code == KeyEvent.VK_S) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 2 ) {
					gp.ui.commandNum =0; 
					if(gp.ui.commandNum< 0) {
						gp.ui.commandNum =2; 
					}
				}

			}

			if(code == KeyEvent.VK_UP) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =2; 
				}

			}
			if(code == KeyEvent.VK_DOWN) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 2 ) {
					gp.ui.commandNum =0; 
				}

			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) { //Selects the new game and starts the game and music
					gp.ui.titleScreenState = 1;

					//gp.playMusic(0);

				}
				if(gp.ui.commandNum == 1) {
					gp.gameState = gp.loadState;
				}//add later this is the load game
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}



		//else if(gp.ui.title == gp.titleState) {

		else if(gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_W) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3; 
				}
				//moves the cursor
			}
			if(code == KeyEvent.VK_S) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 3 ) {
					gp.ui.commandNum =0; 

				}

			}

			if(code == KeyEvent.VK_UP) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =3; 
				}

			}
			if(code == KeyEvent.VK_DOWN) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 3 ) {
					gp.ui.commandNum =0; 
				}

			}
			if (code == KeyEvent.VK_ENTER) {
				
				if(gp.ui.commandNum == 3) {
					System.out.println("going back.");
					gp.ui.titleScreenState =0;
					gp.ui.commandNum=0;
				}
				else {
					System.out.println("do something related to Character");
					gp.ui.titleScreenState =2;
				}
			}

		}
		
		
		
		else if(gp.ui.titleScreenState == 2) {
			if(code == KeyEvent.VK_W) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3; 
				}
				//moves the cursor
			}
			if(code == KeyEvent.VK_S) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 3 ) {
					gp.ui.commandNum =0; 

				}

			}

			if(code == KeyEvent.VK_UP) { //user presses w key
				//directionPressed = Direction.UP;
				gp.ui.commandNum--;
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =3; 
				}

			}
			if(code == KeyEvent.VK_DOWN) { //user presses w key
				gp.ui.commandNum ++;
				if(gp.ui.commandNum> 3 ) {
					gp.ui.commandNum =0; 
				}

			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) { 
					System.out.println("Start game!");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 1) { 
					System.out.println("Start game!");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 2) { 
					System.out.println("Start game!");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 3) { 
					// Set this to go back to the previous menu or title state
					System.out.println("Start game!");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
			}

		}


		//Add key handler for instruction screen later
	}
	/**
	 * Pretty simple just some ifs to check user input and sending that to the specified state or code section
	 * @param code
	 */
	public void playState(int code) {
		if(code == KeyEvent.VK_W) { //user presses w key
			//directionPressed = Direction.UP;
			upPressed = true;

		}
		if(code == KeyEvent.VK_S) { //user presses w key
			downPressed = true;

		}
		if(code == KeyEvent.VK_A) { //user presses w key
			leftPressed = true;

		}
		if(code == KeyEvent.VK_D) { //user presses w key
			rightPressed = true;

		}
		if(code == KeyEvent.VK_P) { //user presses w key
			gp.gameState = gp.pauseState;
			//gp.gameState = gp.saveState;



		}
		if(code == KeyEvent.VK_K) { //user presses w key
			//gp.saveGame();
			gp.gameState = gp.saveState;

		}
		if(code == KeyEvent.VK_L) { //user presses w key
			//also change to the title screen state that is the load function
			//gp.loadGame();
			gp.gameState = gp.loadState;


		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}

		if(code == KeyEvent.VK_ENTER) { //user presses w key
			enterPressed = true;

		}


		//debug 
		if(code == KeyEvent.VK_T) { //user presses w key
			if(checkDrawTime == false) {
				checkDrawTime = true; 

			}
			else if(checkDrawTime == true) {
				checkDrawTime = false;
			}

		}
	}
	/**
	 * Horribly simple and could use some work to the UI
	 * @param code
	 */
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) { //user presses w key
			gp.gameState = gp.playState;


		}
	}
	/**
	 * Changes the state to the dialouge state
	 * @param code
	 */
	public void dialougeState(int code) {
		if( code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;

		}
	}
	/**
	 * This shows the character stats
	 * @param code
	 */
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
	}
	/**
	 * What happens when the player dies
	 * @param code
	 */
	public void dieState(int code) {
		if(code == KeyEvent.VK_W) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =1; 
			}

		}
		if(code == KeyEvent.VK_S) { //user presses s key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 1) {
				gp.ui.commandNum =0; 
				if(gp.ui.commandNum< 0) {
					gp.ui.commandNum =1; 
				}
			}

		}

		if(code == KeyEvent.VK_UP) { //user presses w key
			//directionPressed = Direction.UP;
			gp.ui.commandNum--;
			if(gp.ui.commandNum< 0) {
				gp.ui.commandNum =1; 
			}

		}
		if(code == KeyEvent.VK_DOWN) { //user presses w key
			gp.ui.commandNum ++;
			if(gp.ui.commandNum> 1 ) {
				gp.ui.commandNum =0; 
			}

		}
		//Press the enter Key\
		
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) { // this is for the load game; this works
		//*************************************************************************Issue: when player levels up then dies, the heart gained from leveling up stays there**************
				
				gp.player.alive =true;
				//change to title screen state and and into the load section that way I can avoid making another ui method or anything
				//gp.loadGame("basesave.txt");
				gp.gameState = gp.loadState;
				//gp.gameState = gp.playState;
				}
			if(gp.ui.commandNum == 1) { // this is for the go back to title thsi works but when you go back into the title screen it starts on last menue and player is dead
				//this really should be reset in the title but its late and im tired
				gp.loadGame("basesave.txt"); //this should be used to load the base state of the world 
				gp.player.alive =true;
				gp.ui.titleScreenState =0; //this line right here fixed that issue of going to last menue i schould probably put this somewhere else but ok for now 
				gp.gameState = gp.titleState;
			 
				
			}
			
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) { //user presses w key
			directionPressed = Direction.STILL;
			upPressed = false;

		}
		if(code == KeyEvent.VK_S) { //user presses w key
			downPressed = false;

		}
		if(code == KeyEvent.VK_A) { //user presses w key
			leftPressed = false;

		}
		if(code == KeyEvent.VK_D) { //user presses w key
			rightPressed = false;

		}



	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

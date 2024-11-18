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
			
		}




	}

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
				if(gp.ui.commandNum == 1) {}//add later
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
				if (gp.ui.commandNum == 0) { 
					System.out.println("do something related to Wiz");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 1) { 
					System.out.println("do something related to Thief");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 2) { 
					System.out.println("do something related to Dude");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.commandNum == 3) { 
					// Set this to go back to the previous menu or title state
					gp.ui.titleScreenState = 0;
					System.out.println("Going back to the previous menu");
				}
			}

		}


		//Add key handler for instruction screen later
	}

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


		}
		if(code == KeyEvent.VK_K) { //user presses w key
			gp.saveGame();


		}
		if(code == KeyEvent.VK_L) { //user presses w key
			gp.loadGame();
			


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
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) { //user presses w key
			gp.gameState = gp.playState;


		}
	}

	public void dialougeState(int code) {
		if( code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;

		}
	}

	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
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

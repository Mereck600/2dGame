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
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	//debug
	public boolean checkDrawTime;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * this is used to get the direction of what key was pressed where
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // returns the integer keyCode associated witht he key in this event
		//play state
		if(gp.gameState == gp.playState){
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
		//pause state
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) { //user presses w key
				gp.gameState = gp.playState;
				

			}
		}
		
		//dialouge state
		else if(gp.gameState == gp.dialogueState) {
			if( code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
				
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

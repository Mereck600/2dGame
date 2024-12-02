package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip; //used to open sound files have to be in .wav format 
	URL soundURL[] = new URL[30];  //use url to stroe file path of the sounds
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav"); 
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swing.wav");
		soundURL[8] = getClass().getResource("/sound/levelup.wav");
		soundURL[9] = getClass().getResource("/sound/endingEscape.wav"); //not used yet 
		soundURL[10] = getClass().getResource("/sound/villagerSE.wav"); //joke change later


		
		
	}
	/**
	 * Changes the file that we wish to play
	 * 
	 * @param i
	 */
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); //play sound in index
			clip = AudioSystem.getClip();
			clip.open(ais);  //al of the above lines are to open an audio file in java
			
			
		}catch(Exception e) {
			
		}
		
	}
	/**
	 * i feel this is self explanatory
	 */
	public void play() {
		clip.start();
		
		
	}
	/**
	 * i feel this is self explanatory
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		
	}
	/**
	 * i feel this is self explanatory
	 */
	public void stop() {
		clip.stop();
		
		
	}

}

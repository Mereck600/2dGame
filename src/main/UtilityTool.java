/**
 * class to handel shit i dont wanna type 
 * ie scaling 
 */

package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaledImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); //instancate and pass size
		Graphics2D g2 = scaledImage.createGraphics(); //save in scaled image
		g2.drawImage(original, 0, 0,width, height, null); //draw at the right size 
		g2.dispose();
		return scaledImage;
	}
	
}

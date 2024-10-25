package testCases;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

public class PlayerTest {

    Player player;
    GamePanel gp;
    KeyHandler keyH;

    @BeforeEach
    public void setup() {
       
        gp = new GamePanel();
        keyH = new KeyHandler(gp);
        player = new Player(gp, keyH);
    }

   
    @Test
    public void testUpdate_movementUp() {
        keyH.upPressed = true;
        int originalWorldY = player.worldY;
        player.update();
        
        assertEquals("up", player.direction);
        assertTrue(player.worldY < originalWorldY); // Y should decrease when moving up
    }

    @Test
    public void testUpdate_noMovement() {
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;

        int originalWorldX = player.worldX;
        int originalWorldY = player.worldY;
        player.update();

        assertEquals(originalWorldX, player.worldX);
        assertEquals(originalWorldY, player.worldY);
    }

   

    @Test
    public void testInteractNPC_noNPC() {
        player.interactNPC(0);
        assertEquals(3, gp.dialogueState);
    }
}

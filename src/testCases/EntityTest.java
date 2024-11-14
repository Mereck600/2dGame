package testCases;


import static org.junit.Assert.*;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import org.junit.Before;
import org.junit.Test;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class EntityTest {

    Player player;
    Enemy enemy;
    GamePanel gp;
    main.KeyHandler keyH;

    @Before
    public void setUp() {
        
        player = new Player(gp, keyH);
        enemy = new Enemy(gp);

        // Setting initial health for player and enemy for testing
        player.life = 6;  
        enemy.health = 5;  
    }


    @Test
    public void testPlayerTakeDamage() {
        int initialHealth = player.life;

        player.PlayerTakeDamage(2); 

        // Verify the health decreases by 2
        assertEquals( 4, player.life);
    }
}
package testCases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import monster.MON_Goblin;
import monster.MON_GreenSlime;
import entity.Entity;
import entity.Player;
import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;

public class EntityTest {

    Player player;
    Entity MON_Goblin;
    Entity MON_GreenSlime;
    GamePanel gp;
    KeyHandler keyH;
	public AssetSetter aSetter = new AssetSetter(gp);

    @Before
    public void setUp() {
        // Initialize GamePanel and KeyHandler
        gp = new GamePanel();
        keyH = new KeyHandler(gp);
        aSetter.setMonster();
        // Initialize Player and Enemy
        player = new Player(gp, keyH);
        MON_Goblin = new MON_Goblin(gp);

        // Setting initial health for player and enemy for testing
        player.life = 6;  
        MON_Goblin.life = 5;  
    }

    @Test
    public void testPlayerTakeDamage() {
        int initialHealth = player.life;

        player.contactMonster(2); 

        // Verify the health decreases by 2
        assertEquals("Player health should decrease by 2", initialHealth - 2, player.life);
    }

    @Test
    public void testPlayerDealDamageToEnemy() {
        int initialHealth = MON_Goblin.life;

        player.damageMonster(5); 

        // Verify enemy health decreases by 1
        assertEquals(initialHealth - 1, MON_Goblin.life);

        // Verify health decreases correctly when multiple damages are dealt
        player.damageMonster(5);
        assertEquals(initialHealth - 1, MON_Goblin.life);
    }

    @Test
    public void playerDamageToSlime() {
        int initialHealth = MON_GreenSlime.life;

        player.damageMonster(5); 
        assertEquals( initialHealth - 3, MON_GreenSlime.life);

        player.damageMonster(5);  
        assertEquals(initialHealth - 3, MON_GreenSlime.life);
    }
}

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
    public void testEnemyTakeDamage() {
        int initialHealth = MON_Goblin.life;

        MON_Goblin.takeDamage(3);  
        assertEquals("Enemy health should decrease by 3", initialHealth - 3, MON_Goblin.life);

        // Test if enemy dies when health reaches 0
        MON_Goblin.takeDamage(3); 
        assertTrue("Enemy should have died when health is 0 or less", MON_Goblin.life <= 0);
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
        assertEquals("Player health should decrease by 1", initialHealth - 1, MON_GreenSlime.life);

        enemy.dealDamage(player);  
        assertEquals("Player health should decrease by 2 after two attacks", initialHealth - 2, MON_GreenSlime.life);
    }
}

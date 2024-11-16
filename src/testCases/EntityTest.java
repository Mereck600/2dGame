import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import monster.MON_Goblin;
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
        int initialHealth = enemy.health;

        MON_Goblin.takeDamage(3);  
        assertEquals("Enemy health should decrease by 3", initialHealth - 3, MON_Goblin.health);

        // Test if enemy dies when health reaches 0
        MON_Goblin.takeDamage(3); 
        assertTrue("Enemy should have died when health is 0 or less", enemy.health <= 0);
    }

    @Test
    public void testPlayerDealDamageToEnemy() {
        int initialHealth = enemy.health;

        player.playerDealDamage(enemy); 

        // Verify enemy health decreases by 1
        assertEquals("Enemy health should decrease by 1", initialHealth - 1, enemy.health);

        // Verify health decreases correctly when multiple damages are dealt
        player.playerDealDamage(enemy);
        assertEquals("Enemy health should decrease by 2 after two attacks", initialHealth - 2, enemy.health);
    }

    @Test
    public void testEnemyDealDamageToPlayer() {
        int initialHealth = player.life;

        enemy.dealDamage(player); 
        assertEquals("Player health should decrease by 1", initialHealth - 1, player.life);

        enemy.dealDamage(player);  
        assertEquals("Player health should decrease by 2 after two attacks", initialHealth - 2, player.life);
    }
}

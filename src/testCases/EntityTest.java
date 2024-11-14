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
    
    @Test
    public void testEnemyTakeDamage() {
        int initialHealth = enemy.health;
        
        enemy.takeDamage(3);  
        assertEquals("Enemy health should decrease by 3", initialHealth - 3, enemy.health);

        // Test if enemy dies when health reaches 0
        enemy.takeDamage(3); 
        assertEquals("Enemy died!", enemy.health <= 0);
    }
    @Test
    public void testPlayerDealDamageToEnemy() {
        int initialHealth = enemy.health;

        
        player.playerDealDamage(enemy); 

        
        assertEquals( initialHealth - 1, enemy.health);

        // Test if the enemy's health decreases correctly when multiple damages are dealt
        player.playerDealDamage(enemy);  // Player deals another 1 damage
        assertEquals(initialHealth - 2, enemy.health);
    }
    
    @Test
    public void testEnemyDealDamageToPlayer() {
        int initialHealth = player.life;

        
        enemy.dealDamage(player); 
        assertEquals( initialHealth - 1, player.life);

        
        enemy.dealDamage(player);  
        assertEquals( initialHealth - 2, player.life);
    }
}

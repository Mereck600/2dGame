package testCases;

import main.GamePanel;
import monster.MON_Goblin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Player;

import static org.junit.jupiter.api.Assertions.*;

class MON_GoblinTest {

    GamePanel gamePanel;
    MON_Goblin goblin;
    Player player;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
        goblin = new MON_Goblin(gamePanel);
        player = new Player(gamePanel, null); // Assuming null for KeyHandler
    }

    @Test
    void testGoblinInitialValues() {
        assertEquals(10, goblin.life, "Goblin should start with 10 life.");
        assertEquals(3, goblin.attack, "Goblin should start with an attack value of 3.");
        assertEquals(1, goblin.defense, "Goblin should start with a defense value of 1.");
        assertEquals("Goblin", goblin.name, "Goblin's name should be 'Goblin'.");
    }

    @Test
    void testDamageGoblin() {
        int initialLife = goblin.life;
        player.damageMonster(4); // Damage = 4, Knockback = 1
        assertEquals(initialLife - 4, goblin.life, "Goblin life should decrease by the damage dealt.");
    }

    @Test
    void testGoblinSurvivesDamage() {
        int initialLife = goblin.life;
        player.damageMonster(5); // Damage = 5, No knockback
        assertFalse(goblin.life <= 0, "Goblin should not be dead after taking less damage than its total life.");
        assertEquals(initialLife - 5, goblin.life, "Goblin life should decrease correctly.");
    }

    @Test
    void testGoblinDiesFromDamage() {
        player.damageMonster(goblin.life); // Damage equal to goblin's life
        assertTrue(goblin.life <= 0, "Goblin should die when its life reaches 0.");
    }

    
    
}

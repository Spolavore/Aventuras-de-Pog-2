package test;

import entities.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player(null);
    }

    @Test
    public void testDecreaseLife_PlayerHasLife_LifeDecreased() {
        int initialLife = player.getLifes();

        player.decreaseLife();

        assertEquals(initialLife - 1, player.getLifes());
    }

    @Test
    public void testDecreaseLife_PlayerHasZeroLife() {
        player.setLifes(1);

        player.decreaseLife();

        assertEquals(0, player.getLifes());
    }

    @Test
    public void testIncreaseScore_ScoreIncreased() {
        int initialScore = player.getScore();

        player.increaseScore(100);

        assertEquals(initialScore + 100, player.getScore());
    }

    @Test
    public void testReset_PlayerAttributesReset() {
        player.setLifes(2);
        player.setScores(500);

        player.reset();

        assertEquals(3, player.getLifes());
        assertEquals(0, player.getScore());
    }
}
package test;

import physics.Gravity;
import entities.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GravityTest {

    private Gravity gravity;
    private Player player;

    @Before
    public void setUp() {
        player = new Player(null);
        gravity = new Gravity(player);
    }

    @Test
    public void testGravityForce_PlayerNotFalling_NoChangeInYPosition() {
        player.setFalling(false);
        player.setJumping(false);
        player.setPlayerCanMove(true, 0);

        gravity.gravityForce();

        assertEquals(576, player.getY());
    }

    @Test
    public void testGravityForce_PlayerFallingAndCanMoveDown_ChangesInYPosition() {
 
        player.setXY(0,575);
        player.setFalling(true);
        player.setJumping(false);
        player.setPlayerCanMove(true, 0);

        gravity.gravityForce();

        assertNotEquals(575, player.getY());
    }

    @Test
    public void testGravityForce_PlayerFallingButCannotMoveDown_NoChangeInYPosition() {
        player.setXY(0,575);
        player.setFalling(true);
        player.setJumping(false);
        player.setPlayerCanMove(true, 0);

        gravity.gravityForce();

        assertEquals(576, player.getY());
    }
}

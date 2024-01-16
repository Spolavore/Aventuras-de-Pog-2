package test;

import physics.Jump;
import utils.Constants.Directions;
import entities.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JumpTest {

    private Jump jump;
    private Player player;

    @Before
    public void setUp() {
        player = new Player(null);
        jump = new Jump(player);
    }

    @Test
    public void testJump_PlayerCanMoveInUpDirection_PlayerMovesUp() {
        player.setJumping(false);
        player.setPlayerCanMove(true, 0); // Player can move up

        jump.jump();

        assertEquals(575, player.getY());
    }

    @Test
    public void testJumpWithMovimentation_PlayerCanMoveInLeftDirection_PlayerMovesUpAndLeft() {
        player.setJumping(true);
        player.setPlayerCanMove(true, 0); // Player can move up
        player.setPlayerCanMove(true, 1); // Player can move left

        int initialXPosition = player.getX();
        int initialYPosition = player.getY();

        jump.jumpWithMovimentation(Directions.LEFT);

        assertEquals(initialXPosition - 1, player.getX());
        assertEquals(initialYPosition - 1, player.getY());
    }

    @Test
    public void testJumpWithMovimentation_PlayerCanMoveInRightDirection_PlayerMovesUpAndRight() {
        player.setJumping(true);
        player.setPlayerCanMove(true, 0); // Player can move up
        player.setPlayerCanMove(true, 3); // Player can move right

        int initialXPosition = player.getX();
        int initialYPosition = player.getY();

        jump.jumpWithMovimentation(Directions.RIGHT);

        assertEquals(initialXPosition + 1, player.getX());
        assertEquals(initialYPosition - 1, player.getY());
    }
}

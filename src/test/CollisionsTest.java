package test;

import entities.Player;
import org.junit.Before;
import org.junit.Test;
import physics.Collisions;
import static org.junit.Assert.*;

public class CollisionsTest {

    private char[][] testMap;
    private Player testPlayer;

    @Before
    public void setUp() {
        // Inicializa um mapa de teste
        testMap = new char[][]{
            "                                       ".toCharArray(),
            "                                       ".toCharArray(),
            "                                     c ".toCharArray(),
            "                                     <_".toCharArray(),
            "                                       ".toCharArray(),
            "                                       ".toCharArray(),
            "                                 <>    ".toCharArray(),
            "                                       ".toCharArray(),
            "                              +        ".toCharArray(),
            "                            (===)      ".toCharArray(),
            "                            [---]      ".toCharArray(),
            "                      (==)             ".toCharArray(),
            "                      [--]             ".toCharArray(),
            "                (==)                   ".toCharArray(),
            "                [--]                   ".toCharArray(),
            "                                       ".toCharArray(),
            "          (==)                         ".toCharArray(),
            "          [--]                         ".toCharArray(),
            "     <_>                               ".toCharArray(),
            "                                       ".toCharArray(),
            "=======================================".toCharArray()
    };


        // Inicializa um jogador para teste
        testPlayer = new Player(null);
    }

    @Test
    public void testCheckCollisions_PlayerCanMoveLeft_PlayerCanMoveLeftIsTrue() {
        Collisions collisions = new Collisions(testMap, testPlayer);

        collisions.checkCollisions();

        assertTrue(testPlayer.canMove()[0]);
    }

    @Test
    public void testCheckCollisions_PlayerIsFalling_PlayerIsFallingIsTrue() {
       
        Collisions collisions = new Collisions(testMap, testPlayer);
        testPlayer.setFalling(true);

        assertTrue(testPlayer.isFalling());
    }
}

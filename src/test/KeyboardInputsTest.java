package test;

import main.Game;
import java.awt.event.KeyEvent;
import inputs.KeyboardInputs;
import entities.Player;
import main.GamePanel;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KeyboardInputsTest {

    private Game game;
    private GamePanel gamePanel;
    private Player player;
    private KeyboardInputs keyboardInputs;

    @Before
    public void setUp() {
        game = new Game();
        gamePanel = new GamePanel(game);
        player = new Player(gamePanel);
        keyboardInputs = new KeyboardInputs(gamePanel, player);
    }

    @Test
    public void testKeyReleased() {
        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_A, 'A'));
        assertTrue(KeyboardInputs.sizeDirectionStack() > 0);

        keyboardInputs.keyReleased(createKeyEvent(KeyEvent.VK_A, 'A'));
        assertEquals(0, KeyboardInputs.sizeDirectionStack());
        assertFalse(player.isMoving());

        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_D, 'D'));
        assertTrue(KeyboardInputs.sizeDirectionStack() > 0);

        keyboardInputs.keyReleased(createKeyEvent(KeyEvent.VK_D, 'D'));
        assertEquals(0, KeyboardInputs.sizeDirectionStack());
        assertFalse(player.isMoving());
    }

    @Test
    public void testKeyPressed() {
        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_A, 'A'));
        assertEquals(-1, KeyboardInputs.getLastDirection());
        assertTrue(player.isMoving());

        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_D, 'D'));
        assertEquals(1, KeyboardInputs.getLastDirection());
        assertTrue(player.isMoving());

    }

    @Test
    public void testGameResetOnKeyPress() {
        gamePanel.setGameState("Lose");
        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_SPACE, ' '));
        assertEquals("In game", gamePanel.getGameState());

        gamePanel.setGameState("Victory");
        keyboardInputs.keyPressed(createKeyEvent(KeyEvent.VK_SPACE, ' '));
        assertEquals("In game", gamePanel.getGameState());
    }

    private KeyEvent createKeyEvent(int keyCode, char keyChar) {
        return new KeyEvent(new GamePanel(game), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keyChar);
    }
}
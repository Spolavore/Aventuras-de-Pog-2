package test;


import main.Game;
import main.GamePanel;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class GamePanelTest {
    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel(new Game());
    }

    @Test
    public void testGameStateAfterInitialization() {
        assertEquals("In game", gamePanel.getGameState());
    }

    @Test
    public void testPlayerInitialState() {
        assertEquals(3, gamePanel.getPlayer().getLifes());
        assertEquals(0, gamePanel.getPlayer().getScore());
    }

    @Test
    public void testLevelInitialState() {
        assertEquals(1, gamePanel.getGamePLevel().getCurrentLevel());
    }
}
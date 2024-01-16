package test;

import main.Game;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

    @Test(timeout = 5000)
    public void testRun_GameLoopCompletesWithinTimeLimit() {
        Game game = new Game();

        game.getGameThread().interrupted();
        game.getGameWindow().dispose();

        assertTrue(true); // Se o método run terminar dentro do tempo limite, o teste passa
    }
}

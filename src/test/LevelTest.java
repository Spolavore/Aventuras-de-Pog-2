package test;

import main.Game;
import levels.Level;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LevelTest {

    private Game game;
    private Level level;

    @Before
    public void setUp() {
        game = new Game();
        level = new Level(game);
    }

    @Test
    public void testGoNextLevel() {
        Level.goNextLevel();
        assertEquals(2, Level.currentLevel);

        Level.goNextLevel();
        assertEquals(3, Level.currentLevel);

        level.resetToLevel1();
        Level.goNextLevel();
        assertEquals(2, Level.currentLevel);
    }
}

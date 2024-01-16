package test;

import objects.Chest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChestTest {

    private Chest chest;

    @Before
    public void setUp() {
        chest = new Chest(0, 0, "default");
    }

    @Test
    public void testUpdateAnimationTick_ChangeAnimationTick_TickUpdated() {
        int initialTick = chest.getChestAniTick();

        chest.updateAnimationTick();

        assertNotEquals(initialTick, chest.getChestAniTick());
    }

    @Test
    public void testChangeTypeOfAnimation_ValidType_TypeChanged() {
        String newAnimationType = "opening";

        chest.changeTypeOfAnimation(newAnimationType);

        assertEquals(newAnimationType, chest.getTypeOfAnimation());
    }

    @Test
    public void testSetIsOpened_True_IsOpenedSetToTrue() {
        chest.setIsOpened(true);

        assertTrue(chest.isOpened());
    }
}
package test;

import physics.Damage;
import entities.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DamageTest { 

    private Damage damage;
    private Player player;

    @Before
    public void setUp() {
        player = new Player(null);
        damage = new Damage(player);
    }

    @Test
    public void testApplyDamage_PlayerNotFalling_NoDamageApplied() {
        player.setFalling(false);

        damage.applyDamage();

        assertEquals(3, player.getLifes()); 
    }

    @Test
    public void testApplyDamage_PlayerFallingButFewBlocksFell_NoDamageApplied() {
        player.setFalling(true);

        damage.applyDamage();

        assertEquals(3, player.getLifes());
    }
    
}



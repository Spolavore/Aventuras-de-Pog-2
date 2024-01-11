package physics;

import entities.Player;

public class Damage {
    private Player player;
    private int blocksFell;
    private int lastBlockChecked;
    private boolean shouldApply = false;

    public Damage(Player player) {
        this.player = player;

    }

    public void applyDamage() {
       
        verifyBlocksFelt();
        if (blocksFell >= 9) {
            shouldApply = true;
        }
        // Faz com que o player só tome dano ao tocar no solo
        if (shouldApply && !player.isFalling()) {
            player.decreaseLife();
            blocksFell = 0;
            shouldApply = false;
        }

    }

    private void verifyBlocksFelt() {
        if (player.isFalling()) {

            int yPosition = player.getY();
            int currentBlock = yPosition / 32;

            if (currentBlock != lastBlockChecked) {
                this.lastBlockChecked = currentBlock;
                blocksFell += 1;

            }

        } else {
            blocksFell = 0;
            lastBlockChecked = -1;
        }
    }
}

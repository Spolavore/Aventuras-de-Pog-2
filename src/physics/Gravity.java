package physics;

import entities.Player;

public class Gravity {
    private Player player;
    private int gravityIntensity = 1; // puxa para baixo na intensidade 1

    public Gravity(Player player) {
        this.player = player;

    }

    public void gravityForce() {
        if (player.isFalling() && !player.isJumping() && player.canMove()[2]) {
            player.updateYPosition(gravityIntensity);
        }
    }
}

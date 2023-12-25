package physics;

import java.util.Timer;
import java.util.TimerTask;

import entities.Player;

public class Jump {
    private Player player;
    private boolean jumpIsCoolDown = false;

    public Jump(Player player) {
        this.player = player;
    }

    public void jump() {
        player.updateYPosition(-1);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.setJumping(false);
                jumpIsCoolDown = true;
            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
       
                jumpIsCoolDown = false;
            }
        }, 1500);

    }

    public boolean coolDownIsOn(){
        return this.jumpIsCoolDown;
    }
}

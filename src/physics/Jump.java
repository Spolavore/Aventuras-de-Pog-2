package physics;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import entities.Player;
import inputs.KeyboardInputs;
import utils.Constants.Directions;

public class Jump {
    private Player player;
    private boolean jumpIsCoolDown = false;
    private int coolDownTime = 1500;
    public Jump(Player player) {
        this.player = player;
    }

    public void jump() {
       


        player.updateYPosition(-1);
        player.setTypeOfAnimation(3);

        applyCooldown();
        


    }
    

    private void applyCooldown(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.setJumping(false);
                jumpIsCoolDown = true;
               player.setTypeOfAnimation(0);

            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                jumpIsCoolDown = false;
            }
        }, coolDownTime);
    }


    public boolean coolDownIsOn(){
        return this.jumpIsCoolDown;
    }
}

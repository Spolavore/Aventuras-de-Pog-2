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
    private int coolDownTime = 500;
    private static boolean playerJumped = false;
    private static boolean canJumpAgain = true;

    public Jump(Player player) {
        this.player = player;
    }

    public void jump() {
        int lastDirection = KeyboardInputs.getLastDirection();
        if (player.canMove()[0] && canJumpAgain) {
            player.updateYPosition(-1);
            player.setTypeOfAnimation(3);
            cancelJump();
        } else {
            player.setJumping(false);
        }

    }

    public void jumpWithMovimentation(int Direction) {
        if (player.canMove()[0]) {
            player.updateYPosition(-1);
            player.updateXPosition(Direction);
            player.setTypeOfAnimation(3);
            cancelJump();
        } else {
            player.setJumping(false);
        }

    }

    private void cancelJump() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.setJumping(false);
                jumpIsCoolDown = true;
                playerJumped = true;
                canJumpAgain = false;

            }
        }, 400);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jumpIsCoolDown = false;

            }
        }, coolDownTime);
    }

    public static void reset() {
   
        playerJumped = false;
        canJumpAgain = true;

    


    }

    public boolean coolDownIsOn() {
        return this.jumpIsCoolDown;
    }

    public static boolean canJumpAgain() {
        return canJumpAgain;
    }

    public static boolean playerJumped() {
        return Jump.playerJumped;
    }
}

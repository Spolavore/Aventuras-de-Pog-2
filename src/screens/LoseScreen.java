package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;
import objects.ChestHandler;
import soundtrack.SoundHandler;

public class LoseScreen {
    private GamePanel gamePanel;
    private boolean soundsIsPlaying = false;
    public LoseScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
        
    }


    public void loadLoseScreen(Graphics g,  int playerPoints){
        SoundHandler.stopBackgroundSound();
        draw(g, playerPoints);
        playLoseScreenSounds();
    }


    private void draw(Graphics g, int playerPoints) {
        gamePanel.changeBackgroundColor(Color.BLACK);
        Font fontText = new Font(Font.SANS_SERIF, Font.BOLD, 40);

        g.setFont(fontText);
        g.setColor(Color.RED);
        g.drawString("GAME OVER :(", 500, 150);
       fontText = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        g.setFont(fontText);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + playerPoints, 575, 300);
        g.drawString("Press any button to restart game", 400 , 450);
        
    }


    private void playLoseScreenSounds(){
        if(!soundsIsPlaying){
                SoundHandler.playSound("sounds/som_derrota.wav");
                soundsIsPlaying = true;
            }

    }
}

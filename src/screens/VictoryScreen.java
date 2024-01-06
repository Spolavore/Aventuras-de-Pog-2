package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;
import soundtrack.SoundHandler;
import utils.AssetsHandler;

public class VictoryScreen {
    private GamePanel gamePanel;
    private boolean soundsIsPlaying = false;


    public VictoryScreen(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void draw(Graphics g, int playerPoints){
        Font fontText = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        gamePanel.changeBackgroundColor(Color.BLACK);
        g.setFont(fontText);
        g.setColor(Color.YELLOW);
        g.drawString("VICTORY!", 500 , 150 );
        g.setColor(Color.YELLOW);
        fontText = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        g.setFont(fontText);
        g.drawString("Score: " + playerPoints, 540, 230);
        g.drawImage(AssetsHandler.LoadAssets("/win/dennis.png"), 490, 270, null);

        g.setColor(Color.WHITE);
        g.drawString("Teacher Dennis is proud of you! :)", 385, 500);
        g.setColor(Color.yellow);
        fontText = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        g.setFont(fontText);       
        g.drawString("Press any button to restart game", 438, 550);
        g.setColor(Color.yellow);
    }


    public void playVictorySounds(){
   
        if(!soundsIsPlaying){
            SoundHandler.playSound("sounds/victory_sound_effect.wav");
            soundsIsPlaying = true;
        }
    }

    public void setSoundIsPlaying(boolean bool){
        this.soundsIsPlaying = bool;
    }
}

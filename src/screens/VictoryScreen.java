package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;

public class VictoryScreen {
    private GamePanel gamePanel;



    public VictoryScreen(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void draw(Graphics g, int playerPoints){
        Font fontText = new Font(Font.SANS_SERIF, Font.BOLD, 40);

        g.setFont(fontText);
        g.setColor(Color.RED);
        g.drawString("VICTORY!", 500 , 150 );
    }
}

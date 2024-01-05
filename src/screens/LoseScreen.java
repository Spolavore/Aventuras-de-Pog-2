package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;
import objects.ChestHandler;

public class LoseScreen {
    private GamePanel gamePanel;

    public LoseScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics g, int playerPoints) {
        gamePanel.changeBackgroundColor(Color.BLACK);
        Font fontText = new Font(Font.SANS_SERIF, Font.BOLD, 40);

        g.setFont(fontText);
        g.setColor(Color.RED);
        g.drawString("GAME OVER :(", 500, 150);
       fontText = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        g.setFont(fontText);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + playerPoints, 575, 300);

        
    }
}

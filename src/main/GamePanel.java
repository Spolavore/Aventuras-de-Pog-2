package main;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Graphics;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xPosition = 100;
    private int yPosition = 100;
    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect( xPosition, yPosition, 200, 50);
    }


    public void setXPosition(int xPosition) {
        this.xPosition += xPosition;
        
        repaint();
    }

    public void setYPosition(int yPosition) {
  
        this.yPosition += yPosition;
        repaint();

    }

    public void setRectPos(int x , int y){
        this.xPosition = x;
        this.yPosition = y;
        repaint();
    }
}

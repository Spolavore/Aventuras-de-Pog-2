package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img,subImg;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        importImg();
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

   

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(img.getSubimage(0, 0, 64, 64),0,0, 120, 80,null);
       
        
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

   
     private void importImg() {
        
        InputStream is = getClass().getResourceAsStream("/char_blue.png");
        try {
            System.out.println(is);
            img = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

   
 
}

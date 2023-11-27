package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class GamePanel extends JPanel {
    private Player player;
    private MouseInputs mouseInputs;



    // As animações possuem 7 tipos, cada uma é uma linha da matriz animations
    // algumas animações possuem apenas 5 sprites, enquanto outras 8. Esses números
    // referem-se às colunas da matriz citado
    // Para saber todas as animações possíveis ir res/char_blue.png
    
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        this.player = new Player(this, 0, 100);
        
        player.loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this, this.player));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

   

  
    public void updateGame(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

       
       
        player.render(g);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

  

}

package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import levels.LevelHandler;
import utils.Constants.Directions;
import utils.Constants.Sprites;
import utils.Constants.Directions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.GameDimentions;


public class GamePanel extends JPanel {
    private Game game;
    private Player player;
    private MouseInputs mouseInputs;
    private LevelHandler levelHandler;


    // As animações possuem 7 tipos, cada uma é uma linha da matriz animations
    // algumas animações possuem apenas 5 sprites, enquanto outras 8. Esses números
    // referem-se às colunas da matriz citado
    // Para saber todas as animações possíveis ir res/char_blue.png
    
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.player = new Player(this, 0, 100);
        levelHandler = new LevelHandler(game);
        
        String[] teste = {Sprites.GROUND_TILE,Sprites.GROUND_TILE_2,Sprites.LEFT_GROUND_TILE, Sprites.RIGHT_GROUND_TILE};
        levelHandler.importLevelSprites(teste);
        player.loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this, this.player));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

   

  
    public void updateGame(){
        player.update();
        levelHandler.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.render(g);
        levelHandler.drawLevel(g);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(GameDimentions.GAME_WIDTH, GameDimentions.GAME_HEIGHT);
        System.out.println("size: " + GameDimentions.GAME_WIDTH + " | " + GameDimentions.GAME_HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }


    public void windowLostFocus(){
        System.out.println("Jogo foi minimizado");
        player.setMoving(false);
        player.setTypeOfAnimation(0);
    }
  

}

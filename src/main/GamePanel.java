package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import levels.LevelHandler;
import physics.Collisions;
import levels.Level;
import utils.Constants;
import utils.Constants.Directions;
import utils.Constants.Sprites;
import utils.Constants.Directions.*;
import utils.Constants.GameDimentions;
import utils.Constants.LevelDefaultAssets;
import utils.Constants.LevelMatrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.GameDimentions;
import static utils.Constants.LevelMatrix;

public class GamePanel extends JPanel {
    private Game game;
    private Player player;
    private Level level;
    private MouseInputs mouseInputs;
    private LevelHandler levelHandler;
    private Collisions collisions;

    public GamePanel(Game game){
        // instanciação
        this.player = new Player(this, 16, 576); // posição atual do player em determinada fase
        this.level = new Level(LevelMatrix.Level1Map,game);
        this.collisions = new Collisions(getNowMap(), player);
        mouseInputs = new MouseInputs(this);
        level.loadMapAssets(LevelDefaultAssets.LEVEL_1_FLOOR, LevelDefaultAssets.LEVEL_1_PLATAFORM, LevelDefaultAssets.LEVEL_1_LIMITOR);
        player.loadAnimations();
        setPanelSize();
        addMouseListener(mouseInputs);
        addKeyListener(new KeyboardInputs(this, this.player,this.collisions));
        addMouseMotionListener(mouseInputs);
    }

   

    public void updateGame(){
        player.update();
        collisions.checkCollisions();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.render(g);
        level.draw(LevelMatrix.Level1Map,g);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(GameDimentions.GAME_WIDTH, GameDimentions.GAME_HEIGHT);
        System.out.println("size: " + GameDimentions.GAME_WIDTH + " | " + GameDimentions.GAME_HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }


    // Função que controla o jogo quando o usuário minimiza o jogo ou 
    // clica fora da tela
    public void windowLostFocus(){
        System.out.println("Jogo foi minimizado");
        // Para o jogador caso ele esteja se movendo e
        // limpa a pilha de direções para eliminar qualquer
        // outra direção armazenada
        player.setMoving(false);
        player.setTypeOfAnimation(0);
        KeyboardInputs.clearStack();
    }
    
    // Retorna o Mapa Atual da fase
    public char[][] getNowMap(){
        return this.level.getMatrixMap();
    }
}

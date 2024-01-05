package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import levels.LevelHandler;
import objects.Chest;
import objects.ChestHandler;
import physics.Collisions;
import physics.Gravity;
import screens.LoseScreen;
import soundtrack.SoundHandler;
import levels.Level;
import utils.Constants.BufferedImagesAssets;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import static utils.Constants.GameDimentions;
import static utils.Constants.LevelMatrix;

public class GamePanel extends JPanel {
    private Player player;
    private Level level;
    private MouseInputs mouseInputs;
    private Collisions collisions;
    private Gravity gravity;
    private Color backgroundColor = Color.WHITE;
    private static boolean changeLevel = false;

    public GamePanel(Game game) {
        // instanciação
        this.player = new Player(this); // posição inicial do player em determinada fase
        this.level = new Level(game);
        this.collisions = new Collisions(getNowMap(), player);
        this.gravity = new Gravity(player);
        this.mouseInputs = new MouseInputs(this);

        level.loadMapAssets();
        player.loadAnimations();
        setPanelSize();
        addMouseListener(mouseInputs);
        addKeyListener(new KeyboardInputs(this, this.player));
        addMouseMotionListener(mouseInputs);

      
    }

    public void updateGame() {
        if (changeLevel) {
            player.resetToInitialPosition();
            collisions.updateLevelToCheck(level.getMatrixMap());
            changeLevel = false;
        }
        
        ChestHandler.updateChests();
        player.update();
        collisions.checkCollisions();
        gravity.gravityForce();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(player.getLifes() == 0){
            player.render(g);
            level.draw(level.getMatrixMap(), g, level.getCurrentLevel());
            drawGameStatus(g);
        } else{
            LoseScreen loseScreen = new LoseScreen(this);
            loseScreen.draw(g, player.getScore());
        }


        setBackground(backgroundColor); // Define a cor de fundo inicial -> como ela muda conforme a tela (vitoria ou derrota)
                                        // devemos colocar ela dentro do gameLoop

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
    public void windowLostFocus() {
        System.out.println("Jogo foi minimizado");
        // Para o jogador caso ele esteja se movendo e
        // limpa a pilha de direções para eliminar qualquer
        // outra direção armazenada
        player.setMoving(false);
        player.setTypeOfAnimation(0);
        KeyboardInputs.clearStack();
    }

    // Retorna o Mapa Atual da fase
    public char[][] getNowMap() {
        return this.level.getMatrixMap();
    }

    public static void changeLevel(boolean bool) {
        changeLevel = bool;
    }

    private void drawGameStatus(Graphics g) {
        drawScore(g);
        drawPlayerLife(g);
        drawChestStatus(g);
    }

    private void drawPlayerLife(Graphics g) {
        int imgSpacement = 40;
        for (int i = 0; i < player.getLifes(); i++) {
            g.drawImage(BufferedImagesAssets.playerLifeImg, 0 + (imgSpacement * i), 0, 80, 80, null);
        }
    }

    private void drawScore(Graphics g) {
        Font fontScore = new Font("Arial", Font.BOLD, 30);
        Font fontText = new Font("Arial", Font.TRUETYPE_FONT, 30);
        int scoreXPosition = 1150;
        g.setFont(fontText);
        g.drawString("Score:", scoreXPosition - 100, 40);
        g.setFont(fontScore);
        g.setColor(Color.BLUE);
        g.drawString(Integer.toString(player.getScore()), scoreXPosition, 40);
    }

    private void drawChestStatus(Graphics g) {
        Font fontText = new Font("Arial", Font.TRUETYPE_FONT, 27);
        Font fontChests = new Font("Arial", Font.BOLD, 30);

        g.setFont(fontText);
        g.setColor(Color.black);
        g.drawString("Chests Found:", 440, 40);
        g.setFont(fontChests);
        g.setColor(Color.BLUE);
        g.drawString(ChestHandler.getChestsOpened() + " of " + Integer.toString(ChestHandler.getChestsInLevel()), 634, 40);


    }

    public void changeBackgroundColor(Color color){
        this.backgroundColor = color;
    }
}

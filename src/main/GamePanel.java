package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import entities.Player;
import inputs.KeyboardInputs;
import objects.ChestHandler;
import physics.Collisions;
import physics.Gravity;
import screens.LoseScreen;
import screens.VictoryScreen;
import soundtrack.SoundHandler;
import levels.Level;
import utils.Constants.BufferedImagesAssets;
import utils.Constants.GameDimentions;

import java.awt.Dimension;
import java.awt.Graphics;

import static utils.Constants.GameDimentions;

/* Classe responsável por renderizar tudo na tela 
 * para o usuário poder jogar, é a principal classe
 * da aplicação
 */
public class GamePanel extends JPanel {
    private Player player;
    private Level level;
    private Collisions collisions;
    private Gravity gravity;
    private Color backgroundColor = Color.WHITE;
    private LoseScreen loseScreen;
    private VictoryScreen victoryScreen;

    private String GameState;
    private static boolean changeLevel = false;

    public GamePanel(Game game) {
        // instanciação
        this.player = new Player(this); // posição inicial do player em determinada fase
        this.level = new Level(game); 
        this.collisions = new Collisions(getNowMap(), player);
        this.gravity = new Gravity(player);
        this.loseScreen = new LoseScreen(this);
        this.victoryScreen = new VictoryScreen(this);

        level.loadMapAssets();
        player.loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this, this.player));
        resetToInitStatus();

    }


    /* Realiza o update constante de váriaveis dentro do gameLoop */
    public void updateGame() {

        // Se a variável changeLevel for true então o jogador passou de fase
        // Logo deve-se resetar a sua posição tal como avisar a instancia
        // collisions que a Matriz (MAPA) agora a ser verificada é outra.
        if (changeLevel) {
            player.resetToInitialPosition();
            collisions.updateLevelToCheck(level.getMatrixMap());
            changeLevel = false;

        }
        
        // Caso o jogador não tenha passado de fase então atualiza
        // instancias que devem ser verificadas a todo instante
        ChestHandler.updateChests();
        player.update(); // Status do jogador como animações, movimentação entre outros
        collisions.checkCollisions(); // colisão -> se o jogador está colidindo com algo a cada vez que se move
        gravity.gravityForce(); // aplicando a gravidade para garantir que o jogador caia sempre que não
                                // estiver em cima de um bloco

    }


    /* Função responsável por desenhar coisas na telas
     * para que seja visível ao usuário
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Se o usuário venceu desenha a tela de vitória
        // e muda o estado do jogo para que o KeyboardInputs
        // tenha uma ação diferente
        if (verifyIfPlayerWon()) {
            victoryScreen.draw(g, player.getScore());
            victoryScreen.playVictorySounds();
            setGameState("Victory");
        

        // Se o usuário perdeu desenha a tela de derrota
        // e muda o estado do jogo.
        } else if (verifyIfPlayerLost()) {

            loseScreen.loadLoseScreen(g, player.getScore());
            loseScreen.playLoseScreenSounds();

            setGameState("Lose");
        } 
        // Caso contrário desenha o jogador, o level, e o status atual do jogo
        else {
            player.render(g);
            level.draw(level.getMatrixMap(), g, level.getCurrentLevel());

            drawGameStatus(g);
        }

        setBackground(backgroundColor); // Define a cor de fundo inicial -> como ela muda conforme a tela (vitoria ou
                                        // derrota)
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
    

    // Seter para saber se o gamePanel deve mudar de level
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
        g.drawString("Chests Found:", 480, 40);
        g.setFont(fontChests);
        g.setColor(Color.BLUE);
        g.drawString(ChestHandler.getChestsOpened() + " of " + Integer.toString(ChestHandler.getChestsInLevel()), 670,
                40);

    }

    public void changeBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public void setGameState(String gameState) {
        this.GameState = gameState;
    }

    public String getGameState() {
        return GameState;
    }

    public Level getGamePLevel() {
        return this.level;
    }

    public void resetToInitStatus() {
        setGameState("In game");
        SoundHandler.playBackgourndSound();
        player.reset();
        level.resetToLevel1();
        collisions = new Collisions(level.getMatrixMap(), player);
        changeBackgroundColor(Color.WHITE);
        loseScreen.setSoundIsPlaying(false);
        victoryScreen.setSoundIsPlaying(false);
    }

    private boolean verifyIfPlayerLost() {
        return player.getLifes() == 0;
    }

    private boolean verifyIfPlayerWon() {
        return level.getCurrentLevel() == 4;
    }
    public Player getPlayer() {
        return player;
    }
}

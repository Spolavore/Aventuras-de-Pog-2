package entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import inputs.KeyboardInputs;
import main.GamePanel;
import physics.Damage;
import physics.Jump;
import utils.AssetsHandler;
import utils.Constants.BufferedImagesAssets;
import utils.Constants.Directions;
import utils.Constants.Sprites;

public class Player extends Entity {
    private GamePanel gamePanel;
    private Damage damage;
    private int lifes;
    private int typeOfAnimation;
    private int playerDirection;
    private boolean isMoving = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isInChest = false;
    private boolean isTryingtoOpenChest = false;
    private boolean isInEndpoint = false;
    private BufferedImage img;
    private Jump playerJump;
    private int score;
    public static final int[] playerInitialPosition = { 0, 576 };
    private boolean[] canMove = { true, true, true, true }; // array que armazena as direções que o player pode se mover
                                                            // 0: W | 1: A | 2: S | 3: D

    // As animações possuem 7 tipos, cada uma é uma linha da matriz animations
    // algumas animações possuem apenas 5 sprites, enquanto outras 8. Esses números
    // referem-se às colunas da matriz citado
    // Para saber todas as animações possíveis ir assets/player/char_blue.png

    private BufferedImage[][] animations;
    private int playerAniTick, playerAniIndex, playerAniSpeed = 20;


    public Player(GamePanel g) {
        super(playerInitialPosition[0], playerInitialPosition[1]);
        this.gamePanel = g;
        this.lifes = 3;
        this.score = 0;
        this.playerJump = new Jump(this); // Inicializa o jump permitindo que o player pule
        this.damage = new Damage(this);
        loadAnimations();
    }

    public void update() {
      
        updateAnimationTick();
        updatePosition();
        
        damage.applyDamage();
        changeAnimationAfterFalling();

    }

    public void render(Graphics g) {
        g.drawImage(animations[typeOfAnimation][playerAniIndex], (int) getX(), (int) getY(), 64, 64, null);
    }

    // 0 - 5
    // 0: parado
    // 1: atacando
    // 2: caminhando
    // 3: pulando
    // 4: caindo
    // 5: sofrendo dano
    public void setTypeOfAnimation(int typeOfAnimation) {
        this.typeOfAnimation = typeOfAnimation;
    }

    public void loadAnimations() {
        img = AssetsHandler.LoadAssets(Sprites.PLAYER);
        animations = new BufferedImage[6][8];
        // Pega-se uma subimagem da imagem original (assets/player/char_blue.png)
        // recortando conforme a imagem e colocando as subimagens separada
        // na matriz animations
        for (int i = 0; i < 6; i++) {
            // esse if é utilizado para pegar a animação da queda, no caso nao queremos toda
            // a animação
            // apenas a primeira sprite
            if (i != 4) {
                for (int j = 0; j < 8; j++) {
                    animations[i][j] = img.getSubimage(j * 56, i * 56, 56, 56);
                }
            } else {

                animations[i][0] = img.getSubimage(1 * 56, i * 56, 56, 56);
            }

        }
    }

    private void updateAnimationTick() {
        // Numeros do vetor referem-se a quantas sprites tem cada animação
        // da char_blue.png, ou seja, quantas imagens tem disponiveis para cada
        // tipo de animação
        int animationsMaxIndexes[] = { 6, 6, 8, 8, 1 };
        int currentAnimationIndex = animationsMaxIndexes[this.getTypeOfAnimation()];

        // Aqui fazemos a verificação para saber se a animação atual é diferente da do
        // player caindo
        // pois como a animação do player caindo so tem tamnho 1 então o playerAniIndex
        // sempre vai ser = 0.
        if (getTypeOfAnimation() != 4) {
            playerAniTick++;
            if (playerAniTick >= playerAniSpeed) {
                playerAniTick = 0;
                playerAniIndex += 1;
                if (playerAniIndex >= currentAnimationIndex) {
                    playerAniIndex = 0;
                }
            }
        } else {
            playerAniIndex = 0;
        }

    }

    public void updatePosition() {
        int playerDirection = this.getDirection();

        if (isJumping) {
            if (!playerJump.coolDownIsOn()) {
                playerJump.jump();
            } else {
                System.err.println("Pulo não disponível");
            }

        }
        if (playerDirection == Directions.LEFT && canMove[1] && isMoving && !isFalling) {
            if (isJumping && !playerJump.coolDownIsOn()) {
                playerJump.jumpWithMovimentation(Directions.LEFT);
            } else {

                this.updateXPosition(-1);
            }

        }

        if (playerDirection == Directions.RIGHT && canMove[3] && isMoving && !isFalling) {
            if (isJumping && !playerJump.coolDownIsOn()) {
                playerJump.jumpWithMovimentation(Directions.RIGHT);
            } else {

                this.updateXPosition(1);
            }

        }

    }

    private void changeAnimationAfterFalling() {
        if (!isFalling && !isJumping && KeyboardInputs.sizeDirectionStack() == 0) {

            setTypeOfAnimation(0);
        } else {
            int lastDirection = KeyboardInputs.getLastDirection();
            if (lastDirection == Directions.LEFT || lastDirection == Directions.RIGHT) {
                setTypeOfAnimation(2);

            }
        }
    }

    public void resetToInitialPosition(){
        this.setXY(playerInitialPosition[0],playerInitialPosition[1]);
       
    }



    public int getLifes() {
        return lifes;
    }

    public void decreaseLife() {
        this.lifes -= 1;
    }

    public int getTypeOfAnimation() {
        return typeOfAnimation;
    }

    public void setDirection(int playerDirection) {
        this.playerDirection = playerDirection;
    }

    public int getDirection() {
        return playerDirection;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isInEndpoint() {
        return isInEndpoint;
    }

    public void setIsInEndpoint(boolean bool) {
        this.isInEndpoint = bool;
    }

    public void setJumping(boolean bool) {
        this.isJumping = bool;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isInChest(){
        return isInChest;
    }

    public void setIsInChest(boolean bool){
        isInChest = bool;
    }

    public boolean isTryingtoOpenChest(){
        return isTryingtoOpenChest;
    }
    
    public void setIsTryingtoOpenChest(boolean bool){
        this.isTryingtoOpenChest = bool;

    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean bool) {
        this.isFalling = bool;
    }

    public BufferedImage[][] getAnimations() {
        return animations;
    }

    public int getPlayerAniIndex() {
        return playerAniIndex;
    }

    public void setPlayerCanMove(boolean bool, int index) {
        this.canMove[index] = bool;
    }

    public boolean[] canMove() {
        return this.canMove;
    }

    public int playerXPosition() {
        return this.getX();
    }

    public int playerYPosition() {
        return this.getY();
    }
    

    public int getScore() {
        return score;
    }
}

package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.AssetsHandler;
import utils.Constants.Directions;
import utils.Constants.Sprites;


public class Player extends Entity {
    private GamePanel gamePanel;
    private int lifes;
    private int points;
    private int typeOfAnimation;
    private int playerDirection;
    private boolean isMoving = false;
    private BufferedImage img;
    
    // As animações possuem 7 tipos, cada uma é uma linha da matriz animations
    // algumas animações possuem apenas 5 sprites, enquanto outras 8. Esses números
    // referem-se às colunas da matriz citado
    // Para saber todas as animações possíveis ir res/char_blue.png
    
    private BufferedImage[][] animations;
    private int playerAniTick, playerAniIndex,playerAniSpeed = 10;



    public Player(GamePanel g, int x, int y) {
        super(x, y);
        this.gamePanel = g;
        this.lifes = 0;
        this.points = 0;
        loadAnimations();
    }

    public void update(){
        updateAnimationTick();
        updatePosition();
        System.out.println(this.getY());
    }

    public void render(Graphics g){
        g.drawImage(animations[typeOfAnimation][playerAniIndex], (int)getX(),(int)getY(), 128, 128,null);
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
            // Pega-se uma subimagem da imagem original (res/char_blue.png)
            // recortando conforme a imagem e colocando as subimagens separada
            // na matriz animations
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 8; j++) {
                    animations[i][j] = img.getSubimage(j * 56, i * 56, 56, 56);
                }
            }
    }
    private void updateAnimationTick() {
        // Numeros do vetor referem-se a quantas sprites tem cada animação
        // da char_blue.png, ou seja, quantas imagens tem disponiveis para cada
        // tipo de animação
        int animationsMaxIndexes[] = {6,6,8,8,8};

        playerAniTick++;
        if (playerAniTick >= playerAniSpeed){
            playerAniTick = 0;
            playerAniIndex+=1;
            if(playerAniIndex >= animationsMaxIndexes[this.getTypeOfAnimation()]){
                playerAniIndex = 0;
            }
        }
    }

    public void updatePosition() {

        if (this.isMoving()) {
            int playerDirection = this.getDirection();
            if (playerDirection == Directions.DOWN) {
                if(this.getY() < 514){
                    this.updateYPosition(1);
                }
               
            } else if (playerDirection == Directions.UP) {
                this.updateYPosition(-1);
                

            } else if (playerDirection == Directions.RIGHT) {
                this.updateXPosition(1);
                

            } else {
                this.updateXPosition(-1);
                

            }
        }

    }


    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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


    public BufferedImage[][] getAnimations() {
        return animations;
    }

    public int getPlayerAniIndex() {
        return playerAniIndex;
    }




}

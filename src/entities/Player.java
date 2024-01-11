package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import inputs.KeyboardInputs;
import main.GamePanel;
import physics.Damage;
import physics.Jump;
import soundtrack.SoundHandler;
import utils.AssetsHandler;

import utils.Constants.Directions;
import utils.Constants.Sprites;

/* Classe player, responsável por tudo relacionado ao jogador
 * e algumas coisas que estão em contato com ele
 */
public class Player extends Entity {

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
    private BufferedImage img; // Imagem do jogador (encontrada em assets/player/char_blue.png)
    private Jump playerJump;
    private int score;
    public static final int[] playerInitialPosition = { 0, 576 }; // Posição inicial do jogador em todas as fases
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
        this.lifes = 3;
        this.score = 0;
        this.playerJump = new Jump(this); // Inicializa o jump permitindo que o player pule
        this.damage = new Damage(this); // Inicializa o dano fazendo com que o player seja capaz de
                                        // tomar dano de queda
        loadAnimations();
    }

    // Função chamada dentro do loop do game, dentro da Classe gamePanel
    // Realiza todas as modificações que devem ser realizadas constantemente
    // No Jogador
    public void update() {
        updateAnimationTick();
        updatePosition();
        damage.applyDamage();
        changeAnimationAfterFalling();
    }

    /*
     * Função que renderiza a imagem do jogador na tela
     */
    public void render(Graphics g) {
        g.drawImage(animations[typeOfAnimation][playerAniIndex], (int) getX(), (int) getY(), 64, 64, null);
    }

    // Tipos de animações e seus respectivos índices:
    // (OBS: nem todas as animações estão sendo usadas)
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

    // Função responsável pela mudança de movimentação do jogador
    // com base nas teclas clicadas ou no estado atual do jogador
    // se ele está caindo ou pulado por exemplo.
    // x -> -1 o player anda pra esquerda | x -> +1 o player anda pra direita
    // y -> +1 o player anda para baixo(cai) | y -> -1 player anda para cima (pula)
    public void updatePosition() {
        int playerDirection = this.getDirection();

        if (isJumping) {
            if (!playerJump.coolDownIsOn()) {
                playerJump.jump();
            }

        }
        if (playerDirection == Directions.LEFT && canMove[1] && isMoving && !isFalling) {
            if (isJumping && !playerJump.coolDownIsOn()) {
                // Pulo com movimentação permite que o jogador pule para um direção
                // em específico
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

    /*
     * Função reponsável por trocar a animação do jogador enquanto ele cai para
     * quando ele não está caindo.
     * Responsável, também, salvar a direção para a qual o player está pulando
     * e setar a animação de acordo.
     */
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


    /* Funções abaixo são getters, setters e alguns resetadores de variáveis.
     * Nomes auto-explicativos, por isso não serão documentadas.
     */
    public void resetToInitialPosition() {
        this.setXY(playerInitialPosition[0], playerInitialPosition[1]);

    }

    public int getLifes() {
        return lifes;
    }

    public void decreaseLife() {
        SoundHandler.playSound("sounds/damage.wav");
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

    public boolean isInChest() {
        return isInChest;
    }

    public void setIsInChest(boolean bool) {
        isInChest = bool;
    }

    public boolean isTryingtoOpenChest() {
        return isTryingtoOpenChest;
    }

    public void setIsTryingtoOpenChest(boolean bool) {
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

    public void increaseScore(int score) {
        this.score += score;
    }

    public void reset() {
        score = 0;
        lifes = 3;
        setXY(playerInitialPosition[0], playerInitialPosition[1]);
    }
}

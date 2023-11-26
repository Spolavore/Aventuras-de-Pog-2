package utils;

import main.GamePanel;


public class Player {
    private GamePanel gamePanel;
    private int lifes;
    private int points;
    private int typeOfAnimation;
    private int playerDirection;
    private boolean isMoving = false;

    
    public Player(GamePanel g) {
        this.gamePanel = g;
        this.lifes = 0 ;
        this.points = 0;
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

    public boolean isMoving(){
        return isMoving;
    }
}

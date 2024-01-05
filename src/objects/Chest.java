package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import utils.Constants.BufferedImagesAssets;

public class Chest {
    private BufferedImage[] chestAnimations;
    private int chestAniTick, chestAniIndex, chestAniSpeed = 20;
    private int xPos, yPos;
    private boolean isOpened;
    private String typeOfAnimation;
    public boolean loadOpenAnimation;
    private boolean shouldUpdate = true;

    public Chest(int xPos, int yPos, String typeOfAnimation) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.typeOfAnimation = typeOfAnimation;
        loadDefaultChestAnimation();
    }

    public void drawChest(Graphics g) {

        switch (typeOfAnimation) {

            case "default":
                loadDefaultChestAnimation();
                break;

            case "opening":
                loadOpenChestAnimation();
                break;
            case "opened":
                loadOpenChestAnimation();
                if(chestAniIndex == 4){
                    shouldUpdate = false;
                }
                break;
            default:
                break;
        }

        g.drawImage(chestAnimations[chestAniIndex], xPos, yPos, null);
    }

    public void update() {
        if(shouldUpdate){

            updateAnimationTick();
        }
    }


    private void loadDefaultChestAnimation() {
        chestAnimations = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            chestAnimations[i] = BufferedImagesAssets.chestImage.getSubimage(i * 48, 0, 48, 32);

        }

    }

    private void loadOpenChestAnimation() {
        chestAnimations = new BufferedImage[5];
        chestAniSpeed = 40;
        for (int i = 0; i < 5; i++) {
            chestAnimations[i] = BufferedImagesAssets.chestImage.getSubimage(i * 48, 32, 48, 32);
        }
    }

    private void setChestAssetOpened() {
        if(chestAniIndex == 4 ){
           
            chestAniIndex = 4;
            shouldUpdate = false;
        }
    }

    // Como a animação do bau tem sempre o mesmo tamnaho podemos escrever e chamar
    // esse método como estático.
    public void updateAnimationTick() {
        chestAniTick++;
        if (chestAniTick >= chestAniSpeed) {
            chestAniTick = 0;
            chestAniIndex += 1;
            if (chestAniIndex >= 5) {
                chestAniIndex = 0;
            }
        }

    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void changeTypeOfAnimation(String typeOfAnimation) {
        this.typeOfAnimation = typeOfAnimation;
    }

    public void setIsOpened(boolean bool) {
        isOpened = bool;
    }

    public boolean isOpened() {
        return isOpened;
    }
}

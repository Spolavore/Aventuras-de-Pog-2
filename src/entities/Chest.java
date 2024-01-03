package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utils.Constants.BufferedImagesAssets;

public class Chest extends Entity {
    private static BufferedImage[] chestAnimations;
    private boolean isBeingOppened = false;
    public static int chestsInLevel = 0;
    private static int chestAniTick, chestAniIndex, chestAniSpeed = 20;
    private static ArrayList<Integer> xPositionAllChests = new ArrayList<>();
    private static ArrayList<Integer> yPositionAllChests = new ArrayList<>();
    private static int lastPositionOccupied = 0;

    public Chest(int xChestPosition, int yChestPosition) {
        super(xChestPosition, yChestPosition);
        loadDefaultChestAnimation();
        chestsInLevel += 1;
    }

    public void drawChest(Graphics g) {

        // g.drawImage(chestAnimations[chestAniIndex], getX(), getY(), null);
         g.drawImage(BufferedImagesAssets.chestImage.getSubimage(0 * 48, 0, 48, 32), getX(), getY(), null);

    }

    public static void openChest(int xPlayerPosition, int yPlayerPosition) {
        int playerXPositionConverter = xPlayerPosition / 32;
        int playerYPositionConverter = yPlayerPosition / 32;
       
        for (int i = 0; i < xPositionAllChests.size(); i++) {
            System.out.println(xPositionAllChests.get(i) + " | " + playerXPositionConverter  *32);
            if (xPositionAllChests.get(i) == playerXPositionConverter * 32
                && yPositionAllChests.get(i) == playerYPositionConverter * 32) {
                System.out.println("entrei");
                loadOpenChestAnimation();
            } else {
                
                loadDefaultChestAnimation();
            }
        }

    }

    private static void loadDefaultChestAnimation() {
        chestAnimations = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            chestAnimations[i] = BufferedImagesAssets.chestImage.getSubimage(i * 48, 0, 48, 32);

        }
    }

    private static void loadOpenChestAnimation() {
        chestAnimations = new BufferedImage[5];
        chestAniSpeed = 40;
        for (int i = 0; i < 5; i++) {
            chestAnimations[i] = BufferedImagesAssets.chestImage.getSubimage(i * 48, 32, 48, 32);
        }
    }

    // Como a animação do bau tem sempre o mesmo tamnaho podemos escrever e chamar
    // esse método como estático.
    public static void updateAnimationTick() {
        chestAniTick++;
        if (chestAniTick >= chestAniSpeed) {
            chestAniTick = 0;
            chestAniIndex += 1;
            if (chestAniIndex >= 4) {
                chestAniIndex = 0;
            }
        }

    }

    public static void setAllChestsPositions(ArrayList<Integer> allXPositions, ArrayList<Integer> allYPositions) {
        xPositionAllChests = allXPositions;
        yPositionAllChests = allYPositions;

    }

    public static void setNumOfChests(int numOfChest) {
        chestsInLevel = numOfChest;
    }

}

package objects;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Player;

public class ChestHandler {
    private static ArrayList<Integer> xPositionAllChests = new ArrayList<>();
    private static ArrayList<Integer> yPositionAllChests = new ArrayList<>();
    private static int chestsInLevel = 0;

    private static ArrayList<Chest> chestList = new ArrayList<>();

    public void drawAllChests(Graphics g) {
        for (int i = 0; i < chestList.size(); i++) {
            chestList.get(i).drawChest(g);
        }

    }

    public static void updateChests() {
        for (int i = 0; i < chestList.size(); i++) {
            chestList.get(i).update();
        }
    }

    public void createAllChest() {

        for (int i = 0; i < xPositionAllChests.size(); i++) {
            if (!(chestList.size() == chestsInLevel)) {
                chestList.add(new Chest(xPositionAllChests.get(i), yPositionAllChests.get(i), "default"));
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

    public static int getChestsInLevel() {
        return chestsInLevel;
    }

    public static void openChestCallBack(Player player) {
        int playerXPos = player.getX();
        int playerYPos = player.getY() + 32;

        // Erro relativo ao tentar abrir o bau
        // Serve para que se a | Player Position - Chest Position | for menor que
        // acceptableDistance então permite ele abrir o baú
        int xAcceptableDistance = 30;
        int yAcceptableDistance = 10;

        for (int i = 0; i < chestList.size(); i++) {
            Chest currentChest = chestList.get(i);
            int xCmp = Math.abs(playerXPos - currentChest.getxPos());
            int yCmp = Math.abs(playerYPos - currentChest.getyPos());

            if (xCmp < xAcceptableDistance && yCmp < yAcceptableDistance && !currentChest.isOpened()) {
                currentChest.changeTypeOfAnimation("opening");
                player.increaseScore(200);
                player.setIsTryingtoOpenChest(false);
                currentChest.setIsOpened(true);
                currentChest.changeTypeOfAnimation("opened");

            }
        }
    }
}

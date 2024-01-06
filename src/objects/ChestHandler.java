package objects;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Player;
import soundtrack.SoundHandler;

public class ChestHandler {
    private static ArrayList<Integer> xPositionAllChests = new ArrayList<>();
    private static ArrayList<Integer> yPositionAllChests = new ArrayList<>();
    private static int chestsInLevel = 0;
    private static int chestsOpened = 0;

    private static ArrayList<Chest> chestList = new ArrayList<>(); // Lista que armazena todos os baús presentes em uma fase
                                                                   // quando o level é mudado essa lista é limpada pela função clearChestList



    // Função que deseja todos o baús presentes em uma determinada fase                                                           
    public void drawAllChests(Graphics g) {
        for (int i = 0; i < chestList.size(); i++) {
            chestList.get(i).drawChest(g);
        }

    }


    // Função que realiza o update de todas as animações presentes 
    // em instancias da classe Chest
    public static void updateChests() {
        for (int i = 0; i < chestList.size(); i++) {
            chestList.get(i).update();
        }
    }


    // Função que cria instancias de Chest para que posteriormente
    // elas sejam desenhadas pela função drawAllChest().
    // O if é de suma importancia para que o programa só desenhe
    // a quantidade de baús necessário. Evitando que dentro do loop infinito
    // do jogo os baús sejam desenhados infinitamente.

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

    public static void setChestsOpened(int chestOpened){
        chestsOpened = chestOpened;
    }

    public static void increaseChestOpened(){
        chestsOpened++;
    }

    public static int getChestsInLevel() {
        return chestsInLevel;
    }


    public static int getChestsOpened(){
        return chestsOpened;
    }

    // Função callBack chamada toda vez que o player tenta abrir um baú
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
                ChestHandler.increaseChestOpened();
                SoundHandler.playSound("sounds/coin_effect.wav");
                player.increaseScore(200);
                player.setIsTryingtoOpenChest(false);
                currentChest.setIsOpened(true);
                currentChest.changeTypeOfAnimation("opened");

            }
        }
    }


    public static void reset(){
        setChestsOpened(0);
        clearChestList();
    }

    public static void clearChestList(){
        chestList.clear();
    }
}

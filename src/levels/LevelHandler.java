package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Chest;
import main.Game;
import utils.AssetsHandler;
import utils.Constants.BufferedImagesAssets;
import utils.Constants.LevelDefaultAssets;
import utils.Constants.Sprites;

public class LevelHandler {

    private Game game;
    private ArrayList<BufferedImage> floorSprites = new ArrayList<>();
    private ArrayList<BufferedImage> platformSprites = new ArrayList<>();
    private ArrayList<BufferedImage> limitsSprites = new ArrayList<>();
    private BufferedImage endPoint;

    public LevelHandler(Game game) {
        this.game = game;
        this.endPoint = AssetsHandler.LoadAssets(LevelDefaultAssets.END_POINT); // como o endpoint para passar para a
                                                                                // próxima fase é igual para todos
                                                                                // já o setamos aqui mesmo
    }

    public void drawLevel(char[][] LevelMatrix, Graphics g, int level) {
        renderLevelMatrix(LevelMatrix, g, level);

    }

    // Recebe as sprites definidas na classe Constants.java
    public void importLevelSprites(String[] floorSprites, String[] platformSprites, String[] limitsSprites) {

        for (int i = 0; i < floorSprites.length; i++) {
            this.floorSprites.add(AssetsHandler.LoadAssets(floorSprites[i]));
        }

        for (int i = 0; i < platformSprites.length; i++) {
            this.platformSprites.add(AssetsHandler.LoadAssets(platformSprites[i]));
        }

        for (int i = 0; i < limitsSprites.length; i++) {
            this.limitsSprites.add(AssetsHandler.LoadAssets(limitsSprites[i]));
        }

    }

    public void renderLevelMatrix(char[][] Matrix, Graphics g, int level) {
        // Matrix[0].length quantidade de colunas
        // Matrix.length quantidade de linhas
        int numOfChest = 0;
        ArrayList<Integer> chestsXPositions = new ArrayList<>();
        ArrayList<Integer> chestsYPositions = new ArrayList<>();

        if (level == 1 || level == 2 || level == 3) {
            for (int i = 0; i < Matrix[0].length; i++) {
                for (int j = 0; j < Matrix.length; j++) {
                    switch (Matrix[j][i]) {
                        case '#':
                            g.drawImage(platformSprites.get(0), i * 32, j * 32, null);
                            break;

                        case '(':
                            g.drawImage(floorSprites.get(1), i * 32, j * 32, null);
                            break;

                        case ')':
                            g.drawImage(floorSprites.get(2), i * 32, j * 32, null);
                            break;

                        case '=':
                            g.drawImage(floorSprites.get(0), i * 32, j * 32, null);
                            break;
                        case ']':
                            g.drawImage(floorSprites.get(3), i * 32, j * 32, null);

                        case '+':
                            g.drawImage(endPoint, i * 32, j * 32, null);
                            break;
                        case 'c':

                            Chest chest = new Chest(i * 32, j * 32);
                            chestsXPositions.add(i * 32);
                            chestsYPositions.add(j * 32);
                            chest.drawChest(g);
                            numOfChest++;
                            break;
                        default:
                            break;

                    }

                }
            }
        }

        // Resetamos variaveis da classe Chest ao sair do loop
        // pois estamos em um laço while do game então as informações
        // sempre estarão presentes. Logo precisamos zerá-las para que
        // não sejam somadas
        Chest.setNumOfChests(numOfChest);
        Chest.setAllChestsPositions(chestsXPositions, chestsYPositions);
        numOfChest = 0;
        

    }

}

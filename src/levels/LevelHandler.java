package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utils.AssetsHandler;
import utils.Constants.Sprites;

public class LevelHandler {

    private Game game;
    private ArrayList<BufferedImage> floorSprites = new ArrayList<>();
    private ArrayList<BufferedImage> platformSprites = new ArrayList<>();
    private ArrayList<BufferedImage> limitsSprites = new ArrayList<>();

    public LevelHandler(Game game) {
        this.game = game;

    }

    public void drawLevel(char[][] LevelMatrix, Graphics g, int level) {
        renderLevelMatrix(LevelMatrix, g, level);
        // if(this.levelSprites.size() == 0){
        // System.out.println("Não há sprites para desenhar");
        // } else{
        // for(int i = 0; i < levelSprites.size(); i++){
        // g.drawImage(levelSprites.get(i), i*32, 0, null);
        // }
        // }

    }

    // Recebe as sprites definidas na classe Constants.java
    public void importLevelSprites(String[] floorSprites, String[] platformSprites, String[] limitsSprites) {
        
        for (int i = 0; i < floorSprites.length; i++) {
            this.floorSprites.add(AssetsHandler.LoadAssets(floorSprites[i]));
        }

   
        for (int i = 0; i < platformSprites.length; i++) {
                this.platformSprites.add(AssetsHandler.LoadAssets(platformSprites[i]));
            }
       

        
        for (int i = 0; i <  limitsSprites.length; i++) {
                this.limitsSprites.add(AssetsHandler.LoadAssets(limitsSprites[i]));
            }
        

    }

    public void renderLevelMatrix(char[][] Matrix, Graphics g, int level) {
        // Matrix[0].length quantidade de colunas
        // Matrix.length quantidade de linhas

        if (level == 1) {
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
                        default:
                            break;

                    }
                }
            }
        }
    }

}

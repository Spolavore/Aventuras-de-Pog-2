package levels;

import main.Game;
import main.GamePanel;
import objects.ChestHandler;
import utils.Constants.LevelDefaultAssets;
import utils.Constants.LevelMatrix;

import java.awt.Graphics;

import levels.LevelHandler;

public class Level {
    private static char[][] LevelMap;
    private LevelHandler levelHandler;
    private static int currentLevel = 1;

    public Level(Game game){
        LevelMap = LevelMatrix.Level1Map;
        this.levelHandler = new LevelHandler(game);
        
    }


    public void draw(char[][] MatrixLevel, Graphics g, int level){
        this.levelHandler.drawLevel(MatrixLevel, g, level);
    }


    public void loadMapAssets(){
        if(currentLevel == 1 || currentLevel == 2){
            String floorAssets[] = {LevelDefaultAssets.FLOOR,
                                    LevelDefaultAssets.LOWER_FLOOR,
                                    LevelDefaultAssets.LEFT_LOWER_FLOOR,
                                    LevelDefaultAssets.RIGHT_LOWER_FLOOR,
                                    LevelDefaultAssets.LEFT_FLOOR,
                                    LevelDefaultAssets.RIGHT_FLOOR,
                                    LevelDefaultAssets.PLATAFORM_MIDDLE,
                                    LevelDefaultAssets.PLATAFORM_LEFT,
                                    LevelDefaultAssets.PLATAFORM_RIGHT,
                                    };
            String plataformsAssets[] ={};
            String Limitor[] ={ };
            this.levelHandler.importLevelSprites(floorAssets, plataformsAssets, Limitor);
        }
    }


    public char[][] getMatrixMap() {
        return LevelMap;
    }


    public static void goNextLevel(){
        if(currentLevel < 3){
            currentLevel += 1;
            ChestHandler.clearChestList();
            switch (currentLevel) {
                case 2:
                    LevelMap = LevelMatrix.Level2Map;
                    break;
                
                case 3:
                    LevelMap = LevelMatrix.Level3Map;
                    break;
                default:
                    break;
            }
        GamePanel.changeLevel(true);
        }
    }

    public int getCurrentLevel(){
        return currentLevel;
    }
}

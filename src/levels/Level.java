package levels;

import main.Game;
import utils.Constants.LevelDefaultAssets;

import java.awt.Graphics;

import levels.LevelHandler;

public class Level {
    private char[][] LevelMap;
    private LevelHandler levelHandler;
 

    public Level(char[][] LevelMap, Game game){
        this.LevelMap = LevelMap;
        this.levelHandler = new LevelHandler(game);
        
    }


    public void draw(char[][] MatrixLevel, Graphics g, int level){
        this.levelHandler.drawLevel(MatrixLevel, g, level);
    }


    public void loadMapAssets(int level){
        if(level == 1){
            String floorAssets[] = {LevelDefaultAssets.LEVEL_1_GRASS_BLOCK, LevelDefaultAssets.LEVEL_1_LEFT_FLOOR_BORDER,
            LevelDefaultAssets.LEVEL_1_RIGHT_FLOOR_BORDER} ;
            String plataformsAssets[] ={LevelDefaultAssets.LEVEL_1_PLATAFORM};
            String Limitor[] ={ };
            this.levelHandler.importLevelSprites(floorAssets, plataformsAssets, Limitor);
        }
    }


    public char[][] getMatrixMap() {
        return this.LevelMap;
    }

}

package levels;

import main.Game;

import java.awt.Graphics;

import levels.LevelHandler;

public class Level {
    private char[][] LevelMap;
    private LevelHandler levelHandler;
 

    public Level(char[][] LevelMap, Game game){
        this.LevelMap = LevelMap;
        this.levelHandler = new LevelHandler(game);
        
    }


    public void draw(char[][] MatrixLevel, Graphics g){
        this.levelHandler.drawLevel(MatrixLevel, g);
    }


    public void loadMapAssets(String  floorAssets,String plataformsAssets, String Limitor){
        this.levelHandler.importLevelSprites(floorAssets, plataformsAssets, Limitor);
    }


    public char[][] getMatrixMap() {
        return this.LevelMap;
    }

}

package levels;

import main.Game;
import main.GamePanel;
import objects.ChestHandler;
import utils.Constants.LevelDefaultAssets;
import utils.Constants.LevelMatrix;

import java.awt.Graphics;

public class Level {
    private static char[][] LevelMap;
    private LevelHandler levelHandler;
    public static int currentLevel = 1;

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

    /* Função chamada toda vez que o jogador
     * clicar no endPoint, caso o level que ele
     * esteja seja menor que 3 então ele vai para o próximo
     * level, incrementando o currentLevel em 1
     * 
     * Caso o jogador esteja no level 4 e a função é chamada
     * significa que ele venceu, então é incrementado  o current
     * level mas a funçaõ GamePanel.changeLevel não é chamado, pois
     * NÂO existe o mapa 4, logo gerará erro
     */
    public static void goNextLevel(){
        if(currentLevel == 3 ){
            currentLevel += 1;
        }
        if(currentLevel < 3){
            currentLevel += 1;
            ChestHandler.reset();
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


    public void resetToLevel1(){
        currentLevel = 1;
        LevelMap = LevelMatrix.Level1Map;
        ChestHandler.reset();
    }
    public int getCurrentLevel(){
        return currentLevel;
    }
}

package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utils.AssetsHandler;
import utils.Constants.Sprites;

public class LevelHandler {
    
    private Game game;
    private ArrayList<BufferedImage> levelSprites = new ArrayList<>();

    public LevelHandler(Game game){
        this.game = game;

    }

    public void drawLevel(Graphics g){
        // g.drawImage(levelSprites,10,0,null);
        if(this.levelSprites.size() == 0){
            System.out.println("Não há sprites para desenhar");
        } else{
            for(int i = 0; i < levelSprites.size(); i++){
                g.drawImage(levelSprites.get(i), i*32, 0, null);
            }
        }


    }

    // Recebe as sprites definidas na classe Constants.java
    public void importLevelSprites(String[] sprites){
        for(int i = 0; i < sprites.length; i++){
            levelSprites.add(AssetsHandler.LoadAssets(sprites[i]));
        }
    }

    public void update(){

    }
}

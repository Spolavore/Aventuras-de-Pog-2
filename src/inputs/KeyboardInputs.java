package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import utils.Player;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener  {
    private GamePanel gamePanel;
    private Player player;

    public KeyboardInputs(GamePanel gamePanel, Player player){
        this.gamePanel = gamePanel;
        this.player = player;
    }
    @Override
    public void keyTyped(KeyEvent e){
       
    }

    @Override
    public void keyReleased(KeyEvent e){
        player.setMoving(false);
        player.setTypeOfAnimation(0);
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        char keyPressed = Character.toUpperCase((e.getKeyChar()));
        
        
        player.setMoving(true);
        switch (keyPressed ) {

            case 'A':
                player.setDirection(Directions.LEFT);
                player.setTypeOfAnimation(2);
               
                break;
            
            case 'D':
                // gamePanel.changeXPosition(speed);
                player.setDirection(Directions.RIGHT);
                player.setTypeOfAnimation(2);
            
                break;
            case 'W':
                
                player.setDirection(Directions.UP);
                break;

            case 'S':
                player.setDirection(Directions.DOWN);

                player.setTypeOfAnimation(4);
                break;
            case 'Z':
                player.setTypeOfAnimation(1);
            default:
                player.setMoving(false);
        }
    
    }
}

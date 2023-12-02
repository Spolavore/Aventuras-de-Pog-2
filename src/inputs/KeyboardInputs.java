package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import entities.Player;
import main.GamePanel;
import physics.Collisions;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private Player player;
    private Collisions collisions;
    private ArrayList<Integer> directionStack = new ArrayList<>();

    public KeyboardInputs(GamePanel gamePanel, Player player, Collisions collisions) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.collisions = collisions;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        // verifica se todas as teclas foram soltadas;
        // se sim então significa que o player está parado
        char keyReleased = Character.toUpperCase(e.getKeyChar());

        switch (keyReleased) {
            case 'W':
                if(player.canMove()[0]){
                    player.setMoving(false);
                }
                break;
            case 'A':
                if(player.canMove()[1]){
                    player.setMoving(false);
                }
                break;
            case 'S':
                if(player.canMove()[2]){
                    player.setMoving(false);
                }                
                break;
            case 'D':
                if(player.canMove()[3]){
                    player.setMoving(false);
                }               
                break;        
            default:
                break;
        }
            // O Código remove a direção e essa parte vai selecionar qual é a direção
            // anterior a que foi removida. Isso serve para caso o usuário clique duas
            // teclas e solte uma, então a tecla que continua pressionada vai funcionar
            // normalmente. OBS: a verificação de -1 é caso só estiver uma tecla da pilha.
            // int indexLastDirection = directionStack.size() - 1;
            // if (indexLastDirection != -1) {

            //     player.setDirection(directionStack.get(indexLastDirection));
            // }
            // if (directionStack.isEmpty()) {
            //     player.setMoving(false);
            //     player.setTypeOfAnimation(0);
            // }
        }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = Character.toUpperCase((e.getKeyChar()));
 
        switch (keyPressed) {
            case 'W':
                if (player.canMove()[0]) {
                    player.setDirection(Directions.UP);
                    stackDirection(Directions.UP);
                    player.setMoving(true);
                }

                break;

            case 'A':
                if (player.canMove()[1]) {
                    player.setDirection(Directions.LEFT);
                    player.setTypeOfAnimation(2);
                    stackDirection(Directions.LEFT);
                    player.setMoving(true);

                }

                break;

            case 'S':
                if (player.canMove()[2]) {
                    player.setDirection(Directions.DOWN);
                    player.setTypeOfAnimation(4);
                    stackDirection(Directions.DOWN);
                    player.setMoving(true);

                }
                break;

            case 'D':
                if (player.canMove()[3]) {
                    player.setDirection(Directions.RIGHT);
                    player.setTypeOfAnimation(2);
                    stackDirection(Directions.RIGHT);
                    player.setMoving(true);

                }

                break;

            default:


        }

    }

    private void stackDirection(int dir) {
        int index = directionStack.indexOf(dir);
        if (index == -1) {
            directionStack.add(dir);
        }
    }
}
